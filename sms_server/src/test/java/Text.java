import jlu.nan.SmsApplication;
import jlu.nan.utils.MailUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName: Text
 * @Description:
 * @author: Nan
 * @date: 2020/4/24 20:58
 * @version: V1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SmsApplication.class)
public class Text {


    @Autowired
    MailUtils utils;

    @Test
    public void text(){
       utils.mail("1109066402@qq.com");
    }



}
