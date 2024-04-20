package com.atguigu.gulimall.thirdpart.service;

/**
 * 短信服务
 */
public interface SmsService {

    /**
     * 发送短信验证码
     * @param phone 电话号码
     * @param code  验证码
     */
    public Boolean sendCode(String phone, String code);

}