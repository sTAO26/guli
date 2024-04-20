package com.atguigu.common.vo.auth;

import lombok.Data;

/**
 * 授权结果VO
 * 使用code换取Access Token返回结果
 */
@Data
public class SocialUserVO {
    private String access_token;//令牌
    private long expires_in;// 令牌过期时间
    private String token_type; //令牌类型
    private String uid;//用户唯一标识
}
