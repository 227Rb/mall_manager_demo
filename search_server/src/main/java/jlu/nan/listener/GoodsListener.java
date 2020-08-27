package jlu.nan.listener;

import jlu.nan.service.SearchService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @ClassName: GoodsListener
 * @Description:
 * @author: Nan
 * @date: 2020/4/23 21:59
 * @version: V1.0
 */

@Component
public class GoodsListener {
    @Autowired
    private SearchService service;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "CM.SEARCH.SAVE.QUEUE",durable = "true"),
            exchange = @Exchange(value = "CM.ITEM.EXCHANGE",ignoreDeclarationExceptions = "true",type = ExchangeTypes.TOPIC),
            key = {"item.save","item.update"}
    ))
    public void save(Long id) throws IOException {
        if(id==null){
            return;
        }
        service.save(id);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "CM.SEARCH.DEL.QUEUE",durable = "true"),
            exchange = @Exchange(value = "CM.ITEM.EXCHANGE",ignoreDeclarationExceptions = "true",type = ExchangeTypes.TOPIC),
            key = {"item.del"}
    ))
    public void del(Long id) throws IOException {
        if(id==null){
            return;
        }
        service.del(id);
    }
}
