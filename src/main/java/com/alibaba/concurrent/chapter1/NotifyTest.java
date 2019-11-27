package com.alibaba.concurrent.chapter1;

/**
 * @Author shenmeng
 * @Date 2019/11/27
 **/

public class NotifyTest {

    private static volatile Object resource = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadA get resource Lock!");
                synchronized (resource){
                    System.out.println("threadA begin wait");
                    try {
                        resource.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("threadA end wait");
                }
            }
        });

        Thread threadB=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadB get resource Lock!");
                synchronized (resource){
                    System.out.println("threadB begin wait");
                    try {
                        resource.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("threadB end wait");
                }
            }
        });

        Thread threadC= new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resource){
                    System.out.println("threadC begin nofity");
                    resource.notifyAll();
                }
            }
        });

        threadA.start();
        threadB.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadC.start();

        threadA.join();
        threadB.join();
        threadC.join();

        System.out.println("main over");
    }
}
