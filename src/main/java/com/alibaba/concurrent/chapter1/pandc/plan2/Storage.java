package com.alibaba.concurrent.chapter1.pandc.plan2;

import com.alibaba.concurrent.chapter1.pandc.Apple;
import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author shenmeng
 * @Date 2019/11/26
 **/

public class Storage {

    private  static final Integer MAX_SIZE=10;

    private LinkedList<Apple> list = Lists.newLinkedList();

    private final Lock lock = new ReentrantLock();

    // 仓库满的条件变量
    private final Condition full = lock.newCondition();
    // 仓库空的条件变量
    private final Condition empty = lock.newCondition();


    public void produce(){
        lock.lock();
            while(list.size()>=MAX_SIZE){
                System.out.println("[生产者"+Thread.currentThread().getName()+"仓库已满]");
                try {
                    full.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(new Apple());
            System.out.println("[生产者"+Thread.currentThread().getName()+"生产了一个苹果],现在的库存为："+list.size());
            empty.signalAll();
        lock.unlock();
    }

    public void consume(){
        lock.lock();
            while(list.size()==0){
                System.out.println("[消费者"+Thread.currentThread().getName()+"仓库为空]");
                try {
                    empty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.remove();
            System.out.println("[消费者"+Thread.currentThread().getName()+"消费了一个苹果],现在库存为："+list.size());
            full.signalAll();
            lock.unlock();

    }

}
