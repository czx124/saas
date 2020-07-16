package com.itheima.service.system.user;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.user.User;

import java.util.List;

/**
 * 用户业务方法
 * @author Jackson
 */
public interface UserService {

    // 分页查询
    PageInfo<User> findByPage(String companyId, int pageNum, int PageSize);
    //查询所有部门
    List<User> findAll(String companyId);
    //保存
    void save(User user);

    //更新
    void update(User user);

    //删除
    boolean delete(String id);

    //根据id查询
    User findById(String id);


    void changeRole(String userId, String[] roleIds);

    User findByEmail(String email);

    User saveWxLogin(String code);
}
