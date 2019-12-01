package com.general.manager.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.general.manager.common.DefineException;
import com.general.manager.common.Result;
import com.general.manager.common.ResultEnum;
import com.general.manager.entity.User;
import com.general.manager.entity.dto.UserDTO;
import com.general.manager.entity.vo.UserVO;
import com.general.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Hens
 * @DateTime: 2019/11/30 21:33
 * @Description: 操作用户
 */
@RestController
@RequestMapping("authc")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("users/{uid}")
    public Result getUserById(@PathVariable  Long uid){
        UserVO userById = userService.getUserById(uid);
        if(userById != null){
            return Result.success(userById);
        }
        return Result.failure(ResultEnum.FAIL);
    }

    @PutMapping("users/{uid}")
    public Result updateUser(@RequestBody UserVO userVO){
        int i = userService.updateUser(userVO);
        if(i == 1){
            return Result.success();
        }
        return Result.failure(ResultEnum.FAIL);
    }

    @GetMapping("users")
    public Result getUserList(@RequestParam("pageNo") int pageNo,@RequestParam("pageSize") int pageSize){
        IPage<User> userList = userService.getUserList(pageNo, pageSize);
        if(userList != null){
            return Result.success(userList);
        }
        return Result.failure(ResultEnum.FAIL);
    }

    @DeleteMapping("users/{uid}")
    public Result deleteUser(@PathVariable long uid){
        if(uid != 0){
            int result = 0;
            try {
                result = userService.deleteUserById(uid);
            } catch (DefineException e) {
                e.printStackTrace();
            }
            if(result == 1){
                return Result.success();
            }
        }
        return Result.failure(ResultEnum.FAIL);
    }

    @PostMapping("users")
    public Result saveUser(@RequestBody UserDTO userDTO){
        int result = userService.saveUser(userDTO);
        if(result != 0){
            return Result.success();
        }
        return Result.failure(ResultEnum.FAIL);
    }
}
