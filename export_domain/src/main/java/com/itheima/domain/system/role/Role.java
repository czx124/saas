package com.itheima.domain.system.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jackson
 * @date 2020/7/13 12:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private String id;
    private String name;
    private String remark;
    private Long orderNo;
    private String companyId;
    private String companyName;
}
