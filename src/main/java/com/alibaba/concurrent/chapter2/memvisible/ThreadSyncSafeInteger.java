package com.alibaba.concurrent.chapter2.memvisible;

/**
 * @Author shenmeng
 * @Date 2019/12/1
 **/

public class ThreadSyncSafeInteger{

    private Integer value;

    public synchronized  Integer getValue() {
        return value;
    }

    public synchronized void setValue(Integer value) {
        this.value = value;
    }
}

class TestSync{
    public static void main(String[] args) {
        ThreadSyncSafeInteger t = new ThreadSyncSafeInteger();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                t.setValue(1);
                System.out.println(t.getValue());
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                t.setValue(2);
                System.out.println(t.getValue());
            }
        });

        t1.start();
        t2.start();
    }
}


