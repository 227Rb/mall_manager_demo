package jlu.nan.listener;

import jlu.nan.service.PageService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: GoodsListener
 * @Description:
 * @author: Nan
 * @date: 2020/4/23 21:42
 * @version: V1.0
 */

@Component
public class GoodsListener {

    @Autowired
    private PageService service;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "CM.PAGE.SAVE.QUEUE",durable = "true"),
            exchange = @Exchange(value = "CM.ITEM.EXCHANGE",ignoreDeclarationExceptions = "true",type = ExchangeTypes.TOPIC),
            key = {"item.save","item.update"}
    ))
    public void save(Long id){
        if(id==null){
            return;
        }
        service.createHtml(id,null);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "CM.PAGE.DEL.QUEUE",durable = "true"),
            exchange = @Exchange(value = "CM.ITEM.EXCHANGE",ignoreDeclarationExceptions = "true",type = ExchangeTypes.TOPIC),
            key = {"item.del"}
    ))
    public void del(Long id){
        if(id==null){
            return;
        }
        service.delHtml(id);
    }
}
