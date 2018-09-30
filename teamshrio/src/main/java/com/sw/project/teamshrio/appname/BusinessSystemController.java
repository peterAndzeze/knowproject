package com.sw.project.teamshrio.appname;

import com.sw.project.teamshrio.framework.spring.BaseController;
import com.sw.project.teamshrio.util.PageModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 业务系统管理
 */
@Controller
@RequestMapping("/appname")
public class BusinessSystemController extends BaseController {
    @RequestMapping("/main")
    @Override
    public String getPath(HttpServletRequest request) {
        return "appname/main";
    }
    @RequestMapping("/page")
    @ResponseBody
    public String queryPageBusinessSystems(PageModel pageModel,BusinessSystemModel businessSystemModel){

    }


}
