package com.alibaba.concurrent.chapter6;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自定义不可重入的独占锁
 * @Author shenmeng
 * @Date 2019/12/11
 **/

public class NonReentrantLock implements Lock, java.io.Serializable {

    //内部帮助类
    private static class Sync extends AbstractQueuedSynchronizer{
        //是否锁已经被持有 1-被持有 0-未被持有
        protected boolean isHeldExclusively(){
            return getState()==1;
        }

        //如果状态为0，则尝试获取锁
        public boolean tryAcquire(int acquires){
            assert acquires==1;
            /**
             * 利用CAS的 unsafe.compareAndSwapInt(this, stateOffset, expect, update);
             * stateOffset：state变量在当前类中的内存地址偏移量
             * this：当前对象 expect：期望值 update：新值
             * 如果state=0，则update成1，并返回true
            **/
            if(compareAndSetState(0,1)){
                //设置成功，说明锁被当前线程占有，而且是独占锁。
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        //尝试释放锁，设置state为0
        protected boolean tryRelease(int realeases){
            assert realeases==1;
            //如果当前锁的状态已经是0了，就不需要释放
            if(getState()==0)
                throw new IllegalMonitorStateException();
            //设置当前锁没有线程持有
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        //提供条件变量接口
        ConditionObject newCondition(){
            return new ConditionObject();
        }
    }

    //创建一个sync来做具体的工作
    private final Sync sync=new Sync();

    @Override
    public void lock() {
        //在该方法里面其实是调用了tryAcquire方法
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        //该方法中其实是调用了tryRelease
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked(){
        return sync.isHeldExclusively();
    }
}
