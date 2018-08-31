package com.sw.project.teamshrio.menu;

import java.util.List;

public interface MenuModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MenuModel record);

    int insertSelective(MenuModel record);

    MenuModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MenuModel record);

    int updateByPrimaryKey(MenuModel record);

    /**
     * 根据用户查询菜单权限树
     * @param userId 当前登录用户
     * @param parentId 父级节点
     * @return 菜单集合
     */
    List<MenuModel> queryMenus(Integer userId,Integer parentId);


}