package com.general.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.general.manager.entity.Menu;
import com.general.manager.entity.vo.MenuVO;
import com.general.manager.mapper.MenuDao;
import com.general.manager.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Hens
 * @DateTime: 2019/12/1 9:17
 * @Description: 权限操作
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Override
    public List<MenuVO> getMenuList() {
        List<Menu> menus = menuDao.selectList(null);
        List<MenuVO> menuVOList = new ArrayList<>(menus.size());
        for(int i =0;i<menus.size();i++){
            MenuVO menuVO = new MenuVO();
            BeanUtils.copyProperties(menus.get(i),menuVO);
            menuVOList.add(menuVO);
        }
        return menuVOList;
    }

    @Override
    public IPage<Menu> getMenuPage(int pageNo, int pageSize) {
        IPage<Menu> menuIPage = new Page<>(pageNo,pageSize);
        IPage<Menu> menuPage = menuDao.selectPage(menuIPage, null);
        return menuPage;
    }

    @Override
    public int saveMenu(MenuVO menuVO) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuVO,menu);
        return menuDao.insert(menu);
    }

    @Override
    public Menu getMenu(int mid) {
        if(mid != 0){
            QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("mid",mid);
            return menuDao.selectOne(queryWrapper);
        }
        return null;
    }

    @Override
    public int deleteMenu(int mid) {
        if(mid != 0){
            return menuDao.deleteById(mid);
        }
        return 0;
    }

    @Override
    public int updateMenu(MenuVO menuVO) {
        if(menuVO != null){
            Menu menu = new Menu();
            BeanUtils.copyProperties(menuVO, menu);
            return menuDao.updateById(menu);
        }
        return 0;
    }
}
