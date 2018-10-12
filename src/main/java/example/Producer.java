package example;

import entity.PCData;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Sunshine
 * @Description: 生产者消费者模型 生产者
 * @Date: Create in 2018/10/12 10:10
 */
public class Producer implements Runnable{
    private String name;

    private volatile boolean isRunning = true;
    //内存缓冲区
    private BlockingDeque<PCData> queue;
    //总数，原子操作
    private static AtomicInteger count = new AtomicInteger();
    private static final int SLEEPTIME = 1000;

    public Producer(BlockingDeque<PCData> queue){
        this.queue = queue;
    }

    public Producer(BlockingDeque<PCData> queue, String name){
        this.queue = queue;
        this.name = name;
    }
    @Override
    public void run() {
        PCData data = null;
        Random r = new Random();
        System.out.println("start producing id :" + Thread.currentThread().getId());
        try {
            while (isRunning){
                Thread.sleep(r.nextInt(SLEEPTIME));
                data = new PCData(count.incrementAndGet());
                System.out.println(this.name + "生产的数据" + data + "加入队列");
                if (!queue.offer(data, 2, TimeUnit.SECONDS)){
                    System.err.println("加入队列失败");
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void stop(){
        this.isRunning = false;
        System.out.println(this.name + "停止生产");
    }
}
