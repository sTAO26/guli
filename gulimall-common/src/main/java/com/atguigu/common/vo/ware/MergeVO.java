package com.atguigu.common.vo.ware;

import lombok.Data;

import java.util.List;



@Data
public class MergeVO {

    private Long purchaseId;// 采购单ID
    private List<Long> items;// 采购需求ID

}
