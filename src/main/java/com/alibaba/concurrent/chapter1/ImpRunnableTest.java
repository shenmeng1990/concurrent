package com.alibaba.concurrent.chapter1;

/**
 * @Author shenmeng
 * @Date 2019/11/26
 **/

public class ImpRunnableTest {

    public static class RunnableTask implements Runnable{
        @Override
        public void run() {
            System.out.println("I am a child Thread!");
        }
    }
    public static void main(String[] args) {
        RunnableTask runnableTask= new RunnableTask();
        new Thread(runnableTask).start();
        new Thread(runnableTask).start();
    }

}
