package jlu.nan.item.web;

import jlu.nan.common.vo.PageResult;
import jlu.nan.item.pojo.Sku;
import jlu.nan.item.pojo.SpuDetail;
import jlu.nan.item.service.GoodsService;
import jlu.nan.item.vo.GoodsDetailVO;
import jlu.nan.item.vo.GoodsVO;
import jlu.nan.item.vo.SkuVo;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: GoodsController
 * @Description:
 * @author: Nan
 * @date: 2020/3/30 13:11
 * @version: V1.0
 */

@Controller
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private GoodsService service;

    @GetMapping("/{Id}")
    public ResponseEntity<GoodsVO>findGoods(@PathVariable(name = "Id")Long id){
        return ResponseEntity.ok(service.findGoods(id));
    }



    @GetMapping("/list")
    public ResponseEntity<PageResult<GoodsVO>>findGoodsList(
            @RequestParam(name = "page" ,defaultValue = "1") Integer page,
            @RequestParam(name = "rows" ,defaultValue = "5") Integer rows,
            @RequestParam(name = "saleable",required = false) Boolean saleable,
            @RequestParam(name = "key" ,required = false) String key
    ){
         return ResponseEntity.ok(service.findGoods(page,rows,saleable,key));
    }

    @GetMapping("/spu/detail/{gid}")
    public ResponseEntity<SpuDetail>findGoodsDetail(@PathVariable(name = "gid")Long gid){
        return ResponseEntity.ok(service.findGoodsDetail(gid));
    }

    @GetMapping("/sku/{gid}")
    public ResponseEntity<List<SkuVo>>findSku(@PathVariable(name = "gid")Long skuGid){
        return ResponseEntity.ok(service.findSkuWithStock(skuGid));
    }

    @PostMapping
    public ResponseEntity<Void> saveGoodsWhitSpuAndSku(@RequestBody GoodsDetailVO goodsDetail){
        service.saveGoodsWhitSpuAndSku(goodsDetail);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/sku/add")
    public ResponseEntity<Void> saveSkus(@RequestBody List<Sku> skus){
        service.saveSkus(skus);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/sku/dels")
    public ResponseEntity<Void> delSkus(@PathVariable List<Long> cancelId){
        service.delSkus(cancelId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping
    public ResponseEntity<Void> upDateGoodsWhitSpuAndSku(@RequestBody GoodsDetailVO goodsDetail){
        service.upDateGoodsWhitSpuAndSku(goodsDetail);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/sku/del/{skuId}")
    public ResponseEntity<Void>delSku(@PathVariable(name = "skuId")Long skuId){
        service.delSku(skuId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{skuId}")
    public ResponseEntity<Void>delGoods(@PathVariable(name = "skuId")Long id){
        service.delGoods(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/upper/{id}")
    public ResponseEntity<Void>upperGoods(@PathVariable(name = "id")Long id){
        service.upperGoods(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/lower/{id}")
    public ResponseEntity<Void>lowerGoods(@PathVariable(name = "id")Long id){
        service.lowerGoods(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
