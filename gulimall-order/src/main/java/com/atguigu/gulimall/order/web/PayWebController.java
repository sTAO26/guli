package com.atguigu.gulimall.order.web;

import com.atguigu.common.constant.order.PaymentConstant;
import com.atguigu.common.constant.order.PaymentConstant.PayBusinessDetailType;
import com.atguigu.common.constant.order.PaymentConstant.PayType;
import com.atguigu.common.vo.order.PayVO;
import com.atguigu.gulimall.order.config.AliPayConfig;
import com.atguigu.gulimall.order.service.impl.OrderServiceImpl;
import com.atguigu.gulimall.order.service.impl.PayContextStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 */
@Slf4j
@Controller
public class PayWebController {

    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    PayContextStrategy payContextStrategy;
    @Autowired
    AliPayConfig aliPayConfig;

    /**
     * 创建支付
     * 返回text/html页面
     * @param orderSn       订单号
     *
     */
    @ResponseBody
    @GetMapping(value = "/html/pay", produces = "text/html")
    public String htmlPayOrder(@RequestParam(value = "orderSn", required = false) String orderSn,
                               @RequestParam(value = "payCode", required = true) Integer payCode,
                               @RequestParam(value = "businessCode", required = true) Integer businessCode) throws Exception {
        // 获取支付类型
        PayType payType = PayType.getByCode(payCode);
        // 获取业务类型
        PayBusinessDetailType businessDetailType = PayBusinessDetailType.getByCodeAndBusinessCode(
                payType.getCode(), businessCode);

        // 获取订单信息，构造参数
        PayVO order = orderService.getOrderPay(orderSn);
        order.setNotify_url(PaymentConstant.SYSTEM_URL + businessDetailType.getNotifyUrl());// 封装异步回调地址
        order.setReturn_url(businessDetailType.getReturnUrl());// 封装同步回调地址

        // 请求策略方法
        String html = payContextStrategy.pay(payType, order);
        return html;
    }

//<form name="punchout_form" method="post" action="https://openapi.alipaydev.com/gateway.do?charset=utf-8&method=alipay.trade.page.pay&sign=bp2Aynrkos5M%2FirlC9UTNdTWMcRP0Weh1wje0W6QNHJVrDxb%2FCSh%2Ff6tAs9v0RrwlX24K7lthUUm46cbKRfjTkbj%2FJxw9K0B7iPfe%2BdtggoGN7w9%2BDfrvp7n8kACkPbMZGMms8lZXhLIEAqItyV3rAnOtP0OFMGG0MFlSemjSoiXCZU5oPqVKV9tlh%2B1gEQ0hea68gb15s%2FeYohs1htDBx9uZUeozb32DMo0YFnwCXwNUxv1MGdVBDztVh%2FNl48Uo5Bm9sInTXOrGFZCMhZtdHmK4aO2OVZIBbbchj3NUr3AIoeR%2FW7MLTxhK9NNVOVF9pDy5VOgs5tNtUip5e870w%3D%3D&return_url=http%3A%2F%2Fmember.gulimall.com%2FmemberOrder.html&notify_url=http%3A%2F%2F88x386a733.zicp.fun%2Fpayed%2Fali%2Fnotify&version=1.0&app_id=9021000135619123&sign_type=RSA2&timestamp=2024-03-13+01%3A14%3A55&alipay_sdk=alipay-sdk-java-dynamicVersionNo&format=json">
//<input type="hidden" name="biz_content" value="{&quot;out_trade_no&quot;:&quot;202403130108502021767598637396815873&quot;,&quot;total_amount&quot;:&quot;18903.00&quot;,&quot;subject&quot;:&quot;华为 HUAWEI Mate 30 Pro 星河银 8GB+256GB麒麟990旗舰芯片OLED环幕屏双4000万徕卡电影四摄4G全网通手机&quot;,&quot;body&quot;:&quot;颜色:星河银;版本:8GB+256GB&quot;,&quot;timeout_express&quot;:&quot;null&quot;,&quot;product_code&quot;:&quot;FAST_INSTANT_TRADE_PAY&quot;}">
//<input type="submit" value="立即支付" style="display:none" >
//</form>
//<script>document.forms[0].submit();</script>
}
