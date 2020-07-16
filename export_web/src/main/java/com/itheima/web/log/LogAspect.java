package com.itheima.web.log;

import com.itheima.domain.system.log.SysLog;
import com.itheima.domain.system.user.User;
import com.itheima.service.log.SysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author Jackson
 * @date 2020/7/14 21:44
 */
@Component
@Aspect
public class LogAspect {
    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private HttpServletRequest request;

    @Around("execution(* com.itheima.web.controller..*.*(..)) && !bean(sysLogController)")
    public Object insertLog(ProceedingJoinPoint pjp){
        try {
            Object retv = pjp.proceed();
            SysLog sysLog = new SysLog();
            sysLog.setMethod(pjp.getSignature().getName());
            sysLog.setAction(pjp.getTarget().getClass().getName());
            sysLog.setIp(request.getRemoteUser());
            sysLog.setTime(new Date());
            User user = (User) request.getSession().getAttribute("user");
            if(user != null){
                sysLog.setUserName(user.getUserName());
                sysLog.setCompanyId(user.getCompanyId());
                sysLog.setCompanyName(user.getCompanyName());
            }

            sysLogService.save(sysLog);
            return retv;
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }

    }

}
