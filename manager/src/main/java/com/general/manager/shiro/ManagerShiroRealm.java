package com.general.manager.shiro;

import com.general.manager.entity.Menu;
import com.general.manager.entity.dto.RoleDTO;
import com.general.manager.entity.dto.UserDTO;
import com.general.manager.service.SystemService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * @author: Hens
 * @DateTime: 2019/11/29 21:32
 * @Description: 身份验证
 */
public class ManagerShiroRealm extends AuthorizingRealm {
    @Resource
    SystemService systemService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String username = (String) principalCollection.getPrimaryPrincipal();
        UserDTO userByName = systemService.findUserByName(username);
        for(RoleDTO role : userByName.getRoles()){
            simpleAuthorizationInfo.addRole(role.getRole());
            for(Menu menu: role.getMenus()){
                simpleAuthorizationInfo.addStringPermission(menu.getMenuName());
            }
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        UserDTO userByName = systemService.findUserByName(username);
        if(userByName == null){
            return null;
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userByName.getUsername(),userByName.getPassword(), ByteSource.Util.bytes(userByName.getCredentialsSalt()),getName());
        return simpleAuthenticationInfo;
    }
}
