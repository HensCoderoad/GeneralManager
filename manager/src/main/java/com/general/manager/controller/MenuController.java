package com.general.manager.controller;

import com.general.manager.common.Result;
import com.general.manager.common.ResultEnum;
import com.general.manager.entity.Menu;
import com.general.manager.entity.vo.MenuVO;
import com.general.manager.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Hens
 * @DateTime: 2019/12/1 10:11
 * @Description: 权限菜单接口
 */
@RestController
@RequestMapping("authc")
public class MenuController {
    @Autowired
    MenuService menuService;

    @GetMapping("menus/{mid}")
    public Result getMenuByMid(@PathVariable int mid){
        if(mid != 0){
            Menu menu = menuService.getMenu(mid);
            return Result.success(menu);
        }
        return Result.failure(ResultEnum.FAIL);
    }

    @PostMapping("menus")
    public Result saveMenu(@RequestBody MenuVO menuVO){
        int result = menuService.saveMenu(menuVO);
        if(result != 0){
            return Result.success();
        }
        return Result.failure(ResultEnum.FAIL);
    }

    @PutMapping("menus/{mid}")
    public Result updateMenu(@PathVariable int mid,@RequestBody MenuVO menuVO){
        int result = menuService.updateMenu(menuVO);
        if(result != 0){
            return Result.success();
        }
        return Result.failure(ResultEnum.FAIL);
    }

    @DeleteMapping("menus/{mid}")
    public Result deleteMenu(@PathVariable int mid){
        int result = menuService.deleteMenu(mid);
        if(result != 0){
            return Result.success();
        }
        return Result.failure(ResultEnum.FAIL);
    }
    @GetMapping("menus")
    public Result getMenuList(){
        List<MenuVO> menuList = menuService.getMenuList();
        if(menuList != null){
            return Result.success(menuList);
        }
        return Result.failure(ResultEnum.FAIL);
    }
}
