package com.atguigu.gulimall.auth.agent;

import com.atguigu.common.to.member.SocialUserTO;
import com.atguigu.common.to.member.SocialUserTO;
import com.atguigu.common.utils.R;
import com.atguigu.common.vo.auth.SocialUserVO;
import com.atguigu.common.vo.auth.SocialUserVO;
import com.atguigu.gulimall.auth.feign.MemberFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class MemberAgentService {

    @Autowired
    MemberFeignService memberFeignService;

    /**
     * 将socialuservo转为socialuserto？
     * @param user
     * @return
     */
    public R oauthLogin(SocialUserVO user) {
        SocialUserTO param = new SocialUserTO();
        param.setAccessToken(user.getAccess_token());
        param.setExpiresIn(user.getExpires_in());
        param.setTokenType(user.getToken_type());
        param.setUid(user.getUid());
        return memberFeignService.oauthLogin(param);
    }
}
