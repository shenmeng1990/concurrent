package com.alibaba.concurrent.chapter1;

/**
 * @Author shenmeng
 * @Date 2019/12/1
 **/

public class ThreadLocalTest {

    static ThreadLocal<String> localVariable = new ThreadLocal<>();
    static ThreadLocal<String> localVariable1 = new ThreadLocal<>();

    //print函数
    static void print(String str){
        System.out.println(str+":"+localVariable.get());
        System.out.println(str+":"+localVariable1.get());
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                //设置线程1中本地变量localVariable的值
                localVariable.set("t1 variable");
                localVariable1.set("t1 variable1");
                //调用打印方法
                print("t1");
                localVariable.remove();
                localVariable1.remove();
                //打印本地变量的值
                System.out.println("t1 remove after:"+localVariable.get());
                System.out.println("t1 remove after:"+localVariable1.get());
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //设置线程1中本地变量localVariable的值
                localVariable.set("t2 variable");
                //调用打印方法
                print("t2");
                localVariable.remove();
                //打印本地变量的值
                System.out.println("t2 remove after:"+localVariable.get());
            }
        });

        t1.start();
        t2.start();
    }
}
