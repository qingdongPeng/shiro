package com.test.customrealm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.Map;

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
        userMap.put("pqd", "123456");
        //设置realmName
        super.setName("custom");
    }

    //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection)
    {
        return null;
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
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo("pqd",password,"custom");
        return simpleAuthenticationInfo;
    }

    private String getPasswordByName(String username)
    {
        return userMap.get(username);
    }
}
