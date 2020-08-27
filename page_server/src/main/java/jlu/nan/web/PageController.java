package jlu.nan.web;

import jlu.nan.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

/**
 * @ClassName: PageController
 * @Description:
 * @author: Nan
 * @date: 2020/4/15 20:48
 * @version: V1.0
 */

@Controller
public class PageController {

    @Autowired
    private PageService service;

    @GetMapping("item/{id}.html")
    public String toItemPage(@PathVariable(name = "id")Long id, Model model){
        Map<String,Object>map=service.loadMap(id);
        model.addAllAttributes(map);

        service.createHtml(id,map);

        return "item";
    }
}
