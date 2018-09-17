package com.test.customrealm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Description:自定义Realm，继承AuthorizingRealm
 *
 * @author pengqd
 * @Date 2018/9/17 14:03
 */
public class CustomRealm extends AuthorizingRealm
{

    Map<String, String> userMap = new HashMap<String, String>();

    {
        //密码采用的是MD5加密过,原密码为123456
        userMap.put("pqd", "e10adc3949ba59abbe56e057f20f883e");
        //设置realmName
        super.setName("custom");
    }

    //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection)
    {
        //从主体传过来的认证信息中，获取用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        //从数据库或缓存中获取角色数据
        Set<String> roles = getRolesByUserName(username);
        //从数据库或缓存中获取角色权限
        Set<String> permissons = getPermissionByUserName(username);
        //构建返回对象
        SimpleAuthorizationInfo simpleAuthenticationInfo = new SimpleAuthorizationInfo();
        simpleAuthenticationInfo.setStringPermissions(permissons);
        simpleAuthenticationInfo.setRoles(roles);
        return simpleAuthenticationInfo;
    }



    //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
        throws AuthenticationException
    {

        //从主体传过来的认证信息中获得用户名
        String username = (String)authenticationToken.getPrincipal();

        //通过用户名获得凭证
        String password = getPasswordByName(username);
        if (password == null)
        {
            return null;
        }

        //创建返回对象
        return new SimpleAuthenticationInfo("pqd",password,"custom");
    }

    /**
     * 模拟用户
     */
    private String getPasswordByName(String username)
    {
        return userMap.get(username);
    }

    /**
     * 模拟角色数据
     */
    private Set<String> getRolesByUserName(String username) {
        Set <String> set = new HashSet<String>();
        set.add("admin");
        set.add("user");
        return  set;
    }

    private Set<String> getPermissionByUserName(String username) {
        Set <String> set = new HashSet<String>();
        set.add("user:delete");
        set.add("user:add");
        return set;
    }
}
