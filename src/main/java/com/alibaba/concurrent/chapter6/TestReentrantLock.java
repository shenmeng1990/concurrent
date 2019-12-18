package com.alibaba.concurrent.chapter6;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author shenmeng
 * @Date 2019/12/12
 **/

public class TestReentrantLock {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        lock.lock();
        lock.lock();
        lock.unlock();
        lock.unlock();
    }
}
