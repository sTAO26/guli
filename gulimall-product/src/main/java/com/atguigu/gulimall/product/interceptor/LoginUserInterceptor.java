package com.atguigu.gulimall.product.interceptor;

import com.atguigu.common.constant.auth.AuthConstant;
import com.atguigu.common.vo.auth.MemberResponseVO;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 登录拦截器
 * 从session中获取了登录信息（redis中），封装到了ThreadLocal中
 *
 */
//@Component
public class LoginUserInterceptor implements HandlerInterceptor {

    public static ThreadLocal<MemberResponseVO> loginUser = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // 放行无需登录的请求
//        String uri = request.getRequestURI();
//        AntPathMatcher antPathMatcher = new AntPathMatcher();// 匹配器
//        boolean match = antPathMatcher.match("/order/order/getBuyTime/**", uri);// 查询订单消息
//        boolean match1 = antPathMatcher.match("/payed/**", uri);// 支付回调
//        System.out.println("登录拦截请求：" + uri);
//        if (match || match1) {
//            return true;
//        }
//
//        // 获取登录用户信息
//        MemberResponseVO attribute = (MemberResponseVO) request.getSession().getAttribute(AuthConstant.LOGIN_USER);
//        if (attribute != null) {
//            // 已登录，放行
//            // 封装用户信息到threadLocal
//            loginUser.set(attribute);
//            return true;
//        } else {
//            // 未登录，跳转登录页面
//            response.setContentType("application/json;charset=UTF-8");
//            PrintWriter out = response.getWriter();
//            out.println("<script>alert('请先进行登录，再进行后续操作！');location.href='http://auth.gulimall.com/login.html'</script>");
//            // session.setAttribute("msg", "请先进行登录");
//            // response.sendRedirect("http://auth.gulimall.com/login.html");
//            return false;
//        }
        return true;
    }
}