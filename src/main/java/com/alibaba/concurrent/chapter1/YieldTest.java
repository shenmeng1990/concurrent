package com.alibaba.concurrent.chapter1;

/**
 * @Author shenmeng
 * @Date 2019/11/28
 **/

public class YieldTest implements Runnable{

    private String name;

    YieldTest(String name){
        this.name=name;
        Thread t = new Thread(this,this.name);
        t.start();
    }

    @Override
    public void run() {
        for(int i=0;i<5;i++){
            if((i%5)==0){
                System.out.println(Thread.currentThread().getName()+" yield cpu!");
                Thread.yield();
            }
        }
        System.out.println(Thread.currentThread().getName()+" is over!");
    }

    public static void main(String[] args) {
        new YieldTest("t1");
        new YieldTest("t2");
        new YieldTest("t3");
    }
}
