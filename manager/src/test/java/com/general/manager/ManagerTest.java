package com.general.manager;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.general.manager.entity.User;
import com.general.manager.entity.vo.UserVO;
import com.general.manager.mapper.UserDao;
import com.general.manager.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author: Hens
 * @DateTime: 2019/11/30 22:10
 * @Description: 测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerTest {
    @Autowired
    UserDao userDao;
    @Autowired
    UserService userService;
    @Test
    public void pageTest(){
        IPage<User> page = new Page<>(1,5);
        User user = userDao.selectById(1);
        System.out.println(user);
        IPage<User> userIPage = userDao.selectPage(page, null);
        List<User> records = userIPage.getRecords();
        System.out.println(records);
        long total = userIPage.getTotal();
        System.out.println(total);
        System.out.println(userIPage.getCurrent());
        System.out.println(userIPage.getSize());
        System.out.println(userIPage.getPages());
    }
    @Test
    public void updatePass(){
        int i = userService.updatePassWord(1L, "root", "admin");
        System.out.println(i);
    }
}
