package com.general.manager.service;

import com.general.manager.entity.Menu;
import com.general.manager.entity.User;
import com.general.manager.entity.dto.UserDTO;
import com.general.manager.entity.vo.MenuVO;

import java.util.List;
import java.util.Set;

/**
 * @author: Hens
 * @DateTime: 2019/11/29 20:51
 * @Description: TODO
 */
public interface SystemService {
    /**
     * 获取用户权限菜单
     * @param uid
     * @return
     */
    Set<Menu> getMenuByUserId(Long uid);

    /**
     * 通过用户名获取用户信息
     * @param username
     * @return
     */
    UserDTO findUserByName(String username);

    /**
     * 新增用户
     * @param username
     * @param password
     * @return
     */
    int saveUser(String username, String password);

    /**
     * 判断用户是否存在
     * @param username
     * @return
     */
    int existByUsername(String username);

    /**
     * 获取用户列表
     * @return
     */
    List<User> getUserList();

    /**
     * 获取所有菜单
     * @return
     */
    List<MenuVO> gentMenu();
}
