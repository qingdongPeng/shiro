package com.test.customrealm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;


/**
 * Description:自定义Realm测试
 *
 * @author pengqd
 * @Date 2018/9/17 14:09
 */
public class CustomRealmTest {

    @Test
    public void TestCustomRealm() {

        CustomRealm customRealm = new CustomRealm();

        //构建SecurityManager环境
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(customRealm);

        //对数据进行加密
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5"); //采用md5加密
        matcher.setHashIterations(1);        //设置加密次数
        customRealm.setCredentialsMatcher(matcher);

        //主体提交请求认证
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("pqd", "123456");
        subject.login(token);

        //判断是否认证
        System.out.println(subject.isAuthenticated());

        //授权测试
        subject.checkRoles("admin","user");
        subject.checkPermissions("user:delete","user:add");

        /*Md5Hash hash = new Md5Hash("123456");
        System.out.println(hash);*/

    }
}
