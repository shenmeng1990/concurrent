package com.alibaba.concurrent.chapter1;

/**
 * @Author shenmeng
 * @Date 2019/11/28
 **/

public class InterruptedAndIsInterrupted {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(;;){

                }
            }
        });

        thread.start();

        //设置中断标志
        thread.interrupt();
        //获取中断标志
        System.out.println("isInterrupted:"+thread.isInterrupted());
        //获取中断标志并且重置
        System.out.println("isInterrupted:"+thread.interrupted());
        //获取中断标志并且重置
        System.out.println("isInterrupted:"+Thread.interrupted());
        //获取中断标志
        System.out.println("isInterrupted:"+thread.isInterrupted());

        thread.join();
        System.out.println("main is over");

        /**
         * 第一行输出true能理解，但是下面三行为什么是false、false、ture呢？
         * 不应该是true、false、false吗？
         * 如果你有这样的疑问则说明对着两个函数的区别还是不太清楚。
         * 上面介绍了在interrupted内部调用的是获取当前线程的中断状态，
         * 这里虽然调用的是thead.interrupted()方法，
         * 但是实际是获取的main函数主线程的中断标志，因为主线程是当前线程。
         * 所以threa.interrupted()和Thread.interrupted()的作用是一样的，
         * 目的是获取当前线程的中断标志
         * **/
    }
}
