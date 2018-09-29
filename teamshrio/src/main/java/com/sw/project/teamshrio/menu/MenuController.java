package com.sw.project.teamshrio.menu;

import com.sw.project.teamshrio.framework.spring.BaseController;
import com.sw.project.teamshrio.user.UserModel;
import com.sw.project.teamshrio.user.UserService;
import com.sw.project.teamshrio.util.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Autowired
    UserService userService;
    @Autowired
    MenuService menuService;

    /**
     * 获取功能菜单
     * @return
     */
    @RequestMapping("/getFuncMenu")
    @ResponseBody
    public String getFuncMenu(Long parentId){
        //获取当前session　加上用户查询
        //当前是查询数据库
       String functionMenus= menuService.getFuncMenu(null,parentId);
       return jsonStrAndState(functionMenus, true, "查询成功");
    }




    /**
     *
     * @param parentId
     * @return
     */
    @RequestMapping("/getFuncTree")
    @ResponseBody
    public String getFuncTree(Long parentId){
       String functree= menuService.queryTree(null,parentId);
       return functree;
    }



    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public String updateMenu(MenuModel menuModel) {
        try {
            if (null == menuModel.getId()) {//新增
                menuService.addMenu(menuModel);
            } else {//修改
                menuService.updateMenu(menuModel);
            }
            return jsonStrAndState("", true, "操作成功");
        } catch (Exception e) {
            return jsonStrAndState("", false, e.getMessage());
        }
    }
    @RequestMapping("/menuInfo")
    @ResponseBody
    public String getMenuInfo(Long id){
        MenuModel menuModel=menuService.getMenuModelById(id);
        return jsonStrAndState(menuModel,true,"查询成功");
    }


    @RequestMapping("/getPages")
    @ResponseBody
    public String getMenuPage(PageModel pageModel, MenuModel param) {
        MenuModel menuModel=menuService.getMenuModelById(param.getId());
        List<MenuModel> menuModels=new ArrayList<MenuModel>();
        menuModels.add(menuModel);
        PageModel model=createPage(menuModels.size(), menuModels, 0, 0);
        return jsonStrData(model);
    }


    @Override
    @RequestMapping("/main")
    public String getPath(HttpServletRequest request) {
        return "menu/main";
    }
}
