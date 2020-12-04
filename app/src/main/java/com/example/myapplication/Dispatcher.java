package com.example.myapplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 专门执行异步Runnable的线程池管理类
 */

public class Dispatcher {

    private ExecutorService executorService;

    private static Dispatcher instance;

    public static Dispatcher getInstance() {
        if (instance == null)
            synchronized (Dispatcher.class) {
                if (instance == null){
                    instance = new Dispatcher();
                }
            }
        return instance;
    }

    private Dispatcher() {

    }

    public synchronized ExecutorService executorService() {
        if (executorService == null) {
            executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>(), new ThreadFactory() {
                @Override
                public Thread newThread(Runnable runnable) {
                    Thread result = new Thread(runnable, "Core Dispatcher");
                    result.setName(result.getName());
                    result.setDaemon(false);
                    return result;
                }
            });
        }
        return executorService;
    }

    public void enqueue(Runnable runnable) {
        executorService().execute(runnable);
    }
}
