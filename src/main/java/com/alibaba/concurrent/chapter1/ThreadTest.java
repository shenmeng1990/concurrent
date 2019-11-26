package com.alibaba.concurrent.chapter1;

/**
 * @Author shenmeng
 * @Date 2019/11/26
 **/

public class ThreadTest {

    public static class MyThread extends Thread{

        @Override
        public void run() {
            System.out.println("I am a child thread!");
        }
    }

    public static void main(String[] args) {
        MyThread myThread=new MyThread();
        myThread.start();
    }
}
