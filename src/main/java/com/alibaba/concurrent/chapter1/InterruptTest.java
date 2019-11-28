package com.alibaba.concurrent.chapter1;

/**
 * @Author shenmeng
 * @Date 2019/11/28
 **/

public class InterruptTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread= new Thread(new Runnable() {
            @Override
            public void run() {
                while(!Thread.currentThread().isInterrupted()){
                    System.out.println(Thread.currentThread()+"hello");
                }
            }
        });

        thread.start();
        Thread.sleep(1);

        System.out.println("main thread interrupt thread");
        thread.interrupt();

        thread.join();

        System.out.println("main is over!");
    }
}
