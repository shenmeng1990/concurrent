package com.alibaba.concurrent.chapter6;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author shenmeng
 * @Date 2019/12/6
 **/

public class TestPark {

    public static void main(String[] args) {
        System.out.println("begin park!");
        //使当前线程获取到许可证
        LockSupport.unpark(Thread.currentThread());
        //再次调用park会立刻返回
        LockSupport.park();
        System.out.println("end park!");
    }
}
