package com.hh.pool;

import java.util.concurrent.*;

/**
 * Created by hjz on 2017/11/30 0030.
 */
public class CallableThread implements Callable<String>{

    @Override
    public String call()
            throws Exception
    {
        System.out.println("进入Call方法，开始休眠，休眠时间为：" + System.currentTimeMillis());
        Thread.sleep(10000);
        return "今天停电";
    }

//    public static void main(String[] args) throws Exception
//    {
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        Callable<String> call = new CallableThread();
//        Future<String> future = executorService.submit(call);
//        executorService.shutdown();
//        Thread.sleep(5000);
//        System.out.println("主线程休眠5秒，当前时间" + System.currentTimeMillis());
//        String str = future.get();
//        System.out.println("Future已拿到数据，str=" + str + ";当前时间为：" + System.currentTimeMillis());
//    }

    public static void main(String[] args) throws Exception
    {
        ExecutorService es = Executors.newSingleThreadExecutor();
        Callable<String> call = new CallableThread();
        FutureTask<String> task = new FutureTask<String>(call);
        es.submit(task);
        es.shutdown();
        Thread.sleep(5000);
        System.out.println("主线程等待5秒，当前时间为：" + System.currentTimeMillis());
        String str = task.get();
        System.out.println("Future已拿到数据，str=" + str + ";当前时间为：" + System.currentTimeMillis());
    }
}
