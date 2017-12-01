package com.hh.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * FixedThreadExecutor：固定大小线程池
 * 这个与单线程类似，只是创建了固定大小的线程数量。
 */
public class FixedThreadExecutorTest {

    public static void main(String[] args){
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        for (int i=0; i<16; i++){
            threadPool.execute(new Thread(new SingleThreadExecutorTest.ThreadPoolTest(),"Thread".concat(i + "")));
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
