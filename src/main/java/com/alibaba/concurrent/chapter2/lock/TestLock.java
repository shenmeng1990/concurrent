package com.alibaba.concurrent.chapter2.lock;

/**
 * @Author shenmeng
 * @Date 2019/12/3
 **/

public class TestLock {

    public static synchronized  void helloA(){
        System.out.println("hello A");
    }

    public static synchronized void helloB(){
        System.out.println("hello B");
        helloA();
    }

    public static void main(String[] args) {
        helloB();
    }
}
