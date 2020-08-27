package jlu.nan.service;



import java.util.Map;


public interface PageService {
    Map<String,Object> loadMap(Long id);

    void createHtml(Long id,Map<String, Object>data);

    void delHtml(Long id);
}
