package com.alibaba.concurrent.chapter5;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author shenmeng
 * @Date 2019/12/6
 **/

public class TestIterator {

    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list=new CopyOnWriteArrayList<>();
        list.add("hello");
        list.add("alibaba");

        list.iterator().forEachRemaining(itr->{
            System.out.println(itr);
        });

        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
