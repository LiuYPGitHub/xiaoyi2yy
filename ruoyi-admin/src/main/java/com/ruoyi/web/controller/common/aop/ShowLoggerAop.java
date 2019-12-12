package com.ruoyi.web.controller.common.aop;

import com.ruoyi.web.controller.common.annotation.ShowLogger;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class ShowLoggerAop {

    @Autowired
    private HttpServletRequest request;

    @Pointcut(value = "@annotation(com.ruoyi.web.controller.common.annotation.ShowLogger)")
    public void pointcut(){}

    @Before(value = "pointcut()")
    public void before(JoinPoint point){
        log.info("----------------  start  ----------------");

        showLoggerInfo(point);

        Object[] args = point.getArgs();
        for (Object arg : args) {
            if(arg == null){
                log.info("request:[null]");
            }else{
                log.info("request:["+arg.toString()+"]");
            }

        }
    }

    public void showLoggerInfo(JoinPoint point){
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        if(method.isAnnotationPresent(ShowLogger.class)){
            ShowLogger showLogger = method.getAnnotation(ShowLogger.class);
            log.info("url:["+request.getRequestURI()+"]");
            log.info("info:["+showLogger.info()+"]");
            log.info("method:["+method.getName()+"]");
        }
    }

    @AfterReturning(value = "pointcut()",returning = "result")
    public void afterReturn(Object result){
        if(result != null){
            log.info("response:["+result.toString()+"]");
        }else{
            log.info("response:[Null]");
        }
        log.info("----------------  end  ----------------");
    }

}
