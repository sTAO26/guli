package com.atguigu.gulimall.product.feign;

import com.atguigu.common.utils.R;
import com.atguigu.common.vo.order.MemberAddressVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 会员系统
 */
@FeignClient("gulimall-member")
public interface MemberFeignService {

    /**
     * 查询用户列表
     */
    @GetMapping(value = "/member/member/ids")
    List<Long> getMemberIds();
}
