package com.test.simple;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * Description:IniRealm测试
 *
 * @author pengqd
 * @Date 2018/9/17 10:58
 */
public class IniRealmTest
{

    @Test
    public void testIniRealm(){

        //创建一个Inirealm
        IniRealm iniRealm = new IniRealm("classpath:user.ini");

        //构建SecurityManager环境
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(iniRealm);

        //主体提交请求认证
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("pqd","123456");
        subject.login(token);

        //判断是否认证
        System.out.println(subject.isAuthenticated());

        //角色校验
        subject.checkRole("admin");

        //角色权限校验
        subject.checkPermission("user:update");
    }
}
