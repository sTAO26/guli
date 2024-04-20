package com.atguigu.common.to.ware;

import com.atguigu.common.vo.ware.OrderItemVO;
import lombok.Data;

import java.util.List;


@Data
public class WareSkuLockTO {

    private String orderSn;

    /** 需要锁住的所有库存信息 **/
    private List<OrderItemVO> locks;



}
