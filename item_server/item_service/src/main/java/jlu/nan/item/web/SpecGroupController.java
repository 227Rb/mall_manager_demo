package jlu.nan.item.web;

import jlu.nan.item.pojo.SpecGroup;
import jlu.nan.item.service.SpecGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: SpecController
 * @Description:
 * @author: Nan
 * @date: 2020/3/27 15:03
 * @version: V1.0
 */

@Controller
@RequestMapping("spec")
public class SpecGroupController {

    @Autowired
    private SpecGroupService service;


    @GetMapping("group/{cid}")
    public ResponseEntity<List<SpecGroup>>findSpecById(@PathVariable("cid")Integer cid){
            return ResponseEntity.ok(service.findSpecGroupByCID(cid));
    }

    @PostMapping("group/add")
    public ResponseEntity<Integer>addSpecGroup(SpecGroup specGroup){
        return ResponseEntity.ok(service.addSpecGroup(specGroup));
    }

    @PutMapping("group/update")
    public ResponseEntity<Void>updateSpecGroup(SpecGroup specGroup){
        service.updateSpecGroup(specGroup);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("group/del")
    public ResponseEntity<Void>updateSpecGroup(Integer id){
        service.delSpecGroup(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
