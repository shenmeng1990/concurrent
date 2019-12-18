package com.alibaba.concurrent.chapter6;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author shenmeng
 * @Date 2019/12/6
 **/

public class TestBlockPark {
    public void testPark(){
        LockSupport.park(this);
    }
    public static void main(String[] args) {
        TestBlockPark blockPark=new TestBlockPark();
        blockPark.testPark();
    }
}
