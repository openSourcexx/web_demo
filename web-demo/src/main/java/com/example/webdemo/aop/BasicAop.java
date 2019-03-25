package com.example.webdemo.aop;

import com.example.webdemo.auth.BasicLog;
import com.example.webdemo.beans.OperatorLog;
import com.example.webdemo.service.OperatorLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author tangaq
 * @date 2019/3/21
 */
@Aspect
@Component
public class BasicAop {

    private Logger logger = LoggerFactory.getLogger(BasicAop.class);

    @Autowired
    private OperatorLogService logService;

    /**
     * 配置切入点，带有@Busilog注解的方法
     */
    @Pointcut("@annotation(com.example.webdemo.auth.BasicLog)")
    public void log() {

    }

    /**
     * 前置通知
     * @param joinPoint
     * @param basicLog
     */
    @Before("log() && @annotation(basicLog)")
    public void before(JoinPoint joinPoint,BasicLog basicLog) {

    }

    /**
     * 后置通知
     * @param joinPoint
     * @param basicLog
     */
    @After("log() && @annotation(basicLog)")
    public void after(JoinPoint joinPoint,BasicLog basicLog) {
        // 用户登陆校验
        OperatorLog log = new OperatorLog();
        log.setContent(basicLog.value());
        log.setOperatorName("admin");
        log.setOperatorId(1);
        try {
            logService.save(log);
        } catch (Exception e) {
            logger.error("新增操作日志失败",e);
        }
    }
}
