package com.hh.pool;

/**
 * Created by hjz on 2017/11/30 0030.
 * CachedThreadPool:无界线程池
 * 无界线程池意味着没有工作队列，任务进来就执行，线程数量不够就创建，
 * 与前面两个的区别是：空闲的线程会被回收掉，空闲的时间是60s。
 * 这个适用于执行很多短期异步的小程序或者负载较轻的服务器。
 */
public class CachedThreadPoolTest {
}
