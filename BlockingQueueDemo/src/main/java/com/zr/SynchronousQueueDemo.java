package com.zr;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 生产一个，消费一个
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> synchronousQuence = new SynchronousQueue<String>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+"\t  put1");
                synchronousQuence.put("1");

                System.out.println(Thread.currentThread().getName()+"\t  put2");
                synchronousQuence.put("2");

                System.out.println(Thread.currentThread().getName()+"\t  put3");
                synchronousQuence.put("3");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"aaa").start();

        new Thread(() -> {
            try {
                try { TimeUnit.SECONDS.sleep(3); }catch (Exception e){ e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName()+"\t  take1");
                System.out.println(synchronousQuence.take());

                try { TimeUnit.SECONDS.sleep(3); }catch (Exception e){ e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName()+"\t  take2");
                System.out.println(synchronousQuence.take());

                try { TimeUnit.SECONDS.sleep(3); }catch (Exception e){ e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName()+"\t  take3");
                System.out.println(synchronousQuence.take());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"aaa").start();
    }
}
