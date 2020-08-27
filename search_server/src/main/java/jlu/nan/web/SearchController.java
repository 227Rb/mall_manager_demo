package jlu.nan.web;

import jlu.nan.common.vo.PageResult;
import jlu.nan.pojo.GoodsES;
import jlu.nan.pojo.SearchPageVO;
import jlu.nan.pojo.SearchRequest;
import jlu.nan.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: SearchController
 * @Description:
 * @author: Nan
 * @date: 2020/4/12 1:42
 * @version: V1.0
 */

@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private SearchService service;

    @PostMapping("page")
    public ResponseEntity<SearchPageVO>findGoodsEsPage(@RequestBody  SearchRequest searchRequest){
            return ResponseEntity.ok(service.search(searchRequest));
    }
}
