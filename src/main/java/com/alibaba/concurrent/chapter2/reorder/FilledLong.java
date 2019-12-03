package com.alibaba.concurrent.chapter2.reorder;

/**
 * 假如缓存行大小为64字节，
 * 那么在FilledLong类里面填充了6个long类型的变量，
 * 每个long类型变量占用8个字节，
 * 加上value变量的8字节总共56字节。
 * 另外这里的FilledLong是一个类对象，
 * 而类对象的字节码的对象头占用8个字节，
 * 所以一个FilledLong对象实际上会占用64字节的内存，
 * 这正好可以放入一个缓存行。
 * @Author shenmeng
 * @Date 2019/12/3
 **/

public class FilledLong {
    public volatile  long value=0l;
    public long p1,p2,p3,p4,p5,p6;

    @sun.misc.Contended
    public final static class FilledLong1{
        public volatile long value=0l;
    }
}
