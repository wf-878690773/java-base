package com.zr;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareDate{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    /**
     * 生产
     * @throws InterruptedException
     */
    public void increment() throws InterruptedException {
        lock.lock();
        try {
            //1.判断
            while (number != 0){
                //等待，不生产
                condition.await();
            }
            //2.干活
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //3.通知唤起
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        lock.unlock();
    }

    /**
     * 消费
     * @throws InterruptedException
     */
    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            //1.判断
            while (number == 0){
                //等待，不消费
                condition.await();
            }
            //2.干活
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //3.通知唤起
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        lock.unlock();
    }

}
public class ProdConsumer_TraditionDemo {
    public static void main(String[] args) {
        ShareDate date = new ShareDate();
        new Thread(() ->{
            for (int i = 0; i < 5; i++) {
                try {
                    date.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AAA" ).start();
        new Thread(() ->{
            for (int i = 0; i < 5; i++) {
                try {
                    date.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BBB" ).start();
    }
}
