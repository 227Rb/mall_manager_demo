package jlu.nan.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jlu.nan.common.enums.ExectionEnum;
import jlu.nan.common.exception.CmException;
import jlu.nan.common.vo.PageResult;
import jlu.nan.item.mapper.GoodsMapper;
import jlu.nan.item.mapper.SkuMapper;
import jlu.nan.item.mapper.SpuMapper;
import jlu.nan.item.mapper.StockMapper;
import jlu.nan.item.pojo.Goods;
import jlu.nan.item.pojo.Sku;
import jlu.nan.item.pojo.SpuDetail;
import jlu.nan.item.pojo.Stock;
import jlu.nan.item.service.CategoryService;
import jlu.nan.item.service.GoodsService;
import jlu.nan.item.vo.GoodsDetailVO;
import jlu.nan.item.vo.GoodsVO;
import jlu.nan.item.vo.SkuVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @ClassName: GoodsServiceImpl
 * @Description:
 * @author: Nan
 * @date: 2020/3/30 13:19
 * @version: V1.0
 */

@Service
public class GoodsServiceImpl implements GoodsService{

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public GoodsVO findGoods(Long id) {
        if (id!=null){
            GoodsVO goodsVO = null;
            Goods goods = goodsMapper.selectByPrimaryKey(id);
            if(goods!=null){
                goodsVO = new GoodsVO();
                BeanUtils.copyProperties(goods,goodsVO);
                List<Integer> cids = Arrays.asList(goods.getCid1(), goods.getCid2(), goods.getCid3());
                goodsVO.setCategoryPath(categoryService.getCategoryPath(cids));
            }else{
                throw new CmException(ExectionEnum.GOODS_NO_FIND);
            }
            return goodsVO;
        }else{
            throw new CmException(ExectionEnum.INPUT_DATA_ERROR);
        }
    }

    @Override
    public PageResult<GoodsVO> findGoods(Integer page, Integer rows,Boolean saleable, String key) {
//        开启分页
        if(rows!=-1){
            PageHelper.startPage(page,rows);
        }

//        过滤
//         定义模版
        Example example = new Example(Goods.class);
        if (StringUtils.isNotBlank(key)){
            example.createCriteria().orLike("brand","%"+key+"%")
                                    .orLike("name","%"+key+"%");
        }

        if(saleable!=null){
            example.createCriteria().andEqualTo("saleable",saleable);
        }

//        默认排序 按更新时间排序
        example.setOrderByClause("last_Update_Time DESC");

        List<Goods> goodsList = goodsMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(goodsList)){
            throw  new CmException(ExectionEnum.GOODS_NO_FIND);
        }


//        加入PageResulr;
        PageInfo<Goods> info=new PageInfo<>(goodsList);

//        po转换vo
        List<GoodsVO>voList=goodsList.stream().map(goods -> {
            GoodsVO goodsVO = new GoodsVO();
            BeanUtils.copyProperties(goods,goodsVO);
            List<Integer> cids = Arrays.asList(goods.getCid1(), goods.getCid2(), goods.getCid3());
            goodsVO.setCategoryPath(categoryService.getCategoryPath(cids));
            return goodsVO;
        }).collect(Collectors.toList());

