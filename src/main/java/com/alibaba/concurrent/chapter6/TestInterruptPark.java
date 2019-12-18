package com.alibaba.concurrent.chapter6;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author shenmeng
 * @Date 2019/12/6
 **/

public class TestInterruptPark {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("child thread begin park!");
                while(!Thread.currentThread().isInterrupted()){
                    LockSupport.park();
                }
                System.out.println("child thread end park!");
            }
        });

        t1.start();
        Thread.sleep(1000);

        System.out.println("main thread begin unpark");
        //LockSupport.unpark(t1);
        //调用之前t1被阻塞，调用interrupt不会抛出异常，线程返回。
        t1.interrupt();
    }
}
