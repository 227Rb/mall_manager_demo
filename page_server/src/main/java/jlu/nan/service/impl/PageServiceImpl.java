package jlu.nan.service.impl;

import jlu.nan.client.DetailClient;
import jlu.nan.item.pojo.Category;
import jlu.nan.item.pojo.SpecGroup;
import jlu.nan.item.pojo.SpecParam;
import jlu.nan.item.pojo.SpuDetail;
import jlu.nan.item.vo.GoodsVO;
import jlu.nan.item.vo.SkuVo;
import jlu.nan.service.PageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: PageServiceImpl
 * @Description:
 * @author: Nan
 * @date: 2020/4/17 13:53
 * @version: V1.0
 */

@Slf4j
@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private DetailClient client;

    @Autowired
    private TemplateEngine engine;

    @Override
    public Map<String, Object> loadMap(Long id) {
        Map<String,Object>map=new HashMap<>();
        GoodsVO goods = client.findGoods(id);
        SpuDetail goodsDetail = client.findGoodsDetail(id);
        List<SkuVo> skus = client.findSku(id);

        List<Map<String, Object>> categorys = client.findFamily(goods.getCid3()).stream().map(category -> {
            Map<String, Object> categoryMap = new HashMap<>();
            categoryMap.put("key", category.getId());
            categoryMap.put("name", category.getName());
            return categoryMap;
        }).collect(Collectors.toList());


        Map<Integer,Map<String, Object>> groups=new HashMap<>();

        client.findSpecById(goods.getCid3()).stream().map(group -> {
            Map<String, Object> groupMap = new HashMap<>();
            groupMap.put("name", group.getName());
            groupMap.put("params", new ArrayList<Map<String,Object>>());
            groups.put( group.getId(),groupMap);
            return null;
        }).count();

        Map<Integer,Object>specialSpecParams=new HashMap<>();

        client.findSimpleSpecById(goods.getCid3()).stream().map(param -> {
            if(param.getIsGeneric()){
                Map<String,Object>genericSpecParam=new HashMap<>();
                genericSpecParam.put("id",param.getId());
                genericSpecParam.put("name",param.getName());
                List<Map<String,Object>> params = (List<Map<String, Object>>) groups.get(param.getGid()).get("params");
                params.add(genericSpecParam);
            }else{
                specialSpecParams.put(param.getId(),param.getName());
            }
            return null;
        }).count();




        map.put("brand",goods.getBrand());
        map.put("detail",goodsDetail);
        map.put("skus",skus);
        map.put("categories",categorys);
        map.put("groups",groups);
        map.put("specialSpecParams",specialSpecParams);
        return map;
    }


    public void createHtml(Long id,Map<String, Object>data){

        if(CollectionUtils.isEmpty(data)){
            data=this.loadMap(id);
        }

        Context context = new Context();
        context.setVariables(data);

        File file = new File("D:\\CodingTool\\nginx-1.17.10\\html\\item\\"+id+".html");

        try(PrintWriter writer=new PrintWriter(file,"utf-8")){
            engine.process("item",context,writer);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[静态服务_静态页面生成异常]",e);
        }

    }

    @Override
    public void delHtml(Long id) {
        File file = new File("D:\\CodingTool\\nginx-1.17.10\\html\\item\\"+id+".html");
        file.deleteOnExit();
    }

}
