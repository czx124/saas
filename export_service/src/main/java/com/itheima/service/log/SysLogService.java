package com.itheima.service.log;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.log.SysLog;

/**
 * @author Jackson
 * @date 2020/7/14 20:32
 */
public interface SysLogService {
    PageInfo<SysLog> findAll(String companyId,Integer pageNum,Integer pageSize);

    void save(SysLog sysLog);
}
