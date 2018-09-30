package com.sw.project.teamshrio.menu;

import com.alibaba.fastjson.JSONArray;
import com.sw.project.teamshrio.common.TeamShiroConstant;
import com.sw.project.teamshrio.util.DataRelationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单服务
 */
@Service
public class MenuService {
    @Autowired
    private MenuModelMapper menuModelMapper;

    /**
     * 菜单信息
     *
     * @param userId 用户编号(后期权限用到)
     * @return
     */
    public String queryTree(Long userId, Long parentId) {
        //TODO 查询当前用户的角色信息
        List<MenuModel> menuModels = menuModelMapper.queryMenus(null, parentId);
        List<Map<String, Object>> tree = new ArrayList<Map<String, Object>>();
        for (MenuModel menuModel : menuModels) {
            Map<String, Object> node = new HashMap<String, Object>();
            node.put("id", menuModel.getId());
            node.put("text", menuModel.getMenuName());
            node.put("parentId", menuModel.getParentId());
            node.put("path", menuModel.getPath());
            if (menuModel.getLeaf().equals(TeamShiroConstant.IS_LEAF)) {
                node.put("leaf", true);
            } else {
                node.put("leaf", false);
            }
            node.put("path", menuModel.getPath());
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
    public MenuModel getMenuModelById(Long id) {
        return menuModelMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取工具栏功能菜单
     *
     * @param userId
     * @param parentId
     * @return
     */
    public String getFuncMenu(Long userId, Long parentId) {
        if(null==parentId){
            parentId=-1L;
        }
        List<MenuModel> menuModels = menuModelMapper.queryMenus(null, parentId);
        String mennus = DataRelationUtil.createMenus(menuModels, parentId);
        return mennus;
    }

    /**
     * 新增菜单
     *
     * @param menuModel
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public void addMenu(MenuModel menuModel) throws Exception {
        try {
            menuModel.setLeaf(TeamShiroConstant.IS_LEAF);
            menuModelMapper.insertSelective(menuModel);
            MenuModel parentModel = new MenuModel();
            parentModel.setId(menuModel.getParentId());
            parentModel.setLeaf(TeamShiroConstant.IS_NOT_LEAF);//
            menuModelMapper.updateByPrimaryKeySelective(parentModel);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("添加失败！！！");
        }
    }

    /**
     *　删除菜单
     * @param id
     */
    public void deleteMenuById(Long id,Long parentId,String delType) throws Exception {
        //查询出来当前上级菜单　如果只有这一个子节点，那么删除之后，该父节点变为子节点
        List<MenuModel> menuModels = menuModelMapper.queryMenus(null, parentId);
        MenuModel parentMenu=new MenuModel();
        parentMenu.setId(parentId);
        parentMenu.setLeaf(TeamShiroConstant.IS_LEAF);
        if(menuModels.size()==1){
            menuModelMapper.updateByPrimaryKeySelective(parentMenu);
        }
        menuModelMapper.deleteByPrimaryKey(id);
        if(null!=delType && delType.equals(TeamShiroConstant.DELTYPE)){
            menuModelMapper.deleteByParentId(parentId);
            menuModelMapper.updateByPrimaryKeySelective(parentMenu);
        }
    }

    /**
     * 修改菜单
     *
     * @param menuModel
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public void updateMenu(MenuModel menuModel) throws Exception {
        try {
            menuModelMapper.updateByPrimaryKeySelective(menuModel);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("【" + menuModel.getId() + "】修改失败！！！");
        }
    }

}
