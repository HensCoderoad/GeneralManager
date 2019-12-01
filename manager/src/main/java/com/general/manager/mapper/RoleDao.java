package com.general.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.general.manager.entity.Menu;
import com.general.manager.entity.Role;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface RoleDao extends BaseMapper<Role> {
    /**
     * 根据用户id获取角色
     * @param uid
     * @return
     */
    @Select("select r.rid, r.role, r.weight from user u left join role_user ru on u.uid = ru.uid LEFT join role r on ru.rid = r.rid where u.uid = #{uid}")
    public Set<Role> getRole(Long uid);


}