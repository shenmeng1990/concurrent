package com.alibaba.concurrent.chapter1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author shenmeng
 * @Date 2019/11/28
 **/

public class SleepTest  {

    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                //获取独占锁
                lock.lock();
                try {
                    System.out.println("threadA is in sleep!");
                    Thread.sleep(10000);
                    System.out.println("threadA is in awaked");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                //获取独占锁
                lock.lock();
                try {
                    System.out.println("threadB is in sleep!");
                    Thread.sleep(10000);
                    System.out.println("threadB is in awaked");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        });

        threadA.start();
        threadB.start();
    }
}
