package com.alibaba.concurrent.chapter1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author shenmeng
 * @Date 2019/11/26
 **/

public class ImpCallableTest {

    public static class CallerTask implements Callable<String>{
        @Override
        public String call() throws Exception {
            return "I am callable";
        }
    }

    public static void main(String[] args) {
        FutureTask<String> futureTask =new FutureTask<>(new CallerTask());
        new Thread(futureTask).start();
        try {
            String s = futureTask.get();
            System.out.println("result==="+s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
