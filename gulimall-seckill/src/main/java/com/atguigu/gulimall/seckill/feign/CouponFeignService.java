package com.atguigu.gulimall.seckill.feign;

import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 营销模块
 * @Description:
 **/

@FeignClient("gulimall-coupon")
public interface CouponFeignService {

    /**
     * 查询最近三天需要参加秒杀的场次+商品
     */
    @GetMapping(value = "/coupon/seckillsession/Lates3DaySession")
    R getLates3DaySession();

}
