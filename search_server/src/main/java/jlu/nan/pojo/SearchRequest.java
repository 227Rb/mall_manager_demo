package jlu.nan.pojo;

import java.util.Map;

/**
 * @ClassName: SearchRequest
 * @Description:
 * @author: Nan
 * @date: 2020/4/12 1:54
 * @version: V1.0
 */

public class SearchRequest {
    private String key;
    private Integer page;
    private Map<String,Object> filter;
    private final int DEFAULT_ROWS=5;
    private final int DEFAULT_PAGE=1;

    public Map<String, Object> getFilter() {
        return filter;
    }

    public void setFilter(Map<String, Object> filter) {
        this.filter = filter;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getPage() {
        if (page == null){
            return  DEFAULT_PAGE;
        }
        return Math.max(page,DEFAULT_PAGE);
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getDEFAULT_ROWS() {
        return DEFAULT_ROWS;
    }


}
