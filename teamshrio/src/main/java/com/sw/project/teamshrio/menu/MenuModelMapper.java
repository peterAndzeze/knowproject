package com.sw.project.teamshrio.menu;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface MenuModelMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByParentId(Long parentId);


    int insert(MenuModel record);

    int insertSelective(MenuModel record);

    MenuModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MenuModel record);

    int updateByPrimaryKey(MenuModel record);

    /**
     * 根据用户查询菜单权限树
     * @param roleId 当前登录用户角色
     * @param parentId 父级节点
     * @return 菜单集合
     */
    List<MenuModel> queryMenus(@Param("roleId") Long roleId,@Param("parentId") Long parentId);


}