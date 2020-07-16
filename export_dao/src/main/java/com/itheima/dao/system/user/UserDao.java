package com.itheima.dao.system.user;


import com.itheima.domain.system.roleUser.RoleUser;
import com.itheima.domain.system.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserDao {

	//根据企业id查询全部
	List<User> findAll(String companyId);

	//根据id查询
    User findById(String userId);

	//根据id删除
	void delete(String userId);

	//保存
	void save(User user);

	//更新
	void update(User user);

    void insertRole(@Param("userId")String userId,@Param("roleId") String roleId);

	void deleteRole(String userId);

	User findByEmail(String email);

    List<RoleUser> findRoleUserByUid(String id);

	User findByOpenid(String openid);
}