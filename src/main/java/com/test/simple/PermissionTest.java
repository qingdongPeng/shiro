package com.test.simple;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;


/**
 * Description:权限测试
 *
 * @author pengqd
 * @Date 2018/9/17 10:37
 */
public class PermissionTest
{

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser(){
        //添加用户的同时也添加权限,可添加多个权限
        simpleAccountRealm.addAccount("pqd","123456","admin");
    }

    @Test
    public void TestPermission(){

        //构建SecurtityManager环境
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(simpleAccountRealm);

        //主体提交请求认证
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("pqd","123456");
        subject.login(token);

        //判断是否认证
        System.out.println(subject.isAuthenticated());

        //权限判断
        subject.checkRoles("admin");
     }
}
