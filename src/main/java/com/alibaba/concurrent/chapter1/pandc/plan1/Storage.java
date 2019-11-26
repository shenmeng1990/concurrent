package com.alibaba.concurrent.chapter1.pandc.plan1;

import com.google.common.collect.Lists;

import java.util.LinkedList;

/**
 * @Author shenmeng
 * @Date 2019/11/26
 **/

public class Storage {

    private  static final Integer MAX_SIZE=10;

    private LinkedList<Apple> list = Lists.newLinkedList();

    public void produce(){
        synchronized (list){
            while(list.size()>=MAX_SIZE){
                System.out.println("[生产者"+Thread.currentThread().getName()+"仓库已满]");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(new Apple());
            System.out.println("[生产者"+Thread.currentThread().getName()+"生产了一个苹果],现在的库存为："+list.size());
            list.notifyAll();
        }
    }

    public void consume(){
        synchronized (list){
            while(list.size()==0){
                System.out.println("[消费者"+Thread.currentThread().getName()+"仓库为空]");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.remove();
            System.out.println("[消费者"+Thread.currentThread().getName()+"消费了一个苹果],现在库存为："+list.size());
            list.notifyAll();
        }
    }
}
