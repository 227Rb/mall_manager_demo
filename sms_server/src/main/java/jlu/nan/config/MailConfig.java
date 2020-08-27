package jlu.nan.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName: MailConfig
 * @Description:
 * @author: Nan
 * @date: 2020/4/24 20:53
 * @version: V1.0
 */

@Data
@ConfigurationProperties(prefix = "cm.mail")
public class MailConfig {

    private String from;

    private String TITLE_TEMPLATE="激活邮件";

    private String code;

    private String CENTEXT_TEMPLATE;

    public void setCode(String code) {
        this.code = code;
        this.CENTEXT_TEMPLATE="感谢您注册,激活码为: "+code+" 请在10分钟内完成激活。";
    }
}
