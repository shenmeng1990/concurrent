package com.alibaba.concurrent.chapter1;

/**
 * @Author shenmeng
 * @Date 2019/11/28
 **/

public class SleepInterruptTest {

    public static void main(String[] args) {

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                System.out.println("threadA begin to sleep");
                    Thread.sleep(10000);
                    System.out.println("threadA end sleep");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadA.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //主线程中断子线程
        threadA.interrupt();
    }
}
