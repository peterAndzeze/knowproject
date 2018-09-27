package com.sw.project.teamshrio.menu;

import com.sw.project.teamshrio.framework.spring.BaseController;
import com.sw.project.teamshrio.user.UserModel;
import com.sw.project.teamshrio.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Autowired
    UserService userService;
    @Autowired
    MenuService menuService;

    /**
     * 获取功能菜单树
     * @return
     */
    @RequestMapping("/getFuncMenu")
    @ResponseBody
    public String getFuncTree(Long parentId){
        //获取当前session　加上用户查询
        //当前是查询数据库
       String functionMenus= menuService.getFuncMenu(null,parentId);
        return jsonStrAndState(functionMenus, true, "查询成功");
    }

    @Override
    public String getPath(HttpServletRequest request) {
        return null;
    }
}
