package com.atguigu.gulimall.product.feign;

import com.atguigu.common.utils.R;
import com.atguigu.common.vo.order.OrderItemVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 订单系统
 */
@FeignClient("gulimall-order")
public interface OrderFeignService {



    /**
     * 根据用户id查询商品购买次数
     */
    @GetMapping(value = "/order/order/getBuyTime/{memberid}")
    @ResponseBody
    R getBuytime(@PathVariable("memberid") Long memberid);

}
