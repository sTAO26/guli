package com.atguigu.gulimall.ware.service;

import com.atguigu.common.vo.ware.FareVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.gulimall.ware.entity.WareInfoEntity;

import java.util.Map;

/**
 * 仓库信息
 *
 */
public interface WareInfoService extends IService<WareInfoEntity> {

    /**
     * 分页查询仓库
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 获取运费
     * @param addrId 会员收货地址ID
     */
    FareVO getFare(Long addrId);
}

