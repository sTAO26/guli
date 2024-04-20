package com.atguigu.gulimall.product.interceptor;

import com.atguigu.common.constant.auth.AuthConstant;
import com.atguigu.common.constant.cart.CartConstant;
import com.atguigu.common.to.cart.UserInfoTO;
import com.atguigu.common.vo.auth.MemberResponseVO;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * 判断用户登录状态，并封装用户信息传递给controller

 */
public class ProductInterceptor implements HandlerInterceptor {

    public static ThreadLocal<UserInfoTO> loginUser = new ThreadLocal<>();
    public static UserInfoTO user = new UserInfoTO();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("调用product拦截器prehanle！！！");
        // 获取会话信息，获取登录用户信息
        HttpSession session = request.getSession();
        MemberResponseVO attribute = (MemberResponseVO) session.getAttribute(AuthConstant.LOGIN_USER);
        // 判断是否登录，并封装User对象给controller使用
//        UserInfoTO user = new UserInfoTO();
        if (attribute != null) {
            // 登录状态，封装用户ID，供controller使用
            user.setUserId(attribute.getId());
        }

        // 封装用户信息（登录状态userId非空，游客状态userId空）
//        loginUser.set(user);
        return true;
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        UserInfoTO user = threadLocal.get();
//        if (user != null && !user.isTempUser()) {
//            // 需要为客户端分配游客信息
//            Cookie cookie = new Cookie(CartConstant.TEMP_USER_COOKIE_NAME, user.getUserKey());
//            cookie.setDomain("gulimall.com");// 作用域
//            cookie.setMaxAge(CartConstant.TEMP_USER_COOKIE_TIMEOUT);// 过期时间
//            response.addCookie(cookie);
//        }
//    }
}