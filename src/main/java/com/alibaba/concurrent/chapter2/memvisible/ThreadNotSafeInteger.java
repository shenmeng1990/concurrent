package com.alibaba.concurrent.chapter2.memvisible;

import lombok.Data;

/**
 * @Author shenmeng
 * @Date 2019/12/1
 **/

public class ThreadNotSafeInteger{

    private Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}

class Test{
    public static void main(String[] args) {
        ThreadNotSafeInteger t = new ThreadNotSafeInteger();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                t.setValue(1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(t.getValue());
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                t.setValue(2);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(t.getValue());
            }
        });

        t1.start();
        t2.start();
    }
}


