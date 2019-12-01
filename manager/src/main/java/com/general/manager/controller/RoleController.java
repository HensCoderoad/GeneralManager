package com.general.manager.controller;

import com.general.manager.common.Result;
import com.general.manager.common.ResultEnum;
import com.general.manager.entity.Role;
import com.general.manager.entity.dto.RoleDTO;
import com.general.manager.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Hens
 * @DateTime: 2019/12/1 11:07
 * @Description: 角色接口类
 */
@RestController
@RequestMapping("authc")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("roles")
    public Result getRoleList(){
        List<Role> roleList = roleService.getRoleList();
        if(roleList.size() != 0){
            return Result.success(roleList);
        }
        return Result.failure(ResultEnum.FAIL);
    }

    @PutMapping("roles/{rid}")
    public Result updateRole(@PathVariable int rid,@RequestBody RoleDTO roleDTO){
        int i = roleService.updateRole(roleDTO);
        if(i == 1){
            return Result.success();
        }
        return Result.failure(ResultEnum.FAIL);
    }

    @PostMapping("roles")
    public Result saveRole(@RequestBody RoleDTO roleDTO){
        int i = roleService.saveRole(roleDTO);
        if(i == 1){
            return Result.success();
        }
        return Result.failure(ResultEnum.FAIL);
    }

    @GetMapping("roles/{rid}")
    public Result getRoleByRid(@PathVariable int rid){
        RoleDTO roleDTOByRid = roleService.getRoleDTOByRid(rid);
        if(roleDTOByRid != null){
            return Result.success(roleDTOByRid);
        }
        return Result.failure(ResultEnum.FAIL);
    }

    @DeleteMapping("roles/{rid}")
    public Result deleteRoleByRid(@PathVariable int rid){
        int i = roleService.deleteRoleByRid(rid);
        if(i == 1){
            return Result.success();
        }
        return Result.failure(ResultEnum.FAIL);
    }
}
