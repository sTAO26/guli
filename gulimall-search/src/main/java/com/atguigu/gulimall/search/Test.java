package com.atguigu.gulimall.search;

import java.util.HashMap;

/**
 */
public class Test {
    public static void main(String[] args) {
        Long l1 = new Long(600);
        Long l2 = new Long(600);
        HashMap<Long, String> map = new HashMap<>();
        String put = map.put(l1, "1");
        System.out.println(map.containsKey(l2));
        System.out.println(l1.equals(l2));
        System.out.println(l1 == l2);
    }
}