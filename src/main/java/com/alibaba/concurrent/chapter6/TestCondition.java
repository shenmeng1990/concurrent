package com.alibaba.concurrent.chapter6;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * AQS条件变量的支持
 * @Author shenmeng
 * @Date 2019/12/10
 **/

/**
 * 1：当多个线程调用lock.lock()方法获取锁时，只有一个线程获取到锁，
 * 其他线程会被转换为Node节点插入到Lock锁对应的AQS阻塞队列中。并做自旋CAS尝试获取锁。
 * 2：如果获取到锁的线程有调用了对应条件变量的await方法，则该线程会释放获取到的锁。
 * 并被转换为node节点插入到条件变量对应的条件队列里面。
 * 3：当另外一个线程调用条件变量的signal或者signalAll方法时，会把条件队列里面的一个
 * 或者全部的Node节点移动到AQS的阻塞队列里面，等待实际获取锁。
**/

public class TestCondition {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock=new ReentrantLock();
        /**
        *其实new了一个在AQS内部声明的ConditionObject对象，
         * ConditionObject是AQS的内部类，可以访问AQS内部的变量和方法
         * 在每个条件变量内部都维护了一个条件队列，
         * 用来存放调用条件变量的await方法时被阻塞的线程。
        **/
        Condition condition = lock.newCondition();

        Thread t1 =new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("begin wait!");
                    /**
                    * 当前线程调用条件变量的await方法时，
                     * 在内部会构造一个类型为Node.CONDITION的node节点，
                     * 然后将该节点插入条件队列末尾，
                     * 之后当前线程会释放获取到的锁，并被阻塞挂起。
                    **/
                    condition.await();
                    System.out.println("end wait!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("release lock");
                    lock.unlock();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try{
                    System.out.println("begin signal");
                    /**
                     * 当另外一个线程调用条件变量的signal方法时，
                     * 在内部会吧条件队列里面队头的一个线程节点从条件队列里面移除
                     * 并放入AQS的阻塞队列里面，然后激活这个线程。
                    **/
                    condition.signal();
                    System.out.println("end signal");
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        });

        t1.start();
        Thread.sleep(1000);
        t2.start();

    }
}
