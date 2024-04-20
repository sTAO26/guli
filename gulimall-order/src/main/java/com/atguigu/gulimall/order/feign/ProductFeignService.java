package com.atguigu.gulimall.order.feign;

import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 商品系统

 */
@FeignClient("gulimall-product")
public interface ProductFeignService {

    /**
     * 根据skuId查询spu的信息
     * @param skuId
     * @return
     */
    @GetMapping(value = "/product/spuinfo/skuId/{skuId}")
    R getSpuInfoBySkuId(@PathVariable("skuId") Long skuId);


    /**
     * 根据skuId查询sku的信息
     * @param skuId
     * @return
     */
    @GetMapping(value = "/product/skuinfo/info/{skuId}")
    R getSkuInfoBySkuId(@PathVariable("skuId") Long skuId);

}