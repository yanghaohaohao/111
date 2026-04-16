package org.example.springboot3.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component//交给spring管理，这是一个登录拦截器
public class LoginInterceptor implements HandlerInterceptor {

    @Override//在请求之前的拦截，检查是否登录
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        //从Session中读取userid
        //Integer userId = (Integer) request.getSession().getAttribute("userId");
        /*
        if (userId == null) {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(401);
            response.getWriter().write("{\"code\":401,\"msg\":\"请求失败，未登录\",\"data\":null}");
            return false;
        }


        //如果已经登录，则把userid存入ThreadLocal里
        UserContext.setCurrentUser(userId);

        //不用检查，直接把role放入ThreadLocal，后续再检查
        String role = (String) request.getSession().getAttribute("role");
        UserContext.setCurrentRole(role);
        return true;*/


        //token方案
        String token = request.getHeader("token");
        if (token == null||token.isEmpty()) {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(401);
            response.getWriter().write("{\"code\":401,\"msg\":\"请求失败，未登录\",\"data\":null}");
            return false;
        }
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        try {
            Integer userId = JwtUtil.getUserId(token);
            String role = JwtUtil.getRole(token);

            UserContext.setCurrentUser(userId);
            UserContext.setCurrentRole(role);
            return true;
        } catch (Exception e) {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(401);
            response.getWriter().write("{\"code\":401,\"msg\":\"Token无效或已过期\",\"data\":null}");
            return false;
        }

    }

    @Override//在请求之后的拦截，一般用于销毁threadLocal
    public void afterCompletion(final HttpServletRequest request,
                                final HttpServletResponse response,
                                final Object handler,
                                final Exception ex) throws Exception {
        UserContext.removeThreadId();
    }


}
