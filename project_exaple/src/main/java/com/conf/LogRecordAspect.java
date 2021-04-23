package com.conf;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LMM
 * @date 15:19 2021/4/23
 * @desc 日志
 */
@Aspect
@Configuration
public class LogRecordAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogRecordAspect.class);

// 定义切点Pointcut
   @Pointcut("execution(* com.controller.*Controller.*(..))")
   public void excudeService(){

   }

   @Around("excudeService()")
   public Object doAround(ProceedingJoinPoint pjp)throws Throwable{

       RequestAttributes ra = RequestContextHolder.getRequestAttributes();
       ServletRequestAttributes sra = (ServletRequestAttributes) ra;
       HttpServletRequest request = sra.getRequest();

       String method = request.getMethod();
       String uri = request.getRequestURI();
       String paraString = JSON.toJSONString(request.getParameterMap());
       logger.info("*********请求开始*********");
       logger.info("URI:{},method:{},params:{}",uri,method,paraString);

       Object result = pjp.proceed();
       logger.info("请求结束，返回值是"+JSON.toJSONString(result));

       return result;
   }

}
