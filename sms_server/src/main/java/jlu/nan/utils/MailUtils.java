package jlu.nan.utils;

import jlu.nan.config.MailConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: MailUtils
 * @Description:
 * @author: Nan
 * @date: 2020/4/24 20:51
 * @version: V1.0
 */

@Component
@EnableConfigurationProperties(MailConfig.class)
public class MailUtils {

    @Autowired
    private MailConfig config;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private JavaMailSender mailSender;

    private String CUSTOMER_KEY="sms.tel:";
    private Long SMS_MIN_TIME=60000L;

    public String createCode(){
        String code="";
        for(int i=0;i<5;i++){
            int random=(int)(Math.random()*10);

            code+=random+"";
        }
        return code;
    }

    public String  mail(String to) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        String code=this.createCode();
        config.setCode(code);
        Long lastTime=redisTemplate.opsForValue().getOperations().getExpire(CUSTOMER_KEY+to);

        if(lastTime>0){
            if(lastTime>9*60){
                return null;
            }
        }


        mailMessage.setFrom(config.getFrom());//发送者
        mailMessage.setTo(to);//接收者
        mailMessage.setSubject(config.getTITLE_TEMPLATE());//标题
        mailMessage.setText(config.getCENTEXT_TEMPLATE());//内容
        try {
            mailSender.send(mailMessage);

            redisTemplate.opsForValue().set(CUSTOMER_KEY+to,code,10, TimeUnit.MINUTES);

            return "发送成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return "发送失败！/" + e;
        }
    }

}
