package com.alibaba.concurrent.chapter1;

/**
 * @Author shenmeng
 * @Date 2019/11/29
 **/

public class DaemonAndUserThread {

    public static void main(String[] args) {
        Thread t1= new Thread(new Runnable() {
            @Override
            public void run() {
                for(;;){
                }
            }
        });
        //设置为守护线程后 jvm会自动退出
       // t1.setDaemon(true);
        t1.start();
        System.out.println("main is over");
    }
}
