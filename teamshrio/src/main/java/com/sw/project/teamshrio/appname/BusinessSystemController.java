package com.sw.project.teamshrio.appname;

import com.sw.project.teamshrio.framework.spring.BaseController;
import com.sw.project.teamshrio.util.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    BusinessSystemService businessSystemService;

    /**
     * 主界面跳转
     * @param request
     * @return
     */
    @RequestMapping("/main")
    @Override
    public String getPath(HttpServletRequest request) {
        return forward("appname/main");
    }

    /**
     * 分页信息
     * @param pageModel
     * @param businessSystemModel
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public String queryPageBusinessSystems(PageModel pageModel, BusinessSystemModel businessSystemModel){
        pageModel=businessSystemService.queryPageBusinessSystem(pageModel,businessSystemModel);
        return jsonStrDataForPage(pageModel);
    }

    /**
     * 明细信息
     * @param id
     * @return
     */
    @RequestMapping("/getAppnameInfoId")
    @ResponseBody
    public  String getAppnameInfoById(Long id){
        BusinessSystemModel businessSystemModel=businessSystemService.getAppnameInfoById(id);
        return jsonStrData(businessSystemModel);
    }
    @RequestMapping("/saveOrUpdateAppname")
    @ResponseBody
    public String saveOrUpdateAppname(BusinessSystemModel businessSystemModel){
        try {

            if (null != businessSystemModel.getId()) {//执行update
                businessSystemService.updateBusinessSystem(businessSystemModel);
            } else {//执行insert
                businessSystemService.insertBusinessSystem(businessSystemModel);
            }
            return jsonStrAndState(null,true,"操作数据成功");
        }catch (Exception e){

        }
        return jsonStrAndState(null,false,"操作数据失败");
    }

    @RequestMapping("/deleteAppname")
    @ResponseBody
    public String deleteAppname(Long id){
        try {
            businessSystemService.deleteBusinessSystemById(id);
            return jsonStrAndState(null,true,"操作数据成功");
        }catch (Exception e){

        }
        return jsonStrAndState(null,false,"操作数据失败");
    }

}
