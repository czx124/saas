package com.itheima.web.shiro;

import com.itheima.domain.system.module.Module;
import com.itheima.domain.system.user.User;
import com.itheima.service.system.module.ModuleService;
import com.itheima.service.system.user.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Jackson
 * @date 2020/7/16 17:34
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private ModuleService moduleService;
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取到用户界面输入的邮箱地址和密码
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        //获取用户用户邮箱
        String email = upToken.getUsername();
//        String password = new String(upToken.getPassword());
//        User user = (User) upToken.getPrincipal();
        //根据邮箱查询用户对象
        User user = userService.findByEmail(email);
        String password = user.getPassword();
        if(user!=null){
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, this.getName());
            return info;
        }

        return null;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取登录认证的对象
        User user = (User) principals.getPrimaryPrincipal();
        //根据用户id查询用户具有的权限
        List<Module> list = moduleService.findModulesByUid(user.getId());
        //返回告诉shiro用户有哪些权限
        SimpleAuthorizationInfo sai = new SimpleAuthorizationInfo();
        //添加权限
        if(list!=null && list.size()>0){
            for (Module module:list){
                sai.addStringPermission(module.getName());
            }
        }
        return sai;
    }


}
