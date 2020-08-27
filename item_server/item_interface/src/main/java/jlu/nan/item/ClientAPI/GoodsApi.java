package jlu.nan.item.ClientAPI;

import jlu.nan.common.vo.PageResult;
import jlu.nan.item.pojo.SpecParam;
import jlu.nan.item.pojo.SpuDetail;
import jlu.nan.item.vo.GoodsVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface GoodsApi {

    @GetMapping("goods/{Id}")
   GoodsVO findGoods(@PathVariable(name = "Id")Long id);

    @GetMapping("goods/list")
    PageResult<GoodsVO> findGoodsList(
            @RequestParam(name = "page" ,defaultValue = "1") Integer page,
            @RequestParam(name = "rows" ,defaultValue = "5") Integer rows,
            @RequestParam(name = "saleable",required = false) Boolean saleable,
            @RequestParam(name = "key" ,required = false) String key
    );

    @GetMapping("goods/spu/detail/{gid}")
   SpuDetail findGoodsDetail(@PathVariable(name = "gid")Long gid);


    @GetMapping("spec/param")
    List<SpecParam> findSpecById(@RequestParam(value = "gid",required = false)Integer gid ,
                                 @RequestParam(value = "cid" ,required = false)Integer cid,
                                 @RequestParam(value = "searching",required = false)Boolean searching);
}
