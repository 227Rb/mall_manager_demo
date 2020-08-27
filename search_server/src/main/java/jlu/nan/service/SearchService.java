package jlu.nan.service;

import jlu.nan.item.vo.GoodsVO;
import jlu.nan.pojo.GoodsES;
import jlu.nan.pojo.SearchRequest;
import jlu.nan.pojo.SearchPageVO;

import java.io.IOException;

/**
 * @ClassName: SearchService
 * @Description:
 * @author: Nan
 * @date: 2020/4/11 11:53
 * @version: V1.0
 */

public interface SearchService {

    GoodsES buildGoodsES(GoodsVO goodsVO);

    void loadGoodsES();

    SearchPageVO search(SearchRequest searchRequest);

    void save(Long id) throws IOException;

    void del(Long id);
}
