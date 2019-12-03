package com.alibaba.concurrent.chapter2.unsafe;

import sun.misc.Unsafe;

/**
 * @Author shenmeng
 * @Date 2019/12/3
 **/

public class TestUnsafe {

    //获取unsafe实例
    static final Unsafe unsafe = Unsafe.getUnsafe();
    //记录变量state在类TestUnsafe中的偏移值
    static final long stateoffset;
    //变量state
    private volatile long state=0;

    static{
        //获取state变量在类TestUnsafe中的偏移值
        try {
            stateoffset=unsafe.objectFieldOffset(TestUnsafe.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            System.out.println(e.getLocalizedMessage());
            throw new Error(e);
        }
    }

    public static void main(String args[]){
        TestUnsafe testUnsafe=new TestUnsafe();
        boolean success = unsafe.compareAndSwapLong(testUnsafe, stateoffset, 0, 1);
        System.out.println(success);
    }
}