        return new PageResult<GoodsVO>(voList,info.getTotal(),info.getPages());
    }

    @Override
    public SpuDetail findGoodsDetail(Long gid) {
        SpuDetail spu = new SpuDetail();
        spu.setGid(gid);
        spu.setEnable(true);
        SpuDetail spuDetail = spuMapper.selectOne(spu);
        if(spuDetail==null){
            throw new CmException(ExectionEnum.GOODS_SPU_NO_FIND);
        }
        return spuDetail;
    }

    @Override
    public List<SkuVo> findSkuWithStock(Long skuGid) {
        Sku sku = new Sku();
        sku.setSpuGid(skuGid);
        List<Sku> skuList = skuMapper.select(sku);
        if(CollectionUtils.isEmpty(skuList)){
            throw new CmException(ExectionEnum.GOODS_SKU_NO_FIND);
        }

        List<Long> ids = skuList.stream().map(Sku::getId).collect(Collectors.toList());
        List<Stock> stocks = stockMapper.selectByIdList(ids);
        if(CollectionUtils.isEmpty(stocks)){
            throw new CmException(ExectionEnum.GOODS_STOCK_NO_FIND);
        }
        List<SkuVo> skuVos=skuList.stream().map(s->{
            SkuVo skuVo = new SkuVo();
            BeanUtils.copyProperties(s,skuVo);
            stocks.stream().filter(stock -> stock.getSkuId().equals(s.getId()))
                            .findFirst()
                            .map(stock -> {
                                skuVo.setStock(stock.getStock());
                                return null;
                            }).orElse(null);
            return skuVo;
        }).collect(Collectors.toList());

        if(CollectionUtils.isEmpty(skuVos)){
            throw new CmException(ExectionEnum.SKU_ADD_STOCK_ERROR);
        }

        return skuVos;
    }

    @Transactional
    @Override
    public void delSkus(List<Long> cancelId) {
        for (Long id : cancelId) {
            this.delSku(id);
        }
    }

    @Transactional
    @Override
    public void delSku(Long skuId) {
        int result = skuMapper.deleteByPrimaryKey(skuId);
        if (result==0){
            throw new CmException(ExectionEnum.DEL_SKU_ERROR);
        }
        result= stockMapper.deleteByPrimaryKey(skuId);
        if (result==0){
            throw new CmException(ExectionEnum.DEL_SKU_STOCK_ERROR);
        }

    }

    @Transactional
    @Override
    public void delGoods(Long id) {
        Goods del = new Goods();
        del.setId(id);
        del.setVatid(false);
        int result = goodsMapper.updateByPrimaryKeySelective(del);
        if (result!=1){
            throw new CmException(ExectionEnum.DEL_GOODS_ERROR);
        }

        SpuDetail delDetail = new SpuDetail();
        delDetail.setGid(id);
        delDetail.setEnable(false);
        result= spuMapper.updateByPrimaryKeySelective(delDetail);
        if (result!=1){
            throw new CmException(ExectionEnum.DEL_GOODS_SPU_ERROR);
        }

        Sku delGid = new Sku();
        delGid.setSpuGid(id);
        List<Sku> skus = skuMapper.select(delGid);
        List<Long> ids = skus.stream().map(Sku::getId).collect(Collectors.toList());
        for (Long delId : ids) {
            this.delSku(delId);
        }

        sendMsg("item.del",id);
    }

    @Transactional
    @Override
    public void upDateGoodsWhitSpuAndSku(GoodsDetailVO goodsDetail) {
        int result = goodsMapper.updateByPrimaryKeySelective(goodsDetail.getGoods());
        if(result!=1){
            throw new CmException(ExectionEnum.GOODS_UPDATE_ERROR);
        }

        SpuDetail spuDetail = goodsDetail.getSpuDetail();
        spuDetail.setGid(goodsDetail.getGoods().getId());
        result = spuMapper.updateByPrimaryKeySelective(spuDetail);
        if(result!=1){
            throw new CmException(ExectionEnum.SPU_UPDATE_ERROR);
        }

        if(!CollectionUtils.isEmpty(goodsDetail.getSkus())){
            for (Sku sku : goodsDetail.getSkus()) {
                result= skuMapper.updateByPrimaryKeySelective(sku);
                if(result==0){
                    throw new CmException(ExectionEnum.SKU_UPDATE_ERROR);
                }
                result = stockMapper.updateByPrimaryKeySelective(new Stock(sku.getId(),sku.getStock()));
                if(result==0){
                    throw new CmException(ExectionEnum.STOCK_UPDATE_ERROR);
                }
            }
        }
        sendMsg("item.update",goodsDetail.getGoods().getId());
    }

    @Transactional
    @Override
    public void upperGoods(Long id) {
        Goods goods = new Goods();
        goods.setId(id);
        goods.setSaleable(true);
        int result = goodsMapper.updateByPrimaryKeySelective(goods);
        if(result!=1){
            throw new CmException(ExectionEnum.GOODS_UPPER_ERROR);
        }
        sendMsg("item.update",id);
    }

    @Transactional
    @Override
    public void lowerGoods(Long id) {
        Goods goods = new Goods();
        goods.setId(id);
        goods.setSaleable(false);
        int result = goodsMapper.updateByPrimaryKeySelective(goods);
        if(result!=1){
            throw new CmException(ExectionEnum.GOODS_LOWER_ERROR);
        }
        sendMsg("item.update",id);
    }

    @Transactional
    @Override
    public void saveSkus(List<Sku> skus) {
        List<Stock> stockList = new ArrayList<>();
        int result=0;
        for (Sku sku : skus) {
            sku.setId(null);
            result= skuMapper.insertSelective(sku);
            if(result==0){
                throw new CmException(ExectionEnum.SKU_SAVE_ERROR);
            }
            stockList.add(new Stock(sku.getId(),sku.getStock()));
        }
        if(!CollectionUtils.isEmpty(stockList)){
            result = stockMapper.insertList(stockList);
            if(result==0){
                throw new CmException(ExectionEnum.STOCK_SAVE_ERROR);
            }
        }

        sendMsg("item.del",skus.get(0).getSpuGid());
    }

    @Transactional
    @Override
    public void saveGoodsWhitSpuAndSku(GoodsDetailVO goodsDetail) {
        Goods goods = goodsDetail.getGoods();
        goods.setId(null);
        int result = goodsMapper.insertSelective(goods);
        if(result!=1){
            throw new CmException(ExectionEnum.GOODS_SAVE_ERROR);
        }

        SpuDetail spuDetail = goodsDetail.getSpuDetail();
        spuDetail.setGid(goods.getId());
        result = spuMapper.insertSelective(spuDetail);
        if(result!=1){
            throw new CmException(ExectionEnum.SPU_SAVE_ERROR);
        }

        List<Stock> stockList = new ArrayList<>();
        for (Sku sku : goodsDetail.getSkus()) {
            sku.setId(null);
            sku.setSpuGid(goods.getId());
            result= skuMapper.insertSelective(sku);
            if(result==0){
                throw new CmException(ExectionEnum.SKU_SAVE_ERROR);
            }
            stockList.add(new Stock(sku.getId(),sku.getStock()));
        }
        if(!CollectionUtils.isEmpty(stockList)){
            result = stockMapper.insertList(stockList);

            if(result==0){
                throw new CmException(ExectionEnum.STOCK_SAVE_ERROR);
            }
        }

        sendMsg("item.save",goods.getId());
    }

    private void sendMsg(String routingKey,Long skuId) {
        try {
            amqpTemplate.convertAndSend(routingKey,skuId);
        } catch (AmqpException e) {
            e.printStackTrace();
        }
    }
}
