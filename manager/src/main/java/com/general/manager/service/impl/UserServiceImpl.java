package com.general.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.general.manager.common.DefineException;
import com.general.manager.common.ExceptionEnum;
import com.general.manager.common.Pagenation;
import com.general.manager.entity.Role;
import com.general.manager.entity.RoleUser;
import com.general.manager.entity.User;
import com.general.manager.entity.dto.RoleDTO;
import com.general.manager.entity.dto.UserDTO;
import com.general.manager.entity.vo.UserVO;
import com.general.manager.mapper.RoleDao;
import com.general.manager.mapper.RoleUserDao;
import com.general.manager.mapper.UserDao;
import com.general.manager.service.UserService;
import com.general.manager.shiro.PasswordHelper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author: Hens
 * @DateTime: 2019/11/30 22:02
 * @Description: TODO
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    RoleUserDao roleUserDao;
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public IPage<User> getUserList(int pageNo, int pageSize) {
        IPage<User> page = new Page<>(pageNo,pageSize);
        IPage<User> userIPage = userDao.selectPage(page, null);
//        List<User> records = userIPage.getRecords();
//        List<UserVO> userVOList = new ArrayList<>(records.size());
//        for(User user:records){
//            UserVO userVO = new UserVO();
//            Set<Role> role = roleDao.getRole(user.getUid());
//            userVO.setRoles(role);
//            BeanUtils.copyProperties(user, userVO);
//            userVOList.add(userVO);
//        }
        return userIPage;
    }

    @Override
    public Pagenation getUserPage() {
        IPage<User> page = new Page<>(1,5);
        IPage<User> userIPage = userDao.selectPage(page, null);
        long current = userIPage.getCurrent();
        long size = userIPage.getSize();
        long total = userIPage.getTotal();
        long pages = userIPage.getPages();
        Pagenation pagenation = new Pagenation(total,current,pages,size);
        return pagenation;
    }

    @Override
    public UserVO getUserById(Long uid) {
        if(uid != 0){
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("uid",uid);
            User user = userDao.selectOne(queryWrapper);
            UserVO userVO = new UserVO();
            Set<Role> role = roleDao.getRole(uid);
            userVO.setRoles(role);
            BeanUtils.copyProperties(user,userVO);
            return userVO;
        }
        return null;
    }

    @Override
    public int updateUser(UserVO userVO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",userVO.getUid());
        User user = new User();
        BeanUtils.copyProperties(userVO,user);
        return userDao.update(user, queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteUserById(Long uid) throws DefineException {
        // 删角色中间表
        QueryWrapper<RoleUser> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",uid);
        int delete = roleUserDao.delete(wrapper);
        int i = userDao.deleteById(uid);
        if(delete + i < 2 ){
            throw new DefineException(ExceptionEnum.EXCEPTION);
        }
        return i + delete;
    }

    @Override
    public int saveUser(UserDTO userDTO) {
        if(userDTO != null){
            PasswordHelper passwordHelper = new PasswordHelper();
            passwordHelper.encryptPassword(userDTO);
            User user = new User();
            BeanUtils.copyProperties(userDTO , user);
            userDao.insert(user);
            int uid = user.getUid().intValue();
            Set<RoleDTO> roles = userDTO.getRoles();
            List<RoleDTO> roleDTOList = new ArrayList<>(roles);
            SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH,false);
            RoleUserDao mapper = sqlSession.getMapper(RoleUserDao.class);
            try {
                for (int i = 0; i < roles.size(); i++) {
                    mapper.insert(new RoleUser(uid, roleDTOList.get(i).getRid()));
                    if (i % 1000 == 999 || i == roles.size() - 1) {
                        sqlSession.commit();
                        sqlSession.clearCache();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                sqlSession.rollback();
            }finally {
                sqlSession.close();
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updatePassWord(long uid, String oldPass, String newPass) {
        User user = userDao.selectById(uid);
        System.out.println(user);
        UserDTO userDTO = new UserDTO();
        if(user != null){
            BeanUtils.copyProperties(user,userDTO);
            PasswordHelper passwordHelper = new PasswordHelper();
            passwordHelper.encryptPasswordToUpdate(userDTO,oldPass);
            if(user.getPassword().equals(userDTO.getPassword())){
                passwordHelper.encryptPasswordToUpdate(userDTO, newPass);
                User userUpdate = new User();
                userUpdate.setPassword(userDTO.getPassword());
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("uid",uid);
                return userDao.update(userUpdate, queryWrapper);
            }
        }
        return 0;
    }
}
