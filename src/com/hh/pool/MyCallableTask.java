package com.hh.pool;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by hjz on 2017/11/30 0030.
 */
public class MyCallableTask implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("callable do something");
        Thread.sleep(5000);
        return new Random().nextInt(100);
    }
}
