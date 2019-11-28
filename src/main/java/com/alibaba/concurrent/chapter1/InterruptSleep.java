package com.alibaba.concurrent.chapter1;

/**
 * @Author shenmeng
 * @Date 2019/11/28
 **/

public class InterruptSleep {

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                System.out.println("threadA begin sleep for 200s");
                    Thread.sleep(200000);
                    System.out.println("threadA awaking");
                } catch (InterruptedException e) {
                    System.out.println("threadA is interrupted while sleeping");
                    return;
                }

                System.out.println("threadOne-leaving normally");
            }
        });

        threadA.start();

        //确保子线程进入睡眠
        Thread.sleep(1000);

        //打断子线程的休眠，让子线程从sleep函数返回
        threadA.interrupt();

        //等待子线程执行完毕
        threadA.join();

        System.out.println("main is over");
    }

}
