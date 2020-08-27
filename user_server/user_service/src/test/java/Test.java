import jlu.nan.Business.service.BusinessService;
import jlu.nan.UserApplication;
import jlu.nan.pojo.Business;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ClassName: Test
 * @Description:
 * @author: Nan
 * @date: 2020/3/22 17:25
 * @version: V1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class Test {

    @Autowired
    BusinessService businessService;

    @org.junit.Test
    public void text(){

    }


}
