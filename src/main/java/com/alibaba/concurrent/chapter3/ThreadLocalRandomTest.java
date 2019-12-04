package com.alibaba.concurrent.chapter3;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author shenmeng
 * @Date 2019/12/4
 **/

public class ThreadLocalRandomTest {
    public static void main(String[] args) {
        //获取一个随机数生成器
        ThreadLocalRandom random = ThreadLocalRandom.current();
        //输出10个0-5之间的随机数
        for(int i=0;i<10;i++){
            System.out.println(random.nextInt(5));
        }
    }
}
