package com.itheima.dao.system.log;

import com.itheima.domain.system.log.SysLog;

import java.util.List;

/**
 * @author Jackson
 * @date 2020/7/14 19:33
 */
public interface SysLogDao {
    List<SysLog> findAll(String companyId);

    void save(SysLog sysLog);
}
