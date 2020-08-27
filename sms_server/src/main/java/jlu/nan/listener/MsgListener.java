package jlu.nan.listener;

import jlu.nan.utils.MailUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * @ClassName: MsgListener
 * @Description:
 * @author: Nan
 * @date: 2020/4/24 20:42
 * @version: V1.0
 */

@Component
public class MsgListener {

    @Autowired
    private MailUtils mailUtils;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "CM.VERITY.CODE.QUEUE",durable = "true"),
            exchange = @Exchange(value = "CM.SMS.EXCHANGE",ignoreDeclarationExceptions = "true",type = ExchangeTypes.TOPIC),
            key = {"sms.verity.code"}
    ))
    public void del(Map<String,String>msg) {
        if(CollectionUtils.isEmpty(msg)){
            return;
        }

        if(StringUtils.isEmpty(msg.get("tel"))){
            return;
        }

        mailUtils.mail(msg.get("tel"));
    }



}
