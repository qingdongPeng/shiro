package com.test.customrealm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;


/**
 * Description:自定义Realm测试
 *
 * @author pengqd
 * @Date 2018/9/17 14:09
 */
public class CustomRealmTest
{

    @Test
    public void TestCustomRealm(){

        CustomRealm customRealm = new CustomRealm();

        //构建SecurityManager环境
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(customRealm);

        //主体提交请求认证
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("pqd","123456");
        subject.login(token);

        //判断是否认证
        System.out.println(subject.isAuthenticated());

    }
}
