package com.zr;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量，目的
 * 1.用于多个共享资源的互斥使用，
 * 2.用于并发线程数的控制
 */
public class SemaphoreDemo {

    public static void main(String[] args) {

        /**
         * Semaphore中包含初始化时固定个数的许可，在进行操作的时候，需要先acquire获取到许可，
         * 才可以继续执行任务，如果获取失败，则进入阻塞；处理完成之后需要release释放许可。
         */
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    //
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+" 抢到线程");

                    try { TimeUnit.SECONDS.sleep(3); }catch (Exception e){ e.printStackTrace(); }

                    System.out.println(Thread.currentThread().getName()+" 占用3秒，释放线程");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //释放线程
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }

    }
}
