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
        //创建异步任务
        FutureTask<String> futureTask =new FutureTask<>(new CallerTask());
        //启动线程
        new Thread(futureTask).start();
        try {
            //等待任务执行完毕，并返回结果。
            String s = futureTask.get();
            System.out.println("result==="+s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
