package jlu.nan.item.web;

import jlu.nan.item.pojo.SpecParam;
import jlu.nan.item.service.SpecParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: SpecParamController
 * @Description:
 * @author: Nan
 * @date: 2020/3/28 13:54
 * @version: V1.0
 */

@Controller
@RequestMapping("spec")
public class SpecParamController {

    @Autowired
    private SpecParamService service;


    @GetMapping("param")
    public ResponseEntity<List<SpecParam>> findSpecById(@RequestParam(value = "gid",required = false)Integer gid ,
                                                        @RequestParam(value = "cid" ,required = false)Integer cid,
                                                        @RequestParam(value = "searching",required = false)Boolean searching
    ){
        return ResponseEntity.ok(service.findSpecParam(gid,cid,searching));
    }

    @PostMapping("param/add")
    public ResponseEntity<Integer>addSpecParam(SpecParam specParam){

        return ResponseEntity.ok(service.addSpecParam(specParam));
    }

    @PutMapping("param/update")
    public ResponseEntity<Void>updateSpecParam(SpecParam specParam){
        service.updateSpecParam(specParam);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("param/del/{id}")
    public ResponseEntity<Void>updateSpecParam(@PathVariable("id") Integer id){
        service.delSpecParam(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @GetMapping("param/{cid}")
    public ResponseEntity<List<SpecParam>> findSimpleSpecById(@RequestParam(value = "cid" ,required = false)Integer cid){
        return ResponseEntity.ok(service.findSpecParam(null,cid,null));
    }
}
