package com.sw.project.teamshrio.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/menu")
public class MenuController {
    @RequestMapping("/getMenusTree")
    @ResponseBody
    public String getMenus(){
        //获取当前session

        return "[{id:'1','text':'测试'}]";
    }

}
