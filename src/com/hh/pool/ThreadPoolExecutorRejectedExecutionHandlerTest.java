package com.hh.pool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * RejectedExecutionHandler：饱和策略
 *
 当队列和线程池都满了，说明线程池处于饱和状态，那么必须对新提交的任务采用一种特殊的策略来进行处理。
 这个策略默认配置是AbortPolicy，表示无法处理新的任务而抛出异常。JAVA提供了4中策略：

 1、AbortPolicy：直接抛出异常

 2、CallerRunsPolicy：只用调用所在的线程运行任务

 3、DiscardOldestPolicy：丢弃队列里最近的一个任务，并执行当前任务。

 4、DiscardPolicy：不处理，丢弃掉。
 */
public class ThreadPoolExecutorRejectedExecutionHandlerTest {

    public static void main(String[] args){
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(3);

        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardOldestPolicy();

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,
                5,
                60,
                TimeUnit.SECONDS,
                queue,
                handler);
        for (int i=0; i<9; i++){
            threadPool.execute(new Thread(new ThreadPoolTest(), "Thread".concat(i + "")));
            System.out.println("线程池中活跃的线程数： " + threadPool.getPoolSize());
            if (queue.size() > 0){
                System.out.println("----------------队列中阻塞的线程数" + queue.size());
            }
        }
        threadPool.shutdown();
    }

    public static class ThreadPoolTest implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
