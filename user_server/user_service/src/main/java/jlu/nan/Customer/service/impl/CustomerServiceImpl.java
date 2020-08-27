package jlu.nan.Customer.service.impl;

import jlu.nan.Customer.mapper.CustomerMapper;
import jlu.nan.Customer.service.CustomerService;
import jlu.nan.common.enums.ExectionEnum;
import jlu.nan.common.exception.CmException;
import jlu.nan.pojo.Customer;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: CustomerServiceImpl
 * @Description:
 * @author: Nan
 * @date: 2020/4/24 16:05
 * @version: V1.0
 */

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper mapper;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public Boolean checkData(String data, Integer type) {

        Customer customer = new Customer();
        switch (type){
            case 1:customer.setLoginName(data);
                    break;
            case 2:customer.setTel(data);
                    break;
            default:
                throw new CmException(ExectionEnum.INVALID_USER_DARA_TYPE);
        }


        int result = mapper.selectCount(customer);

        return result==0;
    }

    @Override
    public void sendCode(String tel) {
        Map<String,String>msg=new HashMap<>();
        msg.put("tel",tel);
        amqpTemplate.convertAndSend("CM.SMS.EXCHANGE","sms.verity.code",msg);
    }


}
