package com.alibaba.concurrent.chapter2.reorder;

/**
 * @Author shenmeng
 * @Date 2019/12/3
 **/

public class TestForContent1 {

    static final int LINE_NUM=1024;
    static final int COLUMN_NUM=1024;

    public static void main(String[] args) {
        long [][] array=new long[LINE_NUM][COLUMN_NUM];
        long startTime=System.currentTimeMillis();
        for(int i=0;i<LINE_NUM;++i){
            for(int j=0;j<COLUMN_NUM;++j){
                //array[i][j]=i*2+j;
                array[j][i]=i*2+j;
            }
        }
        long endTime=System.currentTimeMillis();
        long cacheTime=endTime-startTime;
        System.out.println("cache time:"+cacheTime);
    }

}
