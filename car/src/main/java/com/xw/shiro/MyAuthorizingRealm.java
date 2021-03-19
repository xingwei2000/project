package com.xw.shiro;

import com.xw.common.CodeMsg;
import com.xw.common.Result;
import com.xw.common.exception.BussiException;
import com.xw.domain.SysUser;
import com.xw.mapper.SysUserMapper;
import com.xw.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义认证鉴权器
 */
public class MyAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService ;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        char[] p = usernamePasswordToken.getPassword();
        String password = String.valueOf(p);
        // 根据用户名和密码查询用户
        Result result = sysUserService.queryUser(username, password);
        // 校验业务是否成功
        if (!result.getCode().equals(CodeMsg.SUCCESS.code)){
            return null ;
        }
        // 获取数据
        SysUser sysUser = (SysUser) result.getData();
        String realName = sysUser.getRealName();

        ActiveUser activeUser = new ActiveUser() ;
        activeUser.setSysUser(sysUser);
        activeUser.setRealname(sysUser.getRealName());
        activeUser.setRoles(null);
        activeUser.setPermission(null);

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(activeUser,password,realName) ;

        return simpleAuthenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }


}
