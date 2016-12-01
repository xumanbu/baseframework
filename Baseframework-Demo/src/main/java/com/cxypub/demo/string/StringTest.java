package com.cxypub.demo.string;

/**
 * @author oaoCoder-徐飞
 * @version V1.0
 * @Title: StringTest
 * @Package com.test
 * @Description: @TODO
 * Copyright: Copyright (c) 2015
 * Company:上海追月信息科技有限公司
 * @date 2016/5/24 15:24
 */
public class StringTest {

    public static final String MESSAGE = "taobao";

    public static void main(String[] arg){
        String a = "tao"+"bao";//编译优化，常量池
        String b = "tao"; //常量池
        String c = "bao"; //常量池
        String d = new String("taobao");//堆内存

        System.out.println(a==MESSAGE);
        System.out.println(b+c == MESSAGE);
        System.out.println(d==MESSAGE);
    }

}
