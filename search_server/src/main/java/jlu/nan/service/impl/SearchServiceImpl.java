package jlu.nan.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import jlu.nan.client.GoodsClient;
import jlu.nan.common.enums.ExectionEnum;
import jlu.nan.common.exception.CmException;
import jlu.nan.common.utils.JsonUtils;
import jlu.nan.common.vo.PageResult;
import jlu.nan.es.GoodsRepository;
import jlu.nan.item.pojo.SpecParam;
import jlu.nan.item.pojo.SpuDetail;
import jlu.nan.item.vo.GoodsVO;
import jlu.nan.pojo.GoodsES;
import jlu.nan.pojo.SearchPageVO;
import jlu.nan.pojo.SearchRequest;
import jlu.nan.service.SearchService;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: SearchServiceImpl
 * @Description:
 * @author: Nan
 * @date: 2020/4/11 11:54
 * @version: V1.0
 */

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    GoodsRepository goodsRepository;


    /*
    *@Author : Nan
    *@Description :
    *@Date : 12:46 2020/4/11
    *@Param : [goodsVO]
    *@return : jlu.nan.pojo.GoodsES
    *@Desc : 数据转换,完成库数据的准备
    */
    @Override
    public GoodsES buildGoodsES(GoodsVO goodsVO)  {
        GoodsES goodsES = new GoodsES();

        goodsES.setId(goodsVO.getId());
        goodsES.setBasePrice(goodsVO.getBasePrice());
        goodsES.setImgUrl(goodsVO.getImgUrl());
        goodsES.setTitle(goodsVO.getName());
        goodsES.setLastUpdateTime(goodsVO.getLastUpdateTime());
        goodsES.setAllSearch(goodsVO.getCategoryPath()+" "+goodsVO.getBrand()+" "+goodsVO.getName());
        goodsES.setBrand(goodsVO.getBrand());
        goodsES.setCategory(StringUtils.substringAfterLast(goodsVO.getCategoryPath(),"/"));

        SpuDetail goodsDetail = goodsClient.findGoodsDetail(goodsVO.getId());
        Map<String,String> genericSpec = JsonUtils.toMap(goodsDetail.getGenericSpec(),String.class,String.class);
        Map<String, List<String>> specialSpec = JsonUtils.nativeRead(goodsDetail.getSpecialSpec(),
                                                                    new TypeReference<Map<String, List<String>>>() {});
        Map<String,Object>Specs=new HashMap<>();

        List<SpecParam> specById = goodsClient.findSpecById(null, goodsVO.getCid3(), true);
        specById.stream().filter(s -> genericSpec.keySet().contains(s.getId().toString()) ||
                                      specialSpec.keySet().contains(s.getId().toString())).map(p ->{
                    Object v="";
                    if (p.getIsGeneric()){
                        v=genericSpec.get(p.getId().toString());
                        System.out.println(v);
                    }else{
                        v=specialSpec.get(p.getId().toString());
                        System.out.println(v);
                    }
                    Specs.put(p.getName(),v);
                    return null;
                }).count();
        goodsES.setSpecs(Specs);
        return goodsES;
    }

    /*
    *@Author : Nan
    *@Description :
    *@Date : 14:39 2020/4/11
    *@Param : []
    *@return : void
    *@Desc : 加载商品到索引库
    */
    @Override
    public void loadGoodsES(){
        int page=1;
        int rows=50;
        int size=0;
        do {
            PageResult<GoodsVO> goods = goodsClient.findGoodsList(page,rows,true,null);
            List<GoodsES> goodsESList = goods.getItems().stream().map(goodsVO -> {
                GoodsES goodsES = this.buildGoodsES(goodsVO);
                return goodsES;
            }).collect(Collectors.toList());
            goodsRepository.saveAll(goodsESList);

            page++;
            size=goodsESList.size();
        }while (size==50);
    }

    /*
    *@Author : Nan
    *@Description :
    *@Date : 22:06 2020/4/23
    *@Param : [searchRequest]
    *@return : jlu.nan.pojo.SearchPageVO
    *@Desc : 搜索方法
    */
    @Override
    public SearchPageVO search(SearchRequest searchRequest){
        if(StringUtils.isBlank(searchRequest.getKey())){
            return null;
        }
        // 使用原生查询构造器
        NativeSearchQueryBuilder qb = new NativeSearchQueryBuilder();
        // 添加结果过滤
        qb.withSourceFilter( new FetchSourceFilter(new String[]{"id","title","imgUrl","basePrice"},null));
        //添加分页
        qb.withPageable(PageRequest.of(searchRequest.getPage()-1,searchRequest.getDEFAULT_ROWS()));

        //添加查询匹配
        QueryBuilder baseSearch = QueryBuilders.matchQuery("allSearch", searchRequest.getKey());

        //处理规格条件
        qb.withQuery(this.boolSearch(baseSearch, searchRequest.getFilter()));

        //        添加聚合
        qb.addAggregation(AggregationBuilders.terms("aggBrand").field("brand"));
        qb.addAggregation(AggregationBuilders.terms("aggCategory").field("category"));

        //发起查询
        AggregatedPage<GoodsES> result = (AggregatedPage<GoodsES>) goodsRepository.search(qb.build());
        //        解析结果
        List<String> brands = this.getListByAgg(result, "aggBrand");
        List<String> categorys = this.getListByAgg(result, "aggCategory");

        List<Map<String,Object>> specs=null;
        if(!CollectionUtils.isEmpty(categorys)&&categorys.size()==1){
            specs=this.getSpecAgg(baseSearch,categorys.get(0));
        }

        if (!CollectionUtils.isEmpty(result.getContent())){
           return new  SearchPageVO(result.getContent(),result.getTotalElements(),result.getTotalPages(),brands,categorys,specs);
        }else{
            throw new CmException(ExectionEnum.GOODS_NO_FIND);
        }
    }

    /*
    *@Author : Nan
    *@Description :
    *@Date : 22:08 2020/4/23
    *@Param : [id]
    *@return : void
    *@Desc : 处理消息队列
    */
    @Override
    public void save(Long id) throws IOException {
        GoodsVO goods = goodsClient.findGoods(id);
        GoodsES goodsES = this.buildGoodsES(goods);
        this.goodsRepository.save(goodsES);
    }

    @Override
    public void del(Long id) {
        goodsRepository.deleteById(id);
    }


    /*
   根据 条件查询(用户选择) 后 返回搜索器
    */
    private BoolQueryBuilder boolSearch(QueryBuilder baseSearch,Map<String,Object> filter) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(baseSearch);

        if(!CollectionUtils.isEmpty(filter)){
            filter.entrySet().stream().forEach(entry->{
                String key ="";
                if(StringUtils.equals(entry.getKey(),"分类")){
                    key="category";
                }else if(StringUtils.equals(entry.getKey(),"品牌")){
                    key="brand";
                }else{
                    key = "specs."+entry.getKey()+".keyword";
                }
                boolQueryBuilder.filter(QueryBuilders.termQuery(key,entry.getValue()));
            });
        }
        return boolQueryBuilder;
    }


    /*
    根据 基础查询(用户输入) 后 通过指定分类查询所有可搜索spec
     */
    private List<Map<String,Object>> getSpecAgg(QueryBuilder baseSearch, String searchCategory) {
        NativeSearchQueryBuilder qb = new NativeSearchQueryBuilder();
        //字段返回过滤,只要聚合 其他都不要
        qb.withSourceFilter(new FetchSourceFilter(new String[]{""},null));
        //添加基础查询
        qb.withQuery(baseSearch);
        // 获取Specs的key值
       this.getSpecParam(searchCategory).stream().map(key->{
           //添加聚合
            qb.addAggregation(AggregationBuilders.terms(key).field("specs."+key+".keyword"));
            return null;
        }).count();
        //发起查询
        AggregatedPage<GoodsES> result = (AggregatedPage<GoodsES>) goodsRepository.search(qb.build());

        return this.getSpecParam(searchCategory).stream().map(key->{
            Map<String,Object> map=new HashMap<>();
            map.put("k",key);
            map.put("options",this.getListByAgg(result, key));
            return map;
        }).collect(Collectors.toList());
    }

    /*
     提供 聚合类 和聚合名 返回字符串集合
     */
    private List<String>getListByAgg(AggregatedPage<GoodsES> result,String aggName){
        Terms obj= result.getAggregations().get(aggName);
        return obj.getBuckets().stream().map(bucket->{
            return  bucket.getKeyAsString();
        }).collect(Collectors.toList());
    }

    /*
    通过分类名查询索引库中对应分类含有的 specParam的属性key
     */
    private List<String>getSpecParam(String searchCategory){
        ArrayList<String> result = new ArrayList<>();
        HashSet<String> filterKeys = new HashSet<>();
        NativeSearchQueryBuilder qb = new NativeSearchQueryBuilder();
        qb.withSourceFilter(new FetchSourceFilter(new String[]{"specs"},null));

        qb.withQuery(QueryBuilders.matchQuery("category",searchCategory));

        Page<GoodsES> search = goodsRepository.search(qb.build());

        search.stream().map(GoodsES::getSpecs).collect(Collectors.toList())
                .stream().map(spec -> { filterKeys.addAll(spec.keySet()) ;
            return null;
        }).count();

        result.addAll(filterKeys);
        return result;
    }
}
