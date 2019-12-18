package com.alibaba.concurrent.chapter6;

import com.alibaba.concurrent.chapter1.pandc.Apple;


import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author shenmeng
 * @Date 2019/12/12
 **/

public class TestNonReentrantLock {

    private final static Lock lock = new NonReentrantLock();
    private final static Condition full=lock.newCondition();
    private final static Condition empty=lock.newCondition();

    final static Queue<Apple> queue=new
            LinkedBlockingQueue<>();
    final static int queueSize=10;

    public static void main(String[] args) throws InterruptedException {

        Thread producer=new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.lock();
                    while(queue.size()==queueSize){
                        System.out.println("[生产者"+Thread.currentThread().getName()+"仓库已满]");
                        try {
                            full.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.add(new Apple());
                    System.out.println("[生产者"+Thread.currentThread().getName()+"生产了一个苹果],现在的库存为："+queue.size());
                    empty.signalAll();
                    lock.unlock();
                }
            }
        });



        Thread consumer=new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.lock();
                    while(queue.size()==0){
                        System.out.println("[消费者"+Thread.currentThread().getName()+"仓库为空]");
                        try {
                            empty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.poll();
                    System.out.println("[消费者"+Thread.currentThread().getName()+"消费了一个苹果],现在库存为："+queue.size());
                    full.signalAll();
                    lock.unlock();
                }
            }
        });

        producer.start();
        consumer.start();

    }
}
