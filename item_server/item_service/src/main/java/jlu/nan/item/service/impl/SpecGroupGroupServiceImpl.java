package jlu.nan.item.service.impl;

import jlu.nan.common.enums.ExectionEnum;
import jlu.nan.common.exception.CmException;
import jlu.nan.item.mapper.SpecGroupMapper;
import jlu.nan.item.pojo.SpecGroup;
import jlu.nan.item.service.SpecGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @ClassName: SpecServiceImpl
 * @Description:
 * @author: Nan
 * @date: 2020/3/27 15:09
 * @version: V1.0
 */

@Service
public class SpecGroupGroupServiceImpl implements SpecGroupService {

    @Autowired
    private SpecGroupMapper mapper;

    @Override
    public List<SpecGroup> findSpecGroupByCID(Integer cid) {
        SpecGroup group = new SpecGroup();
        group.setCid(cid);
        List<SpecGroup> groups = mapper.select(group);

        if (CollectionUtils.isEmpty(groups)){
            throw new CmException(ExectionEnum.FIND_SPEC_GROUP_ERROR);
        }

        return groups;
    }

    @Transactional
    @Override
    public Integer addSpecGroup(SpecGroup specGroup) {
        specGroup.setId(null);
        mapper.insertSelective(specGroup);

        return specGroup.getId();
    }

    @Transactional
    @Override
    public Integer updateSpecGroup(SpecGroup specGroup) {
        return mapper.updateByPrimaryKeySelective(specGroup);
    }

    @Transactional
    @Override
    public Integer delSpecGroup(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }


}
