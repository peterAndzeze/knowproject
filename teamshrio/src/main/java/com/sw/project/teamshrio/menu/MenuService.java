package com.sw.project.teamshrio.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
      public String queryMenus(Integer userId,Integer parentId){
          System.out.println("servixe");
          List<MenuModel> menuModels=menuModelMapper.queryMenus(userId,parentId);
          System.out.println(menuModels.size());
          return  "";
      }

}
