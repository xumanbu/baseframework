package com.cxypub.test.thread;

/**
 * @author oaoCoder-徐飞
 * @version V1.0
 * @Title: YieldTest
 * @Package com.test
 * @Description: @TODO
 * Copyright: Copyright (c) 2015
 * Company:上海追月信息科技有限公司
 * @date 2016/5/31 14:47
 */
public class YieldTest {
    public static void main(String[] args) throws InterruptedException {
        // 创建线程对象
        YieldThread t1 = new YieldThread("t1");
        YieldThread t2 = new YieldThread("t2");
        // 启动线程
        t1.start();
        t2.start();
        // 主线程休眠100毫秒
        Thread.sleep(100);
        // 终止线程
        t1.interrupt();
        t2.interrupt();
    }
}

class YieldThread extends Thread {
    int i = 0;
    public YieldThread(String name) {
        super(name);
    }
    public void run() {
        while(!isInterrupted()) {
            System.out.println(getName() + "执行了" + ++i + "次");
            if(i % 10 == 0) {// 当i能对10整除时，则让步
                Thread.yield();
            }
        }
    }
}