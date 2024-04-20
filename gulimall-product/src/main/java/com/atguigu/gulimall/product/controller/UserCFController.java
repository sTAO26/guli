package com.atguigu.gulimall.product.controller;

import com.atguigu.common.entity.product.SkuInfoEntity;
import com.atguigu.common.utils.R;

import com.atguigu.gulimall.product.service.UserCFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 商品推荐-基于用户的协同过滤算法

 */
@RestController
@RequestMapping("product/recommend")
public class UserCFController {

    @Autowired
    private UserCFService userCFService;

    /**
     * 查询推荐商品列表
     */
    @GetMapping(value = "/getRecommendSkus")
    @ResponseBody
    public R getRecommendSkus() {
        List<SkuInfoEntity> results = userCFService.getRecommendSkus();
        return R.ok().setData(results);
    }

}
