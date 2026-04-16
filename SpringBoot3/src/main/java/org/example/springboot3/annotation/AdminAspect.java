package org.example.springboot3.annotation;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.springboot3.common.Result;
import org.example.springboot3.common.UserContext;
import org.springframework.stereotype.Component;

@Aspect
@Component//切面
public class AdminAspect {

    //关联自定义注释AdminOnly
    @Around("@annotation(AdminOnly)")
    public Object checkAdmin(ProceedingJoinPoint joinPoint) throws Throwable {
        String role = UserContext.getCurrentRole();
        if (!"admin".equals(role)) {
            return Result.error(403, "无权限访问");
        }
        return joinPoint.proceed();
    }
}
