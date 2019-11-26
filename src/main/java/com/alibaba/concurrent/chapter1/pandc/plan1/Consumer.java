package com.alibaba.concurrent.chapter1.pandc.plan1;

/**
 * @Author shenmeng
 * @Date 2019/11/26
 **/

public class Consumer implements Runnable {

    private Storage storage;

    public Consumer() {
    }

    public Consumer(Storage storage){
        this.storage=storage;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(300);
                storage.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
