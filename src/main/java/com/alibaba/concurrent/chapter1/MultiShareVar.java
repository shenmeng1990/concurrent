package com.alibaba.concurrent.chapter1;

/**
 * @Author shenmeng
 * @Date 2019/11/27
 **/

public class MultiShareVar {

    //创建资源
    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) {

        //创建线程
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    synchronized (resourceA){
                        //获取共享变量resourceA的锁
                        System.out.println("threadA get resouceA lock!");
                        synchronized (resourceB){
                            System.out.println("threadA get resourceB lock");
                            //线程A阻塞，并释放获取到的resourceA的锁
                            System.out.println("threadA release resourceA lock");
                            resourceA.wait();
                        }
                    }
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        });


        //创建线程
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);
                    synchronized (resourceA){
                        //获取共享变量resourceA的锁
                        System.out.println("threadB get resouceA lock!");

                        System.out.println("threadB try to get resourceB lock!");
                        synchronized (resourceB){
                            System.out.println("threadB get resourceB lock");
                            //线程A阻塞，并释放获取到的resourceA的锁
                            System.out.println("threadB release resourceA lock");
                            resourceA.wait();
                        }
                    }
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        threadA.start();
        threadB.start();
        try{
            threadA.join();
            threadB.join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main over!");
    }


}
