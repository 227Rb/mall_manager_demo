package jlu.nan.item.service;

import jlu.nan.item.pojo.SpecGroup;

import java.util.List;

public interface SpecGroupService {
    List<SpecGroup> findSpecGroupByCID(Integer cid);

    Integer addSpecGroup(SpecGroup specGroup);

    Integer updateSpecGroup(SpecGroup specGroup);

    Integer delSpecGroup(Integer id);
}
