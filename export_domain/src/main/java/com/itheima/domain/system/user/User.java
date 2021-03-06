package com.itheima.domain.system.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jackson
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String deptId;
    private String deptName;
    private String email;
    private String userName;
    private String password;
    private String openid;
    /**
     * 1启用0停用
     */ 
    private Integer state;
    private String managerId;
    private String joinDate;
    private Double salary;
    private String birthday;
    private String gender;
    private String station;
    private String telephone;
    /**
     * 0作为内部控制，租户企业不能使用
     *      0-saas管理员
     *      1-企业管理员
     *      2-管理所有下属部门和人员
     *      3-管理本部门
     *      4-普通员工
     */ 
    private Integer degree;
    private String remark;
    private Integer orderNo;
    private String companyId;
    private String companyName;


}