package jlu.nan.Business.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jlu.nan.Business.mapper.BusinessMapper;
import jlu.nan.Business.service.BusinessService;
import jlu.nan.common.enums.ExectionEnum;
import jlu.nan.common.exception.CmException;
import jlu.nan.common.vo.PageResult;
import jlu.nan.pojo.Business;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ClassName: BusinessServiceImpl
 * @Description:
 * @author: Nan
 * @date: 2020/3/22 12:33
 * @version: V1.0
 */

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessMapper mapper;

    @Transactional
    @Override
    public int addBusiness(Business business) {
        int result = mapper.insertSelective(business);
        if(result!=1){
            throw new CmException(ExectionEnum.BUSINESS_SAVE_ERROR);
        }
        return result;
    }

    @Transactional
    @Override
    public int updateBusiness(Business business) {
        int result = mapper.updateByPrimaryKeySelective(business);
        if(result!=1){
            throw new CmException(ExectionEnum.BUSINESS_UPDATE_ERROR);
        }
        return result;
    }

    @Transactional
    @Override
    public int delBusiness(Integer id) {
        int result = mapper.deleteByPrimaryKey(id);
        if(result!=1){
            throw new CmException(ExectionEnum.BUSINESS_DELETE_ERROR);
        }
        return result;
    }

    @Override
    public PageResult<Business> queryKeyByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
//       使用分页助手开启分页追加
        PageHelper.startPage(page,rows);

//       通过定义例子   使用条件查询
        Example example=new Example(Business.class);
//        判断时候有条件判断需求
        if (StringUtils.isNotBlank(key)){
            example.createCriteria().orLike("name","%"+key+"%")
                                    .orLike("tel","%"+key+"%")
                                    .orLike("role","%"+key+"%");
        }

//        追加排序子句
        if(StringUtils.isNotBlank(sortBy)){
            example.setOrderByClause(" "+sortBy+ (desc?"  DESC":"  ASC"));
        }

//        发起真正的查询
        List<Business> businesses = mapper.selectByExample(example);
        if (CollectionUtils.isEmpty(businesses)){
            throw new CmException(ExectionEnum.BUSINESS_NOT_FIND);
        }

//        解析结果取得数据 分页信息等
        PageInfo<Business> info= new PageInfo<>(businesses);
        return new PageResult<Business>(businesses,info.getTotal(),info.getPages());
    }
}
