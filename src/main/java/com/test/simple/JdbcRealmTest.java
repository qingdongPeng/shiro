package com.test.simple;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;


/**
 * Description:JdbcRealm测试
 *
 * @author pengqd
 * @Date 2018/9/17 12:42
 */
public class JdbcRealmTest
{
    //构建数据库连接
    DruidDataSource dataSource = new DruidDataSource();

    {
        dataSource.setUrl("jdbc:mysql://localhost/world?useSSL=true");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
    }

    @Test
    public void TestJdbcRealm(){

        //构建JdbcRealm
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);

        //开启权限开关
        jdbcRealm.setPermissionsLookupEnabled(true);

        //构建SecurityManager
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(jdbcRealm);

        //主体提交请求认证
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("pqd","123456");
        subject.login(token);
        System.out.println(subject.isAuthenticated());
        }
}
