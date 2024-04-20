package com.atguigu.gulimall.auth.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.atguigu.common.constant.auth.AuthConstant;
import com.atguigu.common.to.member.SocialUserTO;
import com.atguigu.common.utils.HttpUtils;
import com.atguigu.common.utils.R;
import com.atguigu.common.vo.auth.MemberResponseVO;
import com.atguigu.common.vo.auth.SocialUserVO;
import com.atguigu.gulimall.auth.agent.MemberAgentService;
import com.atguigu.gulimall.auth.feign.MemberFeignService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 社交登录
 */
@Slf4j
@Controller
public class OAuth2Controller {

    @Autowired
    MemberAgentService memberAgentService;
    @Autowired
    MemberFeignService memberFeignService;

    /**
     * giteee登录授权
     *     用户同意授权 使用 code 获取 token，使用 token 获取 用户数据
     *      http://auth.gulimall.com/oauth/gitee/success
     * @param code
     * @return
     * @throws Exception
     */
    @GetMapping("/oauth/gitee/success")
    public String gitee(@RequestParam("code") String code, HttpSession session, HttpServletResponse servletResponse) throws Exception {
        // 准备请求参数
        Map<String,String> params = new HashMap<>();
        params.put("client_id","c2f5ab27ecf622c28698f08b314ced7041dace33cff06e26498a9bbe1880aa82");
        params.put("redirect_uri","http://auth.gulimall.com/oauth/gitee/success");
        params.put("client_secret","2c71e92bc554140fd8c8f89c9aeafd3c8caf33b5d60b42130e7a29eed319f839");
        params.put("code",code);
        params.put("grant_type","authorization_code");
        Map<String, String> headers = new HashMap<>();
        Map<String, String> querys = new HashMap<>();
        // 获取accesstoken
        HttpResponse response = HttpUtils.doPost("https://gitee.com", "/oauth/token", headers, querys, params);

        if (response.getStatusLine().getStatusCode() == 200) {//获取到token

            String jsonString = EntityUtils.toString(response.getEntity());
            SocialUserVO user = JSONObject.parseObject(jsonString, SocialUserVO.class);

            // gitee 在post成功后的返回数据不带有uid。所以还需要拿token再向gitee get一次来获取唯一标识id
            Map<String, String> queryMap = new HashMap<>();
            queryMap.put("access_token", user.getAccess_token());
            HttpResponse res= HttpUtils.doGet("https://gitee.com", "/api/v5/user", new HashMap<String, String>(), queryMap);
            if (res.getStatusLine().getStatusCode() == 200) {
                //查询成功
                String json = EntityUtils.toString(res.getEntity());
                JSONObject jsonObject = JSON.parseObject(json);
                String id = jsonObject.getString("id");
                user.setUid(id);
            }else {
                return "redirect:http://auth.gulimall.com/login.html";
            }

            // 首次登录自动注册（为当前社交登录用户生成一个会员账号信息，以后这个社交账户就会对应指定的会员）
            // 非首次登录则直接登录成功
            R r = memberAgentService.oauthLogin(user);
          //  R r = memberFeignService.oauthLogin(user);

            if (r.getCode() == 0) {
                // 登录成功
                MemberResponseVO loginUser = r.getData(new TypeReference<MemberResponseVO>() {
                });
                log.info("登录成功：用户：{}", loginUser.toString());

                // 3.信息存储到session中，并且放大作用域（指定domain=父级域名）
                session.setAttribute(AuthConstant.LOGIN_USER, loginUser);
                // 首次使用session时，spring会自动颁发cookie设置domain，所以这里手动设置cookie很麻烦，采用springsession的方式颁发父级域名的domain权限
//                Cookie cookie = new Cookie("JSESSIONID", loginUser.getId().toString());
//                cookie.setDomain("gulimall.com");
//                servletResponse.addCookie(cookie);
                // 跳回首页
                return "redirect:http://gulimall.com";
            } else {
                // 登录失败，调回登录页
                return "redirect:http://auth.gulimall.com/login.html";
            }
        } else {
            // 换取Access_Token失败
            return "redirect:http://auth.gulimall.com/login.html";
        }
    }



    /**
     * 微博登录授权回调页
     *
     * @param code 根据code换取Access Token，且code只能兑换一次Access Token
     */
//    @GetMapping("/oauth2.0/weibo/success")
//    public String weibo(@RequestParam("code") String code, HttpSession session, HttpServletResponse servletResponse) throws Exception {
//        // 1.根据code换取Access Token
//        Map<String, String> headers = new HashMap<>();
//        Map<String, String> querys = new HashMap<>();
//        Map<String, String> map = new HashMap<>();
//        map.put("client_id", "2129105835");
//        map.put("client_secret", "201b8aa95794dbb6d52ff914fc8954dc");
//        map.put("grant_type", "authorization_code");
//        map.put("redirect_uri", "http://auth.gulimall.com/oauth2.0/weibo/success");
//        map.put("code", code);
//        HttpResponse response = HttpUtils.doPost("https://api.weibo.com", "/oauth2/access_token", headers, querys, map);
//
//        // 2.处理请求返回
//        if (response.getStatusLine().getStatusCode() == 200) {
//            // 换取Access_Token成功
//            String jsonString = EntityUtils.toString(response.getEntity());
//            SocialUserVO user = JSONObject.parseObject(jsonString, SocialUserVO.class);
//
//            // 首次登录自动注册（为当前社交登录用户生成一个会员账号信息，以后这个社交账户就会对应指定的会员）
//            // 非首次登录则直接登录成功
//            R r = memberAgentService.oauthLogin(user);
//            if (r.getCode() == 0) {
//                // 登录成功
//                MemberResponseVO loginUser = r.getData(new TypeReference<MemberResponseVO>() {
//                });
//                log.info("登录成功：用户：{}", loginUser.toString());
//
//                // 3.信息存储到session中，并且放大作用域（指定domain=父级域名）
//                session.setAttribute(AuthConstant.LOGIN_USER, loginUser);
//                // 首次使用session时，spring会自动颁发cookie设置domain，所以这里手动设置cookie很麻烦，采用springsession的方式颁发父级域名的domain权限
////                Cookie cookie = new Cookie("JSESSIONID", loginUser.getId().toString());
////                cookie.setDomain("gulimall.com");
////                servletResponse.addCookie(cookie);
//                // 跳回首页
//                return "redirect:http://gulimall.com";
//            } else {
//                // 登录失败，调回登录页
//                return "redirect:http://auth.gulimall.com/login.html";
//            }
//        } else {
//            // 换取Access_Token成功
//            return "redirect:http://auth.gulimall.com/login.html";
//        }
//    }


}