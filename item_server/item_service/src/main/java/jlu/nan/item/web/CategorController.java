package jlu.nan.item.web;

import jlu.nan.common.vo.ExceptionResult;
import jlu.nan.item.pojo.Category;
import jlu.nan.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: CategorController
 * @Description:
 * @author: Nan
 * @date: 2020/3/18 18:58
 * @version: V1.0
 */

@RestController
@RequestMapping("category")
public class CategorController {

    @Autowired
    private CategoryService service;

    @GetMapping("list/{id}")
    public ResponseEntity<List<Category>> findFamily(@PathVariable("id")Integer id){
        List<Category> categoriesFamily = service.selFamily(id);
        return ResponseEntity.ok(categoriesFamily);
    }

    @GetMapping("list")
    public ResponseEntity<List<Category>> findTop(@RequestParam("pid")Integer pid){
        List<Category> fir_categories = service.selAllForParent(pid);

        return ResponseEntity.ok(fir_categories);
    }

    @GetMapping("hasChild")
    public  ResponseEntity<ExceptionResult>checkChild(@RequestParam("pid") Integer pid){
        ExceptionResult result = new ExceptionResult();
        result.setIsResult(service.hasChild(pid));

        return ResponseEntity.ok(result);
    }

    @PostMapping("add")
    public ResponseEntity<Integer> addChild(Category node){
        node.setId(null);
        return ResponseEntity.ok(service.addCategory(node));
    }

    @PutMapping("update")
    public ResponseEntity<Integer> updateCategory(Integer id,String name){
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        return ResponseEntity.ok(service.updateCategory(category));
    }

    @DeleteMapping("delete")
    public ResponseEntity<Integer> delectCategory(Integer id){
        return ResponseEntity.ok(service.delCatrgory(id));
    }
}


