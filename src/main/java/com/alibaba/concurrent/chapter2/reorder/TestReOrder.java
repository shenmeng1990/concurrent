package com.alibaba.concurrent.chapter2.reorder;

/**
 * @Author shenmeng
 * @Date 2019/12/3
 **/

public  class TestReOrder {

    private static int num=0;
    private static boolean ready=false;

    public static class ReadThread extends Thread{
        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()){
                if(ready){//(1)
                    System.out.println(num+num);//(2)
                }
                System.out.println("read thread....");
            }
        }
    }

    public static class WriteThread extends Thread{

        @Override
        public void run() {
            num=2;//(3)
            ready=true;//(4)
            System.out.println("writeThread set over!");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadThread readThread = new ReadThread();
        readThread.start();

        WriteThread writeThread=new WriteThread();
        writeThread.start();

        Thread.sleep(1);
        readThread.interrupt();
        System.out.println("main exit");
    }
}
