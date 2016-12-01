package com.cxypub.demo.sort;

/**
 * @author oaoCoder-徐飞
 * @version V1.0
 * @Title: BubbleTest
 * @Package com.test
 * @Description: @TODO
 * Copyright: Copyright (c) 2015
 * Company:上海追月信息科技有限公司
 * @date 2016/5/25 10:09
 */
public class BubbleTest {
	public static void main(String[] args) {
		int score[] = { 67, 69, 75, 87, 89, 90, 99, 100 };

		for (int j = 0; j < score.length - 1; j++) {
			for (int i = 0; i < score.length - 1; i++) {
				if (score[i] < score[i + 1]) {
					int temp = score[i];
					score[i] = score[i + 1];
					score[i + 1] = temp;
				}
			}
            System.out.print("第" + (j + 1) + "次排序结果：");
            for(int a = 0; a < score.length; a++){
                System.out.print(score[a] + "\t");
            }
            System.out.println("");
        }

		System.out.print("最终排序结果：");
		for (int a = 0; a < score.length; a++) {
			System.out.print(score[a] + "\t");
		}
	}
}
