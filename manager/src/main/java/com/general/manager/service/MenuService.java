package com.general.manager.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.general.manager.entity.Menu;
import com.general.manager.entity.vo.MenuVO;

import java.util.List;

/**
 * @author: Hens
 * @DateTime: 2019/12/1 9:15
 * @Description: TODO
 */
public interface MenuService {

    List<MenuVO> getMenuList();

    IPage<Menu> getMenuPage(int pageNo, int pageSize);

    int saveMenu(MenuVO menuVO);

    Menu getMenu(int mid);

    int deleteMenu(int mid);

    int updateMenu(MenuVO menuVO);
}
