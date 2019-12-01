package com.general.manager.controller;

import com.general.manager.common.Result;
import com.general.manager.common.ResultEnum;
import com.general.manager.entity.dto.UserDTO;
import com.general.manager.entity.vo.LoginUser;
import com.general.manager.entity.vo.UserVO;
import com.general.manager.service.SystemService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Hens
 * @DateTime: 2019/11/29 21:01
 * @Description: 登录控制器
 */
@RestController
public class LoginController {
    @Autowired
    private SystemService systemService;

    @PostMapping("/doLogin")
    public Result doLogin(@RequestBody LoginUser loginUser){
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();
        System.out.println(username);
        System.out.println(password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password );
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException ice) {
            return Result.failure(ResultEnum.PASSWORD_ERRO);
        } catch (UnknownAccountException uae) {
            return Result.failure(ResultEnum.USERNAME_ERRO);
        }
        UserDTO userByName = systemService.findUserByName(username);
        UserVO userVO = new UserVO(userByName.getUid(), userByName.getUsername(), userByName.getNickname());
        return Result.success(userVO);
    }

    @PostMapping("/register")
    public Result register(@RequestParam String username, @RequestParam String password) {
        int isExist = systemService.existByUsername(username);
        if(isExist != 0){
            return Result.failure(ResultEnum.USERNAME_ISEXIST);
        }
        int i = systemService.saveUser(username, password);
        if(i == 1){
            return Result.success();
        }
        return Result.failure(ResultEnum.SERVER_ERROR);
    }

    @GetMapping("/authc/logout")
    public Result logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Result.success();
    }
}
