package com.general.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.general.manager.entity.Menu;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

public interface MenuDao extends BaseMapper<Menu> {
    @Select("select m.* from menu m left join role_menu rm on m.mid = rm.mid LEFT join role r on rm.rid = r.rid where r.rid = #{rid}")
    public Set<Menu> getMenu(int rid);

    @Select("select distinct m.* from menu m left join role_menu rm on m.mid = rm.mid LEFT join role r on rm.rid = r.rid , (select r.rid from role_user r left join user u on r.uid = u.uid where u.uid = #{uid}) ro  where r.rid = ro.rid")
    public Set<Menu> getMenuByUserId(Long uid);
}