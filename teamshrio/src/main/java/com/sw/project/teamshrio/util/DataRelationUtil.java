package com.sw.project.teamshrio.util;

import com.alibaba.fastjson.JSONArray;
import com.sw.project.teamshrio.common.TeamShiroConstant;
import com.sw.project.teamshrio.menu.MenuModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 父子级关系处理
 */
public class DataRelationUtil {

    private static List<MenuModel> menus;

    public static String createMenus(List<MenuModel> menuModels, Long parentId) {// 一级自定义节点0
        menus = new ArrayList<MenuModel>();
        Iterator<MenuModel> iterator = menuModels.iterator();
        MenuModel menuModel = null;
        if (null == parentId) {
            parentId = TeamShiroConstant.PARENT_ID;
        }
        while (iterator.hasNext()) {
            menuModel = iterator.next();
            if (menuModel.getParentId().equals(parentId)) {// 二级节点
                menus.add(menuModel);// 放入
                iterator.remove();// 删除
                isHaveChilds(menuModels, menuModel);
            } else if (menuModel.getLeaf().equals(TeamShiroConstant.IS_NOT_LEAF)) {// 不是最后节点的
                isHaveChilds(menuModels, menuModel);
            }
        }
        return JSONArray.toJSONString(menus);
    }

    /**
     *
     * @Title: isHaveChilds
     * @Description: 所有子节点
     * @author sw
     * @param menuModels
     * @param menuModel
     */
    public static void isHaveChilds(List<MenuModel> menuModels, MenuModel menuModel) {
        Iterator<MenuModel> iterator = menuModels.iterator();
        while (iterator.hasNext()) {
            MenuModel childMenuModel = iterator.next();
            if (childMenuModel.getParentId().equals(menuModel.getId())) {
                List<MenuModel> childModels = null;
                if (null == menuModel.getChildMenuModels()) {
                    childModels = new ArrayList<MenuModel>();
                    childModels.add(childMenuModel);
                    menuModel.setChildMenuModels(childModels);
                } else {
                    menuModel.getChildMenuModels().add(childMenuModel);
                }
            }
        }
    }

    public static void main(String[] args) {
        MenuModel menuModel = new MenuModel();
        MenuModel menuModel1 = new MenuModel();
        menuModel1.setId(12121L);
        List<MenuModel> list = new ArrayList<MenuModel>();
        list.add(menuModel);
        list.add(menuModel1);
        System.out.println(list.size());
        Iterator<MenuModel> iterators = list.iterator();
        while (iterators.hasNext()) {
            if (null != iterators.next().getId()) {
                iterators.remove();
            }
        }
        System.out.println(list.size() + "," + list.get(0).getId());
    }

}
