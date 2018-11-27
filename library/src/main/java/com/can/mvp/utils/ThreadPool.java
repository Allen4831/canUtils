package com.can.mvp.utils;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by CAN on 18-11-27.
 * 异步加载线程
 */

public class ThreadPool {

    private static ThreadPool threadPool;
    private ExecutorService processorsPools,cachedPools;

    public static synchronized ThreadPool getInstance(){
        if(threadPool==null)
            threadPool = new ThreadPool();
        return threadPool;
    }

    public ExecutorService getProcessorsPools(){
        if(processorsPools==null)
            processorsPools = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        return processorsPools;
    }

    public ExecutorService getCachedPools(){
        if(cachedPools==null)
            cachedPools = Executors.newCachedThreadPool();
        return cachedPools;
    }

    public void shutdownCachePools(){
        if(cachedPools!=null&&!cachedPools.isShutdown())
            cachedPools.shutdown();
    }


}
