package com.sw.project.teamshrio.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuController {
    @RequestMapping("/getMenusTree")
    public String getMenus(){
        //获取当前session

        return "[{id:'1','text':'测试'}]";
    }

}
