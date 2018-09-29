package com.sw.project.teamshrio.menu;

import com.sw.project.teamshrio.TeamshrioApplicationTests;
import org.springframework.beans.factory.annotation.Autowired;

public class MenuTest extends TeamshrioApplicationTests {
    @Autowired
    private MenuService menuService;
    @Autowired
    public void getMenus(){
        System.out.println(menuService+"**************");
        menuService.getFuncMenu(null,null);
    }
}
