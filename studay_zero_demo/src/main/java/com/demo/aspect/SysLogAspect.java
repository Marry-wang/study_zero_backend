package com.demo.aspect;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.demo.backend.model.BasePerson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName SysLogAspect
 * @Author 王孟伟
 * @Date 2021/11/17 9:53
 * @Version 1.0
 */
@Aspect
@Component
public class SysLogAspect {

    private static Logger log = LoggerFactory.getLogger(SysLogAspect.class);

    @Pointcut("execution(* com.demo.backend.*.*Controller.*(..))")
    public void pointCut() {
        throw new UnsupportedOperationException();
    }

    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        log.info("========start aop log =========");
        long startTime = System.currentTimeMillis();
        Object[] args = point.getArgs();
        String jsonParam = jsonParams(args);

        Object result = point.proceed();
        long proceedTime = System.currentTimeMillis() - startTime;
        BasePerson user = getUser();
        String className = point.getTarget().getClass().getName();
        MethodSignature signature = (MethodSignature) point.getSignature();
        String methodName = signature.getName();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            String ip = getIpAddress(request);
            log.info("operator:{},time:{},processTime:{},method:{},params:{},result:{},IP:{}",
                    null == user ? "notLogin" : JSONObject.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat),
                    getCurrentTime(), proceedTime,
                    className + "." + methodName + "()",
                    jsonParam,
                    JSONObject.toJSON(result).toString(),
                    ip
            );
        } catch (Exception ex) {
        }

        log.info("========end aop log =========");
        return result;
    }

    /**
     * 获取当前登录用户
     * @return
     */
    public BasePerson getUser() {
        BasePerson person =new BasePerson();
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName(); //主体名，即登录用户名
            person.setUsername(name);
            System.out.println(name);

        } catch (Exception ex) {
            log.error("errMsg<aspect get user exception>", ex);
        }
        return person;
    }

    /**
     * 将请求参数转化为JSON字符串
     * @param args
     *              请求参数
     * @return
     */
    public String jsonParams(Object[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object object : args) {
            if (object != null) {
                if (object instanceof MultipartFile
                        || object instanceof ServletRequest
                        || object instanceof ServletResponse
                        || object instanceof BindingResult) {
                    continue;
                }
                stringBuilder.append(JSONObject.toJSON(object).toString());
            }
        }
        return stringBuilder.toString();
    }

    public String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * <p>
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     * <p>
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     * <p>
     * 用户真实IP为： 192.168.1.110
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unkonw".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unkonw".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unkonw".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unkonw".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unkonw".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
