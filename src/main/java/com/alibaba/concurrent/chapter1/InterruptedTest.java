package com.alibaba.concurrent.chapter1;

/**
 * @Author shenmeng
 * @Date 2019/11/28
 **/

public class InterruptedTest {

    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                //中断标志为true时会退出，并且清除中断标志
                while(!Thread.currentThread().interrupted()){

                }
                System.out.println("thread one isInterrupted:"+Thread.currentThread().isInterrupted());
            }
        });

        one.start();
        one.interrupt();
        one.join();
        System.out.println("main thread is over");
    }

}
