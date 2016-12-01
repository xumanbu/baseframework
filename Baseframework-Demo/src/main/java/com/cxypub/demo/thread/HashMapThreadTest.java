package com.cxypub.demo.thread;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author oaoCoder-徐飞
 * @version V1.0
 * @Title: HashMapThreadTest
 * @Package com.test
 * @Description: @TODO
 * Copyright: Copyright (c) 2015
 * Company:上海追月信息科技有限公司
 * @date 2016/5/25 17:10
 */
public class HashMapThreadTest {

    public static void main(String[] args) {
        final HashMap<String,String> hashMap = new HashMap<String,String>(2);

        for(int i = 0;i<100000;i++){
            final int b = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                   hashMap.put(UUID.randomUUID().toString(),b+"test");
                }
            }).start();
        }

    }
}
