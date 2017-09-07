package develop.lib.http;

import android.support.annotation.NonNull;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by KKK on 2017/9/4.
 */

public class HttpThreadPool {
    private static final byte[] key = new byte[]{};
    /**
     * ThreadPoolExecutor构造函数参数说明：
     * corePoolSize 核心线程池大小
     * maximumPoolSize 线程池最大容量大小
     * keepAliveTime 线程池空闲时，线程存活的时间
     * TimeUnit 时间单位
     * ThreadFactory 线程工厂
     * BlockingQueue任务队列
     * RejectedExecutionHandler 线程拒绝策略
     */
    //arrayblockingqueue threadpoolexecutor
    //cpu线程数用于设置线程池内线程数量
    int cpuThread = Runtime.getRuntime().availableProcessors();
    private static ThreadPoolExecutor pool = null;
    private static HttpThreadPool threadPool;

    private HttpThreadPool() {
        init();
    }

    public static ExecutorService getInstance() {
        synchronized (key) {
            if (threadPool == null) {
                threadPool = new HttpThreadPool();
            }
            return pool;
        }
    }

    private void init() {
        pool = new ThreadPoolExecutor(
                cpuThread,
                4 * cpuThread,
                8 * cpuThread, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(5));
//        ,
//        new CustomThreadFactory(),
//                new CustomRejectedExecutionHandler()
    }

    public void destroy() {
        if (pool != null) {
            pool.shutdownNow();
        }
    }

    private class CustomThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(@NonNull Runnable runnable) {
            Thread thread = new Thread();
            return thread;
        }
    }

    private class CustomRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            try {
                threadPoolExecutor.getQueue().put(runnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
