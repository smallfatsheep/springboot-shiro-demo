package com.example.springbootshirodemo.shiro;

import com.example.springbootshirodemo.bean.Role;
import com.example.springbootshirodemo.bean.User;
import com.example.springbootshirodemo.dao.UserRepository;
import com.example.springbootshirodemo.jwt.JwtToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * doGetAuthenticationInfo() 方法：用来验证当前登录的用户，获取认证信息。
 * doGetAuthorizationInfo() 方法：为当前登录成功的用户授予权限和分配角色。
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("======" + username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 给该用户设置角色，角色信息存在 t_role 表中取
        User user = userRepository.getByUsername(username);
        Set<String> roles = new HashSet<>();
        for (Role role : user.getRoles()){
            roles.add(role.getRolename());
        }
        authorizationInfo.setRoles(roles);
        // 给该用户设置权限，权限信息存在 t_permission 表中取
        authorizationInfo.setStringPermissions(userRepository.getPermissions(roles));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 根据 Token 获取用户名，如果您不知道该 Token 怎么来的，先可以不管，下文会解释
        String username = (String) authenticationToken.getPrincipal();
        System.out.println("+++++++" + username);
        // 根据用户名从数据库中查询该用户
        User user = userRepository.getByUsername(username);
        if(user != null) {
            // 传入用户名和密码进行身份认证，并返回认证信息
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
            return authcInfo;
        } else {
            return null;
        }
    }
}
