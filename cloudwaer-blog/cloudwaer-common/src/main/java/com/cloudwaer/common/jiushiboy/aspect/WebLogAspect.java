package com.cloudwaer.common.jiushiboy.aspect;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.alibaba.fastjson.JSON;
import com.cloudwaer.common.entity.WebLog;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @ClassName WebLogAspect    @Order 设置1 意思为当有多个切面起一个执行
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/11 16:02
 * @Version 1.0
 **/

@Component
@Aspect
@Order(1)
@Slf4j
public class WebLogAspect {
    /**
     * 日志记录 --->  环绕通知: 方法执行之前,方法执行之后
     */

    /**
     * 定义切入点
     */
    @Pointcut("execution(* com.cloudwaer.controller.*.*(..))")
    public void webLog() {
    }

    /**
     * 环绕通知
     */
//    @Around("webLog()")
    public Object recodeWebLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = null;
        WebLog webLog = new WebLog();
        // 方法开始时间
        long start = System.currentTimeMillis();
        // 执行方法调用
        result = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        // 方法结束时间
        long end = System.currentTimeMillis();
        //请求 接口 时间
        webLog.setSpendTime((int) (start - end) / 1000);
        // 获取当前请求的request对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String url = request.getRequestURL().toString();
        //设置请求uri
        webLog.setUri(request.getRequestURI());
        webLog.setUrl(url);
        webLog.setBasePath(StrUtil.removeSuffix(url, URLUtil.url(url).getPath()));

        // 获取安全的上下文
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //获取用户名称
        webLog.setUsername(authentication == null ? "anonymous" : authentication.getPrincipal().toString());
        //TODO 获取ip地址
        webLog.setIp(request.getRemoteAddr());


        // 获取方法名
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        // 获取类名
        String targetClassName = proceedingJoinPoint.getTarget().getClass().getName();
        webLog.setMethod(targetClassName + "." + method.getName());

        // 因为使用到了swagger,所以在方法上添加@ApiOperation(value="")注解
        ApiOperation annotation = method.getAnnotation(ApiOperation.class);
        webLog.setDescription(annotation == null ? "no desc" : annotation.value());
        // 方法入参
        webLog.setParameter(getMethodParamter(method,proceedingJoinPoint.getArgs()));
        // 方法反参
        webLog.setResult(result);
        log.info(JSON.toJSONString(webLog,true));
        return result;
    }

    /**
     * 获取方法执行参数
     * @param method
     * @param args
     * @return
     */
    private Object getMethodParamter(Method method, Object[] args) {
        HashMap<String, Object> methodParamterWithValue = new HashMap<>();
        LocalVariableTableParameterNameDiscoverer localVariableTableParameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        // 方法形参名
        String[] parameterNames = localVariableTableParameterNameDiscoverer.getParameterNames(method);
        for (int i = 0; i < parameterNames.length; i++) {
            methodParamterWithValue.put(parameterNames[i],args[i]);
        }
        return methodParamterWithValue;
    }


}
