package com.atguigu.common.to.member;

import lombok.Data;

/**
 * 社交登录TO
 */
@Data
public class SocialUserTO {
    // 社交账户信息
    private String accessToken;
    private long expiresIn;
    private String uid;
    private String tokenType;


//    /**
//     * 令牌
//     */
//    private String access_token;
//
//
//    /**
//     * 令牌过期时间
//     */
//    private long expires_in;
//
//    /**
//     * 该社交用户的唯一标识
//     */
//    private String id;
//
//
//    /**
//     * 第三方用户名称
//     */
//    private String name;
//
//    /**
//     * 头像
//     */
//    private String  avatar_url;


}
