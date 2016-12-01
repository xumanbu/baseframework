package com.cxypub.demo.thread;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author oaoCoder-徐飞
 * @version V1.0
 * @Title: ConcurrentHashMapTest
 * @Package com.test
 * @Description: @TODO
 * Copyright: Copyright (c) 2015
 * Company:上海追月信息科技有限公司
 * @date 2016/5/25 16:54
 */
public class ConcurrentHashMapTest {
    private static ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>();
    public static void main(String[] args) {
        new Thread("Thread1"){
            @Override
            public void run() {
                map.put(3, 33);
            }
        };

        new Thread("Thread2"){
            @Override
            public void run() {
                map.put(4, 44);
            }
        };

        new Thread("Thread3"){
            @Override
            public void run() {
                map.put(7, 77);
            }
        };
        System.out.println(map);
    }
}
