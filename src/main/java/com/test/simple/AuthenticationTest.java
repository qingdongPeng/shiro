package com.test.simple;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * Description:认证测试
 *
 * @author pengqd
 * @Date 2018/9/17 10:00
 */
public class AuthenticationTest
{

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser()
    {
        simpleAccountRealm.addAccount("pqd","123456");
    }

    @Test
    public void testAuthentication(){

        //构建SecurityManager环境
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(simpleAccountRealm);

        //主体提交认证请求
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("pqd","123456");
        subject.login(token);

        //判断是否认证
        System.out.println(subject.isAuthenticated()); //输出结果为true

        //退出
        subject.logout();
        System.out.println(subject.isAuthenticated()); //输出结果为false
    }
}
