package jlu.nan.item.ClientAPI;

import jlu.nan.item.pojo.Category;
import jlu.nan.item.pojo.SpecGroup;
import jlu.nan.item.pojo.SpecParam;
import jlu.nan.item.pojo.SpuDetail;
import jlu.nan.item.vo.GoodsVO;
import jlu.nan.item.vo.SkuVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName: DetailApi
 * @Description:
 * @author: Nan
 * @date: 2020/4/10 18:57
 * @version: V1.0
 */

public interface DetailApi {
    @GetMapping("/category/list/{id}")
    List<Category> findFamily(@PathVariable("id")Integer id);

    @GetMapping("/goods/{Id}")
     GoodsVO findGoods(@PathVariable(name = "Id")Long id);

    @GetMapping("/goods/spu/detail/{gid}")
     SpuDetail findGoodsDetail(@PathVariable(name = "gid")Long gid);

    @GetMapping("/goods/sku/{gid}")
    List<SkuVo>findSku(@PathVariable(name = "gid")Long skuGid);

    @GetMapping("/spec/group/{cid}")
    List<SpecGroup>findSpecById(@PathVariable("cid")Integer cid);

    @GetMapping("/spec/param/{cid}")
    List<SpecParam>findSimpleSpecById(@RequestParam(value = "cid" ,required = false)Integer cid);
}
