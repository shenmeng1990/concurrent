package com.alibaba.concurrent.chapter1;

/**
 * @Author shenmeng
 * @Date 2019/11/29
 **/

public class DeadLockTest {

    private static Object resourceA = new Object();

    private static Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA){
                    System.out.println(Thread.currentThread().getName()+" get resourceA");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+" try to get resourceB");
                    synchronized (resourceB){
                        System.out.println(Thread.currentThread().getName()+" get resourceB");
                    }
                }
            }
        },"t1");

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceB){
                    System.out.println(Thread.currentThread().getName()+" get resourceB");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+" try to get resourceA");
                    synchronized (resourceA){
                        System.out.println(Thread.currentThread().getName()+" get resourceA");
                    }
                }
            }
        },"t2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("main is over");

    }

}
