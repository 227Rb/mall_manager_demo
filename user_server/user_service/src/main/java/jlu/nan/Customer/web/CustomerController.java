package jlu.nan.Customer.web;

import jlu.nan.Customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: CustomerController
 * @Description:
 * @author: Nan
 * @date: 2020/4/24 16:03
 * @version: V1.0
 */

@Controller
public class CustomerController {

    @Autowired
    private CustomerService service;


    @GetMapping("/check/{data}/{type}")
    public ResponseEntity<Boolean>checkData(@PathVariable(name = "data")String data,
                                            @PathVariable(name = "type")Integer type){

        return ResponseEntity.ok(service.checkData(data,type));

    }

    @PostMapping("code")
    public ResponseEntity<Void>sendCode(@RequestParam("tel")String tel){
        service.sendCode(tel);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
