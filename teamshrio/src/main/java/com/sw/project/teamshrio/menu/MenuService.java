package com.sw.project.teamshrio.menu;

import com.alibaba.fastjson.JSONArray;
import com.sw.project.teamshrio.common.TeamShiroConstant;
import com.sw.project.teamshrio.util.DataRelationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @param userId 用户编号(后期权限用到)
     * @return
     */
      public String queryMenus(Long userId,Long parentId){
          //TODO 查询当前用户的角色信息
          List<MenuModel> menuModels=menuModelMapper.queryMenus(null,parentId);
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
     * 获取工具栏功能菜单
     * @param userId
     * @param parentId
     * @return
     */
      public  String getFuncMenu(Long userId,Long parentId){
          List<MenuModel> menuModels=menuModelMapper.queryMenus(null,parentId);
          String mennus=DataRelationUtil.createMenus(menuModels,parentId);
          return mennus;
      }

}
