package com.atguigu.gulimall.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.atguigu.common.constant.seckill.SeckillConstant;
import com.atguigu.common.entity.order.OrderEntity;
import com.atguigu.common.entity.order.OrderItemEntity;
import com.atguigu.common.entity.product.SkuInfoEntity;
import com.atguigu.common.to.cart.UserInfoTO;
import com.atguigu.common.to.product.BuyTimeTO;
import com.atguigu.common.to.seckill.SeckillSkuRedisTO;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;
import com.atguigu.common.utils.R;
import com.atguigu.common.vo.auth.MemberResponseVO;
import com.atguigu.gulimall.product.feign.MemberFeignService;
import com.atguigu.gulimall.product.feign.OrderFeignService;
import com.atguigu.gulimall.product.interceptor.LoginUserInterceptor;
import com.atguigu.gulimall.product.interceptor.ProductInterceptor;
import com.atguigu.gulimall.product.service.SkuInfoService;
import com.atguigu.gulimall.product.service.UserCFService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static com.atguigu.common.constant.seckill.SeckillConstant.SECKILL_CHARE_KEY;

@Slf4j
@Service
public class UserCFServiceImpl implements UserCFService {

    @Autowired
    SkuInfoService skuInfoService;
    @Autowired
    MemberFeignService memberFeignService;
    @Autowired
    OrderFeignService orderFeignService;


    /**
     * 获取推荐商品列表
     */
    @Override
    public List<SkuInfoEntity> getRecommendSkus(){
        List<SkuInfoEntity> recommend= new ArrayList<>();
        // 获取登录用户
        UserInfoTO user = ProductInterceptor.user;

        if(user==null){
            System.out.println("用户未登录，随机生成推荐商品！");
            recommend.addAll(skuInfoService.list(new QueryWrapper<SkuInfoEntity>().eq("spu_id", 36)));
            recommend.addAll(skuInfoService.list(new QueryWrapper<SkuInfoEntity>().eq("spu_id", 33)));
            return recommend;
        }


        //获取所有用户id列表
        List<Long> memberIds= memberFeignService.getMemberIds();
        //获取所有商品id列表
        List<Long> skuIds = skuInfoService.skuids();

        HashMap<Long,HashMap<Long,Integer>> matrix = new HashMap<>();
        // 获取所有用户的订单，构建用户-商品矩阵
        for(Long id : memberIds){
            HashMap<Long,Integer> temp = new HashMap<>();
            for(Long sku : skuIds){
                temp.put(sku,0);
            }

            // 查询购买过的商品列表和次数 (设置订单服务不对该请求进行登录拦截！！！）
            R r = orderFeignService.getBuytime(id);
            List<BuyTimeTO> buyTimeTOList = r.getData(new TypeReference<List<BuyTimeTO>>(){});
            for(BuyTimeTO buyTimeTO : buyTimeTOList){
                Long skuid = buyTimeTO.getSkuId();
                Integer time = buyTimeTO.getTime();
                temp.put(skuid,time + temp.get(skuid));
            }

            matrix.put(id,temp);
        }
        System.out.println("matrixxxxxxx:::"+matrix);

        //计算目标用户与其他用户的相似度
        HashMap<Long,Double> similarMap = new HashMap<>();
        for(int j = 0;j<memberIds.size();j++){
            if(memberIds.get(j) == user.getUserId())
                continue;
            HashMap<Long,Integer> imap = matrix.get(user.getUserId());
            HashMap<Long,Integer> jmap = matrix.get(memberIds.get(j));
            //两个用户之间的相似度
            double similarity = 0.0;
            //余弦相似度公式中的分子
            double molecule = 0.0;
            //余弦相似度公式中的分母
            double denminator = 1.0;
            // 余弦相似度公式中分母根号下的两个向量的模的值
            double vector1 = 0.0;
            double vector2 = 0.0;
            for(Long skuid : skuIds){
                Integer itime = imap.get(skuid);
                Integer jtime = jmap.get(skuid);
                //计算分子
                molecule += itime*jtime;
                //累计分母中的两个向量的模
                vector1 += Math.pow(itime,2);
                vector2 += Math.pow(jtime,2);
            }
            //计算分母
            denminator = Math.sqrt(vector1)+Math.sqrt(vector2);
            //计算相似度
            similarity = molecule / denminator;
            similarMap.put(memberIds.get(j),similarity);
        }

        // 相似度排序
        List<Map.Entry<Long, Double>> list = new ArrayList<Map.Entry<Long, Double>>(similarMap.entrySet()); //转换为list
        Collections.sort(list, new Comparator<Map.Entry<Long, Double>>() {
            @Override
            public int compare(Map.Entry<Long, Double> o1, Map.Entry<Long, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        System.out.println(list);

        //将相似度最高的用户商品进行推荐?    相似度与商品购买次数乘积后排序?
        Long MostSimUser = list.get(0).getKey();
        List<Long> recommendids = new ArrayList<>();
        for (Long skuid : skuIds){
            //剔除目标用户买过的
            if (matrix.get(MostSimUser).get(skuid)!=0 && matrix.get(user.getUserId()).get(skuid)==0)
            {
                recommendids.add(skuid);
//                skuInfoService.get
            }
        }

        System.out.println(recommendids);
        recommend.addAll(skuInfoService.getByIds(recommendids));
        Collections.reverse(recommend);
        return recommend;
    }


}
