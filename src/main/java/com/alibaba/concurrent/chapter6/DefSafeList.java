package com.alibaba.concurrent.chapter6;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author shenmeng
 * @Date 2019-12-18
 **/
public class DefSafeList {

    public static class ReentrantLockList{

        //线程不安全的list
        private List<String> array= Lists.newArrayList();

        //独占锁
        private volatile ReentrantLock lock = new ReentrantLock();

        //添加元素
        public void add(String e){
            lock.lock();
            try{
                array.add(e);
            }finally {
                lock.unlock();
            }
        }

        //删除元素
        public void remove(String e){
            lock.lock();
            try{
                array.remove(e);
            }finally {
                lock.unlock();
            }
        }

        //获取数据
        public String get(int index){
            lock.lock();
            try{
                return array.get(index);
            }finally {
                lock.unlock();
            }
        }
    }
}
