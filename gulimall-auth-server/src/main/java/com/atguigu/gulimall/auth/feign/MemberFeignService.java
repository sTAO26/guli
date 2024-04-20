package com.atguigu.gulimall.auth.feign;

import com.atguigu.common.to.member.MemberUserRegisterTO;
import com.atguigu.common.to.member.SocialUserTO;
import com.atguigu.common.to.member.SocialUserTO;
import com.atguigu.common.utils.R;
import com.atguigu.common.vo.auth.SocialUserVO;
import com.atguigu.common.vo.auth.UserLoginVO;
import com.atguigu.common.vo.auth.UserRegisterVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 会员服务
 *
 */
@FeignClient("gulimall-member")
public interface MemberFeignService {

    /**
     * 注册
     */
    @PostMapping("/member/member/regist")
    R regist(@RequestBody UserRegisterVO user);

    /**
     * 登录
     */
    @PostMapping("/member/member/login")
    R login(@RequestBody UserLoginVO vo);

    /**
     * 社交登录
     */
    @PostMapping("/member/member/social/oauth2/login")
    R oauthLogin(@RequestBody SocialUserTO user);


}