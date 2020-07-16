package com.itheima.domain.system.dept;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept {
    private String id;
    private String deptName;
    private Integer state;
    private String companyId;
    private String companyName;
    private Dept parent;
}
