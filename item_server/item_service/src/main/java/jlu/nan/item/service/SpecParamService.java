package jlu.nan.item.service;

import jlu.nan.item.pojo.SpecParam;

import java.util.List;

public interface SpecParamService {

    Integer addSpecParam(SpecParam specParam);

    void updateSpecParam(SpecParam specParam);

    void delSpecParam(Integer id);

    List<SpecParam> findSpecParam(Integer gid, Integer cid, Boolean searching);
}
