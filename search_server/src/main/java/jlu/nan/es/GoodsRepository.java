package jlu.nan.es;

import jlu.nan.pojo.GoodsES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @ClassName: GoodsRepository
 * @Description:
 * @author: Nan
 * @date: 2020/4/8 15:23
 * @version: V1.0
 */

public interface GoodsRepository  extends ElasticsearchRepository<GoodsES,Long> {
    List<GoodsES>findByBasePriceBetween(Long start, Long end);
}
