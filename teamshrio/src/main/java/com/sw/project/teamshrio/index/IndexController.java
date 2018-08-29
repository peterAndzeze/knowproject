package com.sw.project.teamshrio.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/login")
    public String login(){
        return "main";
    }


}
