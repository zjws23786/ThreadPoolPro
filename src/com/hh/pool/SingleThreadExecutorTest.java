package com.hh.pool;

import java.util.concurrent.*;

/**
 * SingleThreadExecutor：单线程线程池
 *单线程线程池的创建也是通过ThreadPoolExecutor，里面的核心线程数和线程数都是1，
 * 并且工作队列使用的是无界队列。由于是单线程工作，每次只能处理一个任务，
 * 所以后面所有的任务都被阻塞在工作队列中，只能一个个任务执行
 */
public class SingleThreadExecutorTest {

    public static void main(String[] args){;

        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        for (int i=0; i<16; i++){
            threadPool.execute(new Thread(new ThreadPoolTest(),"Thread".concat(i + "")));
            System.out.println("内存地址："+threadPool.toString());

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
