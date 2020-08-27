package jlu.nan.item.service.impl;

import jlu.nan.common.enums.ExectionEnum;
import jlu.nan.common.exception.CmException;
import jlu.nan.item.mapper.CategoryMapper;
import jlu.nan.item.pojo.Category;
import jlu.nan.item.service.CategoryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: CategoryServiceImpl
 * @Description:
 * @author: Nan
 * @date: 2020/3/18 18:41
 * @version: V1.0
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Transactional
    @Override
    public Integer addCategory(Category category) {
        Category becomeParent = new Category();
        becomeParent.setId(category.getParentId());
        becomeParent.setIsParent(true);

       this.updateCategory(becomeParent);

        categoryMapper.insertSelective(category);
        return category.getId();
    }

    @Transactional
    @Override
    public Integer updateCategory(Category category) {
       return  categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Transactional
    @Override
    public Integer delCatrgory(Integer id) {
        return categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Category> selAllForParent(Integer parentId) {
        Category topParent =new Category();
        topParent.setParentId(parentId);

        List<Category> result = categoryMapper.select(topParent);

        if(result==null){
            throw new CmException(ExectionEnum.CATEGORY_NOT_FIND);
        }

        return result;
    }

    @Override
    public Boolean hasChild(Integer parentId) {
        Category parent =new Category();
        parent.setParentId(parentId);

        int i = categoryMapper.selectCount(parent);

        if (i>0){
            return true;
        }else{
            return false;
        }
    }


    public String getCategoryPath(List<Integer> cids){
        //            查询分类名
        List<Category> categoryList = categoryMapper.selectByIdList(cids);
        if (categoryList.size()!=3){
            throw new CmException(ExectionEnum.CATEGORY_NOT_FIND);
        }
        List<String> categories=categoryList.stream().map(Category::getName).collect(Collectors.toList());
        return StringUtils.join(categories,"/");
    }


    public List<Category> selFamily(Integer id){
        List<Category>list=new ArrayList<>();
        Category category = categoryMapper.selectByPrimaryKey(id);
        if(category==null){
            throw new CmException(ExectionEnum.CATEGORY_NOT_FIND);
        }

        do {
            list.add(category);
            category = categoryMapper.selectByPrimaryKey(category.getParentId());
            if(category==null){
                throw new CmException(ExectionEnum.CATEGORY_NOT_FIND);
            }
        }while (category.getParentId()!=0);
        list.add(category);
        Collections.reverse(list);
        return list;
    }

}
