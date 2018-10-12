package example;

import entity.PCData;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Sunshine
 * @Description: 生产者消费者模型 消费者
 * @Date: Create in 2018/10/12 10:25
 */
public class Consumer implements Runnable{

    private String name;

    private BlockingDeque<PCData> queue;
    private static final int SLEEPTIME = 1000;
    private static AtomicInteger count = new AtomicInteger(0);
    public Consumer(BlockingDeque<PCData> queue){
        this.queue = queue;
    }

    public Consumer(BlockingDeque<PCData> queue, String name){
        this.queue = queue;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("start Consumer id :" + Thread.currentThread().getId() + " name is " + Thread.currentThread().getName());
        Random r = new Random();
        try {
            while (true){
                PCData data = queue.take();
                if (data != null){
                    int re = data.getData();
                    System.out.println(this.name + "获取了" + re + " 进行了平方计算  " + MessageFormat.format("{0} * {1} = {2}", re, re, Math.pow(re, 2)) + "第" + count.incrementAndGet() + "次操作");
                    Thread.sleep(r.nextInt(SLEEPTIME));
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
