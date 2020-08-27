package jlu.nan.item.service;

import jlu.nan.item.pojo.Category;

import java.util.List;

public interface CategoryService {

    /*
    *@Author : Nan
    *@Description :加入分类
    *@Date : 18:35 2020/3/18
    *@Param : [category]
    *@return : void
    *@Desc :
    */
    Integer addCategory(Category category);


    Integer updateCategory(Category category);

    Integer delCatrgory(Integer id);

    List<Category> selAllForParent(Integer parentId);

    Boolean hasChild(Integer parentId);

    String getCategoryPath(List<Integer> cids);

    List<Category> selFamily(Integer id);
}
