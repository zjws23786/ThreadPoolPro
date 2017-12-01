package com.hh.pool;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by hjz on 2017/11/30 0030.
 */
public class CallableTest {

    public static void main(String[] args) throws Exception {
        Callable<Integer> callable = new MyCallableTask();
        FutureTask<Integer> future = new FutureTask<Integer>(callable);
        Thread thread = new Thread(future);
        thread.start();
        Thread.sleep(100);
        //尝试取消对此任务的执行
        //尝试取消对任务的执行，该方法如果由于任务已完成、已取消则返回false，
        // 如果能够取消还未完成的任务，则返回true，该DEMO中由于任务还在休眠状态，所以可以取消成功
        future.cancel(true);
        //判断是否在任务正常完成前取消
        System.out.println("future is cancel:"+future.isCancelled());
        if (!future.isCancelled()){
            System.out.println("future is cancelled");
        }
        //判断任务是否已完成
        System.out.println("future is done:" + future.isDone());
        if (!future.isDone()){
            System.out.println("future get=" + future.get());
        }else{
            //任务已完成
            System.out.println("task is done");
        }

    }
}
