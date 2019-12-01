package com.alibaba.concurrent.chapter2.memvisible;

/**
 * @Author shenmeng
 * @Date 2019/12/1
 **/

public class ThreadVolatileSafeInteger{

    private volatile Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}

class TestVolatile{
    public static void main(String[] args) {
        ThreadVolatileSafeInteger t = new ThreadVolatileSafeInteger();
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


