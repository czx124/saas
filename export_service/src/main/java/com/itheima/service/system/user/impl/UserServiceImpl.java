package com.itheima.service.system.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.system.role.RoleDao;
import com.itheima.dao.system.user.UserDao;
import com.itheima.domain.system.roleUser.RoleUser;
import com.itheima.domain.system.user.User;
import com.itheima.service.system.user.UserService;
import com.itheima.service.utils.HttpUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Jackson
 * @date 2020/7/13 9:42
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public PageInfo<User> findByPage(String companyId, int pageNum, int pageSize) {
        //1.调用startPage方法
        PageHelper.startPage(pageNum, pageSize);
        //2.查询全部列表
        List<User> list = userDao.findAll(companyId);
        //构造pagebean
        return new PageInfo<User>(list);
    }

    //查询所有部门
    @Override
    public List<User> findAll(String companyId) {
        return userDao.findAll(companyId);
    }

    @Override
    public void save(User user) {
        user.setId(UUID.randomUUID().toString());
        if(user.getPassword()!=null){
            user.setPassword(new Md5Hash(user.getPassword(),user.getEmail()).toString());
        }
        userDao.save(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public boolean delete(String id) {
        List<RoleUser> roleUserList = userDao.findRoleUserByUid(id);
        if(roleUserList!=null && roleUserList.size()>0){
            return false;
        }else {
            userDao.delete(id);
            return true;
        }

    }

    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public void changeRole(String userId, String[] roleIds) {
        userDao.deleteRole(userId);
        if (roleIds != null && roleIds.length > 0) {
            for (String roleId : roleIds) {
                userDao.insertRole(userId, roleId);
            }
        }


    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User saveWxLogin(String code) {

        String accessTokenUrl = "http://api.weixin.qq.com/sns/oauth2/access_token";
        String appid = "wx3bdb1192c22883f3";
        String secret = "db9d6b88821df403e5ff11742e799105";
        String wxInfoUrl = "http://api.weixin.qq.com/sns/userinfo";
        User user = null;
        String atUtl = accessTokenUrl + "?code=" + code +
                "&appid=" + appid + "&secret=" + secret + "grant_type=authorization_code";
        System.out.println(atUtl);
        Map<String, Object> map1 = HttpUtils.sendGet(atUtl);
        Object access_token = map1.get("access_token");
        String openid = map1.get("openid").toString();
        if(access_token==null && openid==null){
            return null;
        }

        user = userDao.findByOpenid(openid);
            if(user != null){
                System.out.println("返回数据库中的用户对象");
            }
            else {
                System.out.println("查询微信数据库中的用户对象");
                String wxurl = wxInfoUrl+"?access_token="+access_token+"&openid="+openid;
                Map<String, Object> map2 = HttpUtils.sendGet(wxurl);
                Object nickname = map2.get("nickname");
                Object headerimgurl = map2.get("headerimgurl");
                if(nickname == null || headerimgurl ==null){
                    return user;
                }
                user.setId(UUID.randomUUID().toString());
                user.setOpenid(openid);
                user.setDegree(4);
                userDao.save(user);
            }
            return user;

    }
}
