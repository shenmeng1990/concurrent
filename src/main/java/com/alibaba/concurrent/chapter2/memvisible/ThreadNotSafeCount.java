package com.alibaba.concurrent.chapter2.memvisible;

/**
 * @Author shenmeng
 * @Date 2019/12/1
 **/

public class ThreadNotSafeCount {

    private long value;

    public synchronized Long getCount(){
        return value;
    }

    public synchronized void inc(){
        ++value;
    }
}
