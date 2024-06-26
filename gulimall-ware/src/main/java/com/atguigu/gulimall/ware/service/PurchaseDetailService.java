package com.atguigu.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.gulimall.ware.entity.PurchaseDetailEntity;

import java.util.List;
import java.util.Map;

/**

 */
public interface PurchaseDetailService extends IService<PurchaseDetailEntity> {

    /**
     * 商品库存（可根据skuId、wareId查找）
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据采购单ID查询采购需求
     */
    List<PurchaseDetailEntity> listDetailByPurchaseId(Long purchaseId);
}

