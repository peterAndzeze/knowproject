package com.sw.project.teamshrio.orginfo;

import com.alibaba.fastjson.JSONArray;
import com.sw.project.teamshrio.common.TeamShiroConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 组织机构信息
 */
@Service
public class OrganizationService {
    @Autowired
    private OrgInfoModelMapper orgInfoModelMapper;
    /**
     * 菜单信息
     *
     * @param userId 用户编号(后期权限用到)
     * @return
     */
    public String queryOrgTree(Long userId, Long parentId) {
        //TODO 查询当前用户的角色信息
        List<OrgInfoModel> orgInfoModels = orgInfoModelMapper.getOrgInfosByParentId(null, parentId);
        List<Map<String, Object>> tree = new ArrayList<Map<String, Object>>();
        for (OrgInfoModel orgInfoModel : orgInfoModels) {
            Map<String, Object> node = new HashMap<String, Object>();
            node.put("id", orgInfoModel.getId());
            node.put("text", orgInfoModel.getOrgname());
            node.put("parentId", orgInfoModel.getParentId());
            if (orgInfoModel.getLeaf().equals(TeamShiroConstant.IS_LEAF)) {
                node.put("leaf", true);
            } else {
                node.put("leaf", false);
            }
            tree.add(node);
        }
        return JSONArray.toJSONString(tree);
    }

    /**
     * 根据主键获取
     *
     * @param id
     * @return
     */
    public OrgInfoModel getOrgInfoById(Long id) {
        return orgInfoModelMapper.selectByPrimaryKey(id);
    }


    /**
     * 子菜单信息
     * @param userId
     * @param parentId
     * @return
     */
    public  List<OrgInfoModel> getOrgInfoByParentId(Long userId,Long parentId){
        return  orgInfoModelMapper.getOrgInfosByParentId(userId, parentId);
    }


    /**
     * 新增菜单
     *
     * @param orgInfoModel
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public void addOrgInfo(OrgInfoModel orgInfoModel) throws Exception {
        try {
            orgInfoModel.setLeaf(TeamShiroConstant.IS_LEAF);
            orgInfoModelMapper.insertSelective(orgInfoModel);
            OrgInfoModel parentModel = new OrgInfoModel();
            parentModel.setId(orgInfoModel.getParentId());
            parentModel.setLeaf(TeamShiroConstant.IS_NOT_LEAF);//
            orgInfoModelMapper.updateByPrimaryKeySelective(parentModel);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("添加失败！！！");
        }
    }

    /**
     *　删除菜单
     * @param id
     */
    public void deleteOrgInfoById(Long id,Long parentId,String delType) throws Exception {
        //查询出来当前上级菜单　如果只有这一个子节点，那么删除之后，该父节点变为子节点
        List<OrgInfoModel> orgInfoModels= orgInfoModelMapper.getOrgInfosByParentId(null, parentId);
        OrgInfoModel parentOrgInfoModel=new OrgInfoModel();
        parentOrgInfoModel.setId(parentId);
        parentOrgInfoModel.setLeaf(TeamShiroConstant.IS_LEAF);
        if(orgInfoModels.size()==1){
            orgInfoModelMapper.updateByPrimaryKeySelective(parentOrgInfoModel);
        }
        orgInfoModelMapper.deleteByPrimaryKey(id);
        if(null!=delType && delType.equals(TeamShiroConstant.DELTYPE)){
            orgInfoModelMapper.deleteByParentId(parentId);
            orgInfoModelMapper.updateByPrimaryKeySelective(parentOrgInfoModel);
        }
    }

    /**
     * 修改菜单
     *
     * @param orgInfoModel
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public void updateOrgInfo(OrgInfoModel orgInfoModel) throws Exception {
        try {
            orgInfoModelMapper.updateByPrimaryKeySelective(orgInfoModel);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("【" + orgInfoModel.getId() + "】修改失败！！！");
        }
    }

}
