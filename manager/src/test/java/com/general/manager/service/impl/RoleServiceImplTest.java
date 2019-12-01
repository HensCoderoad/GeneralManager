package com.general.manager.service.impl;

import com.general.manager.entity.Menu;
import com.general.manager.entity.Role;
import com.general.manager.entity.dto.RoleDTO;
import com.general.manager.service.RoleService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: Hens
 * @DateTime: 2019/12/1 11:14
 * @Description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class RoleServiceImplTest {
    @Autowired
    RoleService roleService;

    @Test
    void getRoleDTOByRid() {
        RoleDTO roleDTOByRid = roleService.getRoleDTOByRid(1);
        System.out.println(roleDTOByRid);
    }

    @Test
    @Transactional
    @Rollback(true)
    void saveRole() {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRole("king");
        Set<Menu> menus = new HashSet<>();
        Menu menu = new Menu();
        menu.setMid(1);
        menu.setMenuOrder(2);
        menu.setParentId((byte) 0);
        menu.setMenuName("com");
        menu.setIsShow((byte) 0);
        menus.add(menu);
        roleDTO.setMenus(menus);
        roleDTO.setWeight(50);
        System.out.println(roleService.saveRole(roleDTO));
    }

    @Test
    @Transactional
    @Rollback(true)
    void deleteRoleByRid() {
        System.out.println(roleService.deleteRoleByRid(1));
    }

    @Test
    @Transactional
    @Rollback(true)
    void updateRole() {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRole("king");
        Set<Menu> menus = new HashSet<>();
        Menu menu = new Menu();
        menu.setMid(1);
        menu.setMenuOrder(2);
        menu.setParentId((byte) 0);
        menu.setMenuName("com");
        menu.setIsShow((byte) 0);
        menus.add(menu);
        roleDTO.setMenus(menus);
        roleDTO.setWeight(50);
        roleDTO.setRid(1);
        System.out.println(roleService.updateRole(roleDTO));
    }

    @Test
    void getRoleList() {
        List<Role> roleList = roleService.getRoleList();
        roleList.forEach(System.out::print);
    }
}