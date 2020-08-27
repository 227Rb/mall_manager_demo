package jlu.nan.pojo;

import jlu.nan.common.vo.PageResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SearchPageVO
 * @Description:
 * @author: Nan
 * @date: 2020/4/12 19:01
 * @version: V1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchPageVO extends PageResult<GoodsES> {

    private List<String> brands;
    private List<String> categorys;
    private List<Map<String,Object>> specs;

    public SearchPageVO(List<GoodsES> items, Long totalCount, Integer totalPage, List<String> brands, List<String> categorys,List<Map<String,Object>> specs) {
        super(items, totalCount, totalPage);
        this.brands = brands;
        this.categorys = categorys;
        this.specs=specs;
    }
}
