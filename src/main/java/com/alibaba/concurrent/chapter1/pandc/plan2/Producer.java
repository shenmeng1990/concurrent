package com.alibaba.concurrent.chapter1.pandc.plan2;


/**
 * @Author shenmeng
 * @Date 2019/11/26
 **/

public class Producer implements Runnable {

    private Storage storage;

    public Producer() {
    }

    public Producer(Storage storage)  {
        this.storage = storage;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(1000);
                storage.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
