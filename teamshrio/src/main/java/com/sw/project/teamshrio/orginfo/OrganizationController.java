package com.sw.project.teamshrio.orginfo;

import com.sw.project.teamshrio.framework.spring.BaseController;
import com.sw.project.teamshrio.user.UserService;
import com.sw.project.teamshrio.util.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 组织机构信息
 */
@RequestMapping("organzation")
@Controller
public class OrganizationController extends BaseController {
    @Autowired
    UserService userService;
    @Autowired
    OrganizationService organizationService;



    /**
     * @param parentId
     * @return
     */
    @RequestMapping("/getOrgTree")
    @ResponseBody
    public String getOrgTree(Long parentId) {
        String orgTree = organizationService.queryOrgTree(null, parentId);
        return orgTree;
    }


    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public String updateOrgInfo(OrgInfoModel orgInfoModel) {
        try {
            if (null == orgInfoModel.getId()) {//新增
                organizationService.addOrgInfo(orgInfoModel);
            } else {//修改
                organizationService.updateOrgInfo(orgInfoModel);
            }
            return jsonStrAndState("", true, "操作成功");
        } catch (Exception e) {
            return jsonStrAndState("", false, e.getMessage());
        }
    }

    @RequestMapping("/orgInfo")
    @ResponseBody
    public String getOrgInfo(Long id) {
        OrgInfoModel orgInfoModel = organizationService.getOrgInfoById(id);
        return jsonStrAndState(orgInfoModel, true, "查询成功");
    }


    @RequestMapping("/getPages")
    @ResponseBody
    public String getOrgInfoPage(PageModel pageModel, OrgInfoModel param) {
        OrgInfoModel orgInfoModel = organizationService.getOrgInfoById(param.getId());
        List<OrgInfoModel> childOrgInfos =organizationService.getOrgInfoByParentId(null,param.getId());
        childOrgInfos.add(orgInfoModel);
        PageModel model = createPage(childOrgInfos.size(), childOrgInfos, 0, 0);
        return jsonStrData(model);
    }

    @RequestMapping("/deleteOrgInfo")
    @ResponseBody
    public String deleteOrgInfoById(Long id,Long parentId,String delType) {
        try {
            organizationService.deleteOrgInfoById(id,parentId,delType);
            return jsonStrAndState(null,true,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonStrAndState(null,false,"删除失败");
    }

    @Override
    @RequestMapping("/main")
    public String getPath(HttpServletRequest request) {
        return forward("organization/main");
    }



}
