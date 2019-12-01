package com.general.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.general.manager.entity.Menu;
import com.general.manager.entity.Role;
import com.general.manager.entity.RoleMenu;
import com.general.manager.entity.dto.RoleDTO;
import com.general.manager.mapper.MenuDao;
import com.general.manager.mapper.RoleDao;
import com.general.manager.mapper.RoleMenuDao;
import com.general.manager.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author: Hens
 * @DateTime: 2019/12/1 10:28
 * @Description: 角色逻辑处理
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao roleDao;
    @Autowired
    RoleMenuDao roleMenuDao;
    @Autowired
    MenuDao menuDao;


    @Override
    public RoleDTO getRoleDTOByRid(int rid) {
        RoleDTO roleDTO = new RoleDTO();
        Role role = roleDao.selectById(rid);
        Set<Menu> menus = menuDao.getMenu(rid);
        BeanUtils.copyProperties(role, roleDTO);
        roleDTO.setMenus(menus);
        return roleDTO;
    }

    @Override
    public int saveRole(RoleDTO roleDTO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDTO, role);
        int insertRole = roleDao.insert(role);
        int rid  = role.getRid();
        Set<Menu> menus = roleDTO.getMenus();
        int insertMenu = 0;
        for(Menu menu:menus){
            RoleMenu roleMenu = new RoleMenu(rid,menu.getMid());
            insertMenu = roleMenuDao.insert(roleMenu);
        }
        if(insertRole + insertMenu == 1 + menus.size()){
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteRoleByRid(int rid) {
        int i = roleDao.deleteById(rid);
        Set<Menu> menus = menuDao.getMenu(rid);
        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("rid",rid);
        int delete = roleMenuDao.delete(queryWrapper);
        if(i + delete == 1 + menus.size()){
            return 1;
        }
        return 0;
    }

    @Override
    public int updateRole(RoleDTO roleDTO) {
        int rid = roleDTO.getRid();
        Role role = new Role();
        BeanUtils.copyProperties(roleDTO,role);
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("rid",rid);
        int update = roleDao.update(role, queryWrapper);
        // 更新角色权限中间表
        QueryWrapper<RoleMenu> wrapper = new QueryWrapper<>();
        queryWrapper.eq("rid",roleDTO.getRid());
        int delete = roleMenuDao.delete(wrapper);
        int insert = 0;
        for(Menu menu:roleDTO.getMenus()){
            RoleMenu roleMenu = new RoleMenu(rid,menu.getMid());
            insert = roleMenuDao.insert(roleMenu);
        }
        if(update + insert == 1 + roleDTO.getMenus().size()){
            return 1;
        }
        return 0;
    }

    @Override
    public List<Role> getRoleList() {
        return roleDao.selectList(null);
    }
}
