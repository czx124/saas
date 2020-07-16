package com.itheima.domain.system.log;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Jackson
 * @date 2020/7/14 19:31
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysLog {
    private String id;
    private String userName;
    private String ip;
    private Date time;
    private String method;
    private String action;
    private String companyId;
    private String companyName;
}
