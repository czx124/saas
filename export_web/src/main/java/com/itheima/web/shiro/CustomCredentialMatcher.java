package com.itheima.web.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author Jackson
 * @date 2020/7/16 21:22
 */
public class CustomCredentialMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info){
        //获取用户输入的email
        String email = (String) token.getPrincipal();
        //获取用户输入的密码
        String credentials = new String((char[]) token.getCredentials());
        //对用户输入的密码加密，加盐(参数1加密，参数2加盐)
        String encodePsw = new Md5Hash(credentials,email).toString();
        //获取数据库的正确密码
        String dbPsw = (String) info.getCredentials();
        return encodePsw.equals(dbPsw);
    }

}

