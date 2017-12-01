package com.hh.pool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 public ThreadPoolExecutor(int corePoolSize,
                       int maximumPoolSize,
                       long keepAliveTime,
                       BlockingQueue<Runnable> workQueue,
                      TimeUnit unit,
                      RejectedExecutionHandler handler)
 corePoolSize：线程池核心线程数量

 maximumPoolSize:线程池最大线程数量

 keepAliverTime：当活跃线程数大于核心线程数时，空闲的多余线程最大存活时间

 unit：存活时间的单位

 workQueue：存放任务的队列

 handler：超出线程范围和队列容量的任务的处理程序


 https://www.cnblogs.com/dongguacai/p/6030187.html
 */
public class ThreadPoolExecutorTest {

    public static void main(String[] args){
        //存放任务的队列【这里最多存放5个任务】
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(5);

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5,
                10,
                60,
                TimeUnit.SECONDS,
                queue);

        for (int i=0; i<16; i++){
            threadPool.execute(new Thread(new ThreadPoolTest(),"Thread".concat(i + "")));
            System.out.println("线程池中活跃的线程数："+threadPool.getPoolSize());
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


    /**
     * 执行结果
     * Exception in thread "main" java.util.concurrent.RejectedExecutionException: Task Thread[Thread15,5,main] rejected from java.util.concurrent.ThreadPoolExecutor@29453f44[Running, pool size = 10, active threads = 10, queued tasks = 5, completed tasks = 0]
     at java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2047)
     at java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:823)
     at java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1369)
     at com.hh.pool.ThreadPoolExecutorTest.main(ThreadPoolExecutorTest.java:40)
     从结果可以观察出：

     1、创建的线程池具体配置为：核心线程数量为5个；全部线程数量为10个；工作队列的长度为5。

     2、我们通过queue.size()的方法来获取工作队列中的任务数。

     3、运行原理：

     刚开始都是在创建新的线程，达到核心线程数量5个后，新的任务进来后不再创建新的线程，而是将任务加入工作队列，任务队列到达上线5个后，新的任务又会创建新的普通线程，直到达到线程池最大的线程数量10个，后面的任务则根据配置的饱和策略来处理。我们这里没有具体配置，使用的是默认的配置AbortPolicy:直接抛出异常。

     　　当然，为了达到我需要的效果，上述线程处理的任务都是利用休眠导致线程没有释放！！！
     *
     *
     */

}
