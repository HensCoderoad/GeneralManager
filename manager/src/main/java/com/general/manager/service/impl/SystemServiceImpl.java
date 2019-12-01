package com.general.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.general.manager.entity.Menu;
import com.general.manager.entity.Role;
import com.general.manager.entity.User;
import com.general.manager.entity.dto.RoleDTO;
import com.general.manager.entity.dto.UserDTO;
import com.general.manager.entity.vo.MenuVO;
import com.general.manager.mapper.MenuDao;
import com.general.manager.mapper.RoleDao;
import com.general.manager.mapper.UserDao;
import com.general.manager.service.SystemService;
import com.general.manager.shiro.PasswordHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author: Hens
 * @DateTime: 2019/11/29 20:54
 * @Description: TODO
 */
@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private PasswordHelper passwordHelper;

    private Logger logger = LoggerFactory.getLogger(SystemServiceImpl.class);

    @Override
    public Set<Menu> getMenuByUserId(Long uid) {
        logger.info("获取菜单的uid={}",uid);
        if(uid == null){
            return Collections.emptySet();
        }
        return menuDao.getMenuByUserId(uid);
    }

    @Override
    public UserDTO findUserByName(String username) {
        logger.info("获取用户信息的username={}",username);
        if(username == null){
            return null;
        }
        // 拿出user的所有信息并写入userdto,需要数据（Set<RoleDTO> roles;）
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userDao.selectOne(queryWrapper);
        if(user == null){
            return null;
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        // 根据uid获取role信息
        Long uid = userDTO.getUid();
        Set<Role> roles = roleDao.getRole(uid);
        List<Role> roleList = new ArrayList<>(roles);
        List<RoleDTO> roleDTOList = new ArrayList<>();
        for(int i = 0;i<roleList.size();i++){
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setRid(roleList.get(i).getRid());
            roleDTO.setRole(roleList.get(i).getRole());
            roleDTO.setWeight(roleList.get(i).getWeight());
            roleDTOList.add(roleDTO);
        }
        Set<RoleDTO> roleDTOs = new HashSet<>(roleDTOList);
        // 根据role的id获取menu信息
        for(RoleDTO role: roleDTOs){
            Set<Menu> menus = menuDao.getMenu(role.getRid());
            // 将获得的menu根据Id放入userdto中
            role.setMenus(menus);
        }
        userDTO.setRoles(roleDTOs);
        return userDTO;
    }

    @Override
    public int saveUser(String username, String password) {
        logger.info("注册用户的username={}，password={}",username,password);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setPassword(password);
        passwordHelper.encryptPassword(userDTO);
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setSalt(userDTO.getSalt());
        int insert = userDao.insert(user);
        return insert;
    }

    @Override
    public int existByUsername(String username) {
        logger.info("判断用户存在的username={}",username);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = userDao.selectOne(queryWrapper);
        if(user != null){
            return 1;
        }
        return 0;
    }

    @Override
    public List<User> getUserList() {
        IPage<User> page = new Page<>(1,5);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        IPage<User> userIPage = userDao.selectPage(page, wrapper);
        long current = userIPage.getCurrent();
        long pages = userIPage.getPages();
        List<User> records = userIPage.getRecords();
        long size = userIPage.getSize();
        long total = userIPage.getTotal();
        return records;
    }

    @Override
    public List<MenuVO> gentMenu() {
//        List<Menu> menus = menuDao.selectList(null);
//        List<MenuVO> menuVOList = new ArrayList<>();
//        for(Menu menu:menus){
//            // 先找到父级
//            if(menu.getParentId() ==0){
//                MenuVO menuVO = new MenuVO();
//                menuVO.setTitle(menu.getMenuName());
//                menuVO.setHref(menu.getPath());
//                menuVO.setIcon(null);
//                menuVO.setSpread(false);
//                menuVO.setMenu_order(menu.getMenuOrder());
//                // 再找他的子类
//                Set<MenuVO> menuVOSet = new HashSet<>();
//                for(Menu menuChild:menus){
//                    if(menuChild.getParentId().intValue() == menu.getMid()){
//                        MenuVO menuVOChildren = new MenuVO();
//                        menuVOChildren.setTitle(menuChild.getMenuName());
//                        menuVOChildren.setHref(menuChild.getPath());
//                        menuVOChildren.setIcon(null);
//                        menuVOChildren.setSpread(false);
//                        menuVOSet.add(menuVOChildren);
//                    }
//                }
//                menuVO.setChildren(menuVOSet);
//                menuVOList.add(menuVO);
//            }
//        }
//        Collections.sort(menuVOList);
        return null;
    }
}
