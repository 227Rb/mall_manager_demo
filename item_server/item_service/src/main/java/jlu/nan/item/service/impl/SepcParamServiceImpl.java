package jlu.nan.item.service.impl;

import jlu.nan.common.enums.ExectionEnum;
import jlu.nan.common.exception.CmException;
import jlu.nan.item.mapper.SpecParamMapper;
import jlu.nan.item.pojo.SpecParam;
import jlu.nan.item.service.SpecParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @ClassName: SepcParamServiceImpl
 * @Description:
 * @author: Nan
 * @date: 2020/3/28 13:58
 * @version: V1.0
 */

@Service
public class SepcParamServiceImpl implements SpecParamService {

    @Autowired
    private SpecParamMapper mapper;

    @Transactional
    @Override
    public Integer addSpecParam(SpecParam specParam) {
        specParam.setId(null);
        mapper.insertSelective(specParam);
        return specParam.getId();
    }

    @Transactional
    @Override
    public void updateSpecParam(SpecParam specParam) {
        mapper.updateByPrimaryKeySelective(specParam);
    }

    @Transactional
    @Override
    public void delSpecParam(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<SpecParam> findSpecParam(Integer gid, Integer cid, Boolean searching) {
        SpecParam specParam = new SpecParam();
        specParam.setGid(gid);
        specParam.setCid(cid);
        specParam.setSearching(searching);
        List<SpecParam> params = mapper.select(specParam);

        if(CollectionUtils.isEmpty(params)){
            throw new CmException(ExectionEnum.FIND_SPEC_PARAM_ERROR);
        }

        return params;
    }
}
