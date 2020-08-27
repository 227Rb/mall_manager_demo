package jlu.nan.common.vo;

import lombok.Data;

import java.util.List;

/**
 * @ClassName: PageResult
 * @Description:
 * @author: Nan
 * @date: 2020/3/23 23:08
 * @version: V1.0
 */

@Data
public class PageResult<T> {

    private List<T> items;
    private Long totalCount;
    private Integer totalPage;

    public PageResult() {
    }

    public PageResult(List<T> items, Long totalCount) {
        this.items = items;
        this.totalCount = totalCount;
    }

    public PageResult(List<T> items, Long totalCount, Integer totalPage) {
        this.items = items;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
    }
}
