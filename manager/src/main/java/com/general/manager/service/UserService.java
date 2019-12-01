package com.general.manager.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.general.manager.common.DefineException;
import com.general.manager.common.Pagenation;
import com.general.manager.entity.User;
import com.general.manager.entity.dto.UserDTO;
import com.general.manager.entity.vo.UserVO;

import java.util.List;

/**
 * @author: Hens
 * @DateTime: 2019/11/30 21:56
 * @Description: 用户
 */
public interface UserService {
    /**
     * 分页查询角色
     * @param pageNo
     * @param pageSize
     * @return
     */
    IPage<User> getUserList(int pageNo, int pageSize);

    Pagenation getUserPage();

    /**
     * 根据Id查询用户
     * @param uid
     * @return
     */
    UserVO getUserById(Long uid);

    /**
     * 更新用户信息
     * @param userVO
     * @return
     */
    int updateUser(UserVO userVO);

    /**
     * 删除用户信息
     * @param uid
     * @return
     */
    int deleteUserById(Long uid) throws DefineException;

    /**
     * 新增用户
     * @param userDTO
     * @return
     */
    int saveUser(UserDTO userDTO);

    /**
     * 修改用户密码
     * @param uid
     * @param oldPass
     * @param newPss
     * @return
     */
    int updatePassWord(long uid, String oldPass,String newPss);
}
