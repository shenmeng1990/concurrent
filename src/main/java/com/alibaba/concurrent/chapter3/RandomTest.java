package com.alibaba.concurrent.chapter3;

import java.util.Random;

/**
 * @Author shenmeng
 * @Date 2019/12/4
 **/

public class RandomTest {

    public static void main(String[] args) {
        //创建一个默认种子的随机数生成器
        Random random = new Random();
        //输出10个在0-5之间的随机数
        for (int i=0;i<5;i++){
            System.out.println(random.nextInt(5));
        }
    }
}
