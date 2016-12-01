package com.cxypub.test.thread;

/**
 * @author oaoCoder-徐飞
 * @version V1.0
 * @Title: InterruptTest
 * @Package com.test
 * @Description: @TODO
 * Copyright: Copyright (c) 2015
 * Company:上海追月信息科技有限公司
 * @date 2016/5/31 14:38
 */
public class InterruptTest {
    public static void main(String[] args) throws InterruptedException {
        MyThread t = new MyThread("MyThread");
        t.start();
        Thread.sleep(3000);// 睡眠100毫秒
        t.interrupt();// 中断t线程
        Thread.sleep(3000);// 睡眠100毫秒
        t.interrupt();// 中断t线程
    }
}


class MyThread extends Thread {
    int i = 0;
    public MyThread(String name) {
        super(name);
    }
    public void run() {
        while(!isInterrupted()) {// 当前线程没有被中断，则执行
            System.out.println(getName() + getId() + "执行了" + ++i + "次");
        }
    }
}