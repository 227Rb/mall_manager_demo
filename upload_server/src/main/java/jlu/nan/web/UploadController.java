package jlu.nan.web;

import jlu.nan.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: UploadController
 * @Description:
 * @author: Nan
 * @date: 2020/3/25 22:24
 * @version: V1.0
 */

@Controller
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private UploadService service;

    @PostMapping("image")
    public ResponseEntity<String> uploadImg(@RequestParam(name = "file") MultipartFile file){
                return ResponseEntity.ok(service.uploadImg(file));
    }


}
