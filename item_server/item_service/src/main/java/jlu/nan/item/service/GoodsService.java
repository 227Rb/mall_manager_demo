package jlu.nan.item.service;

import jlu.nan.common.vo.PageResult;
import jlu.nan.item.pojo.Goods;
import jlu.nan.item.pojo.Sku;
import jlu.nan.item.pojo.SpuDetail;
import jlu.nan.item.vo.GoodsDetailVO;
import jlu.nan.item.vo.GoodsVO;
import jlu.nan.item.vo.SkuVo;

import java.util.List;


/**
 * @ClassName: GoodsService
 * @Description:
 * @author: Nan
 * @date: 2020/3/30 13:18
 * @version: V1.0
 */

public interface GoodsService {
    PageResult<GoodsVO> findGoods(Integer page, Integer rows,Boolean saleable,String key);

    void saveGoodsWhitSpuAndSku(GoodsDetailVO goodsDetail);

    SpuDetail findGoodsDetail(Long gid);

    List<SkuVo> findSkuWithStock(Long skuGid);

    void upDateGoodsWhitSpuAndSku(GoodsDetailVO goodsDetail);

    void delSku(Long skuId);

    void saveSkus(List<Sku> skus);

    void delGoods(Long id);

    void upperGoods(Long id);

    void lowerGoods(Long id);

    GoodsVO findGoods(Long id);

    void delSkus(List<Long> cancelId);
}
