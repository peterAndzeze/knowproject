package com.sw.project.teamshrio.admin;

import com.sw.project.teamshrio.framework.session.SessionPermission;
import com.sw.project.teamshrio.framework.session.SessionUser;
import com.sw.project.teamshrio.framework.session.SessionUtils;
import com.sw.project.teamshrio.user.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/admin/admin")
@Controller
public class AdminController {
    private Logger logger= LoggerFactory.getLogger(AdminController.class);

    @RequestMapping
    public String execute(HttpServletRequest request, Model model){
        SessionUser sessionUser= SessionUtils.getSessionUser(request);
        System.out.println("*************************************");
        // 设置登录用户名
        model.addAttribute("userName", sessionUser.getAccount());
        SessionPermission sessionPermission = SessionUtils.getSessionPermission(request);
        // 设置当前登录用户没有的权限，以便控制前台按钮的显示或者隐藏
        model.addAttribute("sessionUserNoPermissions", sessionPermission == null ? null : sessionPermission.getNoPermissions());
        // 默认首页
        // model.addAttribute("defaultPage", null);
        return "main";
    }



}
