package com.alibaba.concurrent.chapter5;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author shenmeng
 * @Date 2019/12/6
 **/

public class TestCopyList {
    private static volatile CopyOnWriteArrayList<String> list=new CopyOnWriteArrayList();

    public static void main(String[] args) throws InterruptedException {
        list.add("hello");
        list.add("alibaba");
        list.add("welcome");
        list.add("to");
        list.add("hangzhou");

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                //修改下标为1的元素
                list.set(1,"baba");
                //删除元素
                list.remove(2);
                list.remove(3);
            }
        });

        Iterator<String> iterator = list.iterator();

        t1.start();
        t1.join();

        //迭代元素
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
