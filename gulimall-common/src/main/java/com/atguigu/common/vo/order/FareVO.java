package com.atguigu.common.vo.order;

import com.atguigu.common.vo.ware.MemberAddressVO;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class FareVO {
    private MemberAddressVO address;
    private BigDecimal fare;
}
