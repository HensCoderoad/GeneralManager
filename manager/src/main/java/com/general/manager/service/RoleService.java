package com.general.manager.service;

import com.general.manager.entity.Menu;
import com.general.manager.entity.Role;
import com.general.manager.entity.dto.RoleDTO;

import java.util.List;

/**
 * @author: Hens
 * @DateTime: 2019/12/1 10:21
 * @Description: TODO
 */
public interface RoleService {
    /**
     * 获取角色信息
     * @param rid
     * @return
     */
    RoleDTO getRoleDTOByRid(int rid);

    /**
     * 新增角色
     * @param roleDTO
     * @return
     */
    int saveRole(RoleDTO roleDTO);

    /**
     * 删除角色
     * @param rid
     * @return
     */
    int deleteRoleByRid(int rid);

    /**
     * 修改角色
     * @param roleDTO
     * @return
     */
    int updateRole(RoleDTO roleDTO);

    /**
     * 获取所有角色信息
     * @return
     */
    List<Role> getRoleList();
}
