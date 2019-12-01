package com.general.manager.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.general.manager.common.DefineException;
import com.general.manager.entity.User;
import com.general.manager.entity.dto.RoleDTO;
import com.general.manager.entity.dto.UserDTO;
import com.general.manager.entity.vo.UserVO;
import com.general.manager.service.SystemService;
import com.general.manager.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: Hens
 * @DateTime: 2019/12/1 0:59
 * @Description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    UserService userService;
    @Autowired
    SystemService systemService;

    @Test
    void getUserList() {
        IPage<User> userList = userService.getUserList(1, 5);
        System.out.println(userList);
    }

    @Test
    void getUserById() {
        UserVO userById = userService.getUserById(1L);
        System.out.println(userById);
    }

    @Test
    void updateUser() {
        UserVO userVO = new UserVO();
        userVO.setUid(2L);
        userVO.setNickname("nicka");
        userVO.setUpdateTime(new Date());
        userService.updateUser(userVO);
    }

    @Test
    @Transactional
    @Rollback(true)
    void deleteUserById() {
        try {
            System.out.println(userService.deleteUserById(2L));
        } catch (DefineException e) {
            e.printStackTrace();
        }
    }

    @Test
    void saveUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("hens");
        int i = systemService.existByUsername(userDTO.getUsername());
        if(i!=1){
            userDTO.setNickname("wang");
            userDTO.setPassword("nick");
            Set<RoleDTO> roleDTOSet = new HashSet<>();
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setRid(1);
            RoleDTO roleDTO1 = new RoleDTO();
            roleDTO1.setRid(2);
            roleDTOSet.add(roleDTO);
            roleDTOSet.add(roleDTO1);
            userDTO.setRoles(roleDTOSet);
            userDTO.setCreateTime(new Date());
            userDTO.setUpdateTime(new Date());
            System.out.println(userService.saveUser(userDTO));
        }
        System.out.println("UserName is Exist");
    }

    @Test
    @Transactional
    @Rollback(true)
    void updatePassWord() {
        System.out.println(userService.updatePassWord(2L, "root", "admin"));
    }
}