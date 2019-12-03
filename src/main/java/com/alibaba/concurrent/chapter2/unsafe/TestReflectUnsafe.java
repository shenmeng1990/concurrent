package com.alibaba.concurrent.chapter2.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Author shenmeng
 * @Date 2019/12/3
 **/

public class TestReflectUnsafe {

    static final Unsafe unsafe;

    static final long stateOffset;

    private volatile long state=0;

    static{
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe =(Unsafe) f.get(null);
            stateOffset = unsafe.objectFieldOffset(TestUnsafe.class.getDeclaredField("state"));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            throw new Error(e);
        }

    }

    public static void main(String[] args) {
        TestReflectUnsafe t=new TestReflectUnsafe();
        boolean success = unsafe.compareAndSwapLong(t, stateOffset, 0, 1);
        System.out.println(success);
    }
}
