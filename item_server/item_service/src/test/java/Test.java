import jlu.nan.ItemApplication;
import jlu.nan.item.pojo.Category;
import jlu.nan.item.service.CategoryService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName: Test
 * @Description:
 * @author: Nan
 * @date: 2020/3/21 13:46
 * @version: V1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ItemApplication.class)
public class Test {

    @Autowired
    CategoryService service;

    @org.junit.Test
    public void test(){
        Category category = new Category();
        category.setId(null);
        category.setName("测试");
        category.setParentId(2);
        category.setIsParent(false);
        category.setSort(99);
        Integer integer = service.addCategory(category);
        System.out.println(integer);
    }

}
