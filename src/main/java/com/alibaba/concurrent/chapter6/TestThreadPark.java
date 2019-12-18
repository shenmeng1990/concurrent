package com.alibaba.concurrent.chapter6;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author shenmeng
 * @Date 2019/12/6
 **/

public class TestThreadPark {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 =new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("child thread begin park");
                //调用park方法，挂起自己
                LockSupport.park();
                System.out.println("child thread end park");
            }
        });

        t1.start();
        //主线程休眠1s，目的为了让子线程挂起
        Thread.sleep(1000);
        System.out.println("main thread begin unpark");
        //调用unpark方法让子线程持有许可证，然后park方法返回。
        LockSupport.unpark(t1);
    }
}
