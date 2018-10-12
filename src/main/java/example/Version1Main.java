package example;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import entity.PCData;

import java.util.concurrent.*;

/**
 * @Author: Sunshine
 * @Description:
 * @Date: Create in 2018/10/12 10:34
 */
public class Version1Main {
    public static void main(String[] args) throws InterruptedException{
        BlockingDeque<PCData> queue = new LinkedBlockingDeque<>(10);
        BlockingQueue<Runnable> queue1 = new LinkedBlockingQueue<>(10);
        Producer p1 = new Producer(queue, "p1");
        Producer p2 = new Producer(queue, "p2");
        Producer p3 = new Producer(queue, "p3");
        Consumer c1 = new Consumer(queue, "c1");
        Consumer c2 = new Consumer(queue, "c2");
        Consumer c3 = new Consumer(queue, "c3");
//        ExecutorService service = Executors.newCachedThreadPool();
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("tester").build();
        ExecutorService service = new ThreadPoolExecutor(6, 10, 60L, TimeUnit.MILLISECONDS, queue1, threadFactory);
        service.execute(p1);
        service.execute(p2);
        service.execute(p3);
        service.execute(c1);
        service.execute(c2);
        service.execute(c3);
        Thread.sleep(10 * 1_000);
        p1.stop();
        p2.stop();
        p3.stop();
        Thread.sleep(3000);
        service.shutdown();
    }
}
