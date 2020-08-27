package jlu.nan.Business.web;


import jlu.nan.Business.service.BusinessService;
import jlu.nan.common.vo.PageResult;
import jlu.nan.pojo.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @ClassName: BusinessController
 * @Description:
 * @author: Nan
 * @date: 2020/3/22 12:32
 * @version: V1.0
 */

@RestController
@RequestMapping("business")
public class BusinessController {

    @Autowired
    private  BusinessService service;

    @DeleteMapping("del")
    public  ResponseEntity<Void>del(Integer id){
        service.delBusiness(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("addOrUpdate")
    public ResponseEntity<Void>addBusiness(Business business){
        service.addBusiness(business);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("addOrUpdate")
    public ResponseEntity<Void>upDateBusiness(Business business){
        service.updateBusiness(business);
        return ResponseEntity.status(HttpStatus.RESET_CONTENT).build();
    }



    @GetMapping("page")
   public ResponseEntity<PageResult<Business>> findPage(
            @RequestParam(name = "page" ,defaultValue = "1") Integer page,
            @RequestParam(name = "rows" ,defaultValue = "5") Integer rows,
            @RequestParam(name = "sortBy" ,required = false) String sortBy,
            @RequestParam(name = "descending" ,defaultValue = "false") boolean desc,
            @RequestParam(name = "key" ,required = false) String key){


        return ResponseEntity.ok(service.queryKeyByPage(page,rows,sortBy,desc,key));
   }

}
