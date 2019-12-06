package com.alibaba.concurrent.chapter4;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author shenmeng
 * @Date 2019/12/5
 **/

public class TestAtomic {
    //创建Long型原子计数器
    private static AtomicLong atomicLong = new AtomicLong();
    //创建数据源
    private static Integer[] arrayOne=new Integer[]{0,1,2,3,0,5,6,0,56,0};
    private static Integer[] arrayTwo=new Integer[]{10,2,3,4,0,5,6,0,56,0};

    public static void main(String[] args) throws InterruptedException {
        //线程t1统计数组arrayOne中0的个数
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int size=arrayOne.length;
                for(int i=0;i<size;i++){
                    if(arrayOne[i].intValue()==0){
                        atomicLong.incrementAndGet();
                    }
                }
            }
        });

        //线程t2统计数组arrayOne中0的个数
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int size=arrayTwo.length;
                for(int i=0;i<size;i++){
                    if(arrayTwo[i].intValue()==0){
                        atomicLong.incrementAndGet();
                    }
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("count:"+atomicLong.get());
    }
}
