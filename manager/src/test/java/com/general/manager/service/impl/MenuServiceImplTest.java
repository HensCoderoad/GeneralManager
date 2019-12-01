package com.general.manager.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.general.manager.entity.Menu;
import com.general.manager.entity.vo.MenuVO;
import com.general.manager.service.MenuService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: Hens
 * @DateTime: 2019/12/1 9:23
 * @Description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class MenuServiceImplTest {
    @Autowired
    MenuService menuService;

    @Test
    void getMenuList() {
        List<MenuVO> menuList = menuService.getMenuList();
        menuList.forEach(System.out::print);
    }

    @Test
    @Transactional
    @Rollback(true)
    void saveMenu() {
        MenuVO menuVO = new MenuVO();
        menuVO.setPath("not");
        menuVO.setIsShow((byte) 1);
        menuVO.setMenuName("wang");
        menuVO.setMenuOrder(1);
        menuVO.setParentId((byte) 1);
        int i = menuService.saveMenu(menuVO);
        System.out.println(i);
    }

    @Test
    void getMenu() {
        Menu menu = menuService.getMenu(1);
        System.out.println(menu);
    }

    @Test
    @Transactional
    @Rollback(true)
    void deleteMenu() {
        System.out.println(menuService.deleteMenu(1));
    }

    @Test
    @Transactional
    @Rollback(true)
    void updateMenu() {
        MenuVO menuVO = new MenuVO();
        menuVO.setMid(1);
        menuVO.setMenuOrder(2);
        menuVO.setParentId((byte) 0);
        menuVO.setParentId((byte) 5);
        menuVO.setMenuName("com");
        menuVO.setIsShow((byte) 0);
        System.out.println(menuService.updateMenu(menuVO));
    }

    @Test
    void menuPage(){
        IPage<Menu> menuPage = menuService.getMenuPage(1, 5);
        List<Menu> records = menuPage.getRecords();
        records.forEach(System.out::print);
    }
}