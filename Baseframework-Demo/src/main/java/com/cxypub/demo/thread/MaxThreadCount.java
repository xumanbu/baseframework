package com.cxypub.demo.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author oaoCoder-徐飞
 * @version V1.0
 * @Title: MaxThreadCount
 * @Package com.cxypub.demo.thread
 * @Description: @TODO
 * Copyright: Copyright (c) 2015
 * Company:上海追月信息科技有限公司
 * @date 2016/6/24 9:58
 */
public class MaxThreadCount {

    private static final AtomicInteger count = new AtomicInteger();

    public static void main(String[] args) {
        while (true){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    run();
                }
            }).start();
        }

    }

    public static void run() {
        System.out.println(count.incrementAndGet());

        while (true)
            try {
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                break;
            }
    }
}
