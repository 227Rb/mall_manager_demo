package jlu.nan.Business.service;


import jlu.nan.common.vo.PageResult;
import jlu.nan.pojo.Business;




public interface BusinessService {

    int addBusiness(Business business);

    int updateBusiness(Business business);

    int delBusiness(Integer id);

    PageResult<Business> queryKeyByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key);
}
