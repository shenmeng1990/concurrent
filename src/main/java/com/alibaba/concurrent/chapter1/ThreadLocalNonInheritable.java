package com.alibaba.concurrent.chapter1;

/**
 * ThreadLocal不支持继承
 * @Author shenmeng
 * @Date 2019/12/1
 **/

public class ThreadLocalNonInheritable {

    private static ThreadLocal<String> threadLocal= new InheritableThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("hello world");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread:"+threadLocal.get());
            }
        });
        thread.start();
        System.out.println("main:"+threadLocal.get());
    }
}
