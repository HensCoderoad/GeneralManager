package com.general.manager.controller;

import com.general.manager.common.Result;
import com.general.manager.entity.Menu;
import com.general.manager.entity.dto.UserDTO;
import com.general.manager.entity.vo.MenuVO;
import com.general.manager.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

/**
 * @author: Hens
 * @DateTime: 2019/11/30 12:12
 * @Description: 首页控制器
 */
@Controller
@RequestMapping("authc")
public class HomeController {
    @Autowired
    private SystemService systemService;

    @GetMapping("home")
    public Result toHome(@RequestParam String username){
        // 获取用户信息
        UserDTO userByName = systemService.findUserByName(username);
        // 获取用户菜单
        Long uid = userByName.getUid();
        Set<Menu> menuByUserId = systemService.getMenuByUserId(uid);
        return Result.success(menuByUserId);
    }

    @GetMapping("menu")
    @ResponseBody
    public Result getMenuList(){
        List<MenuVO> menuVOS = systemService.gentMenu();
        return Result.success(menuVOS);
    }

}
