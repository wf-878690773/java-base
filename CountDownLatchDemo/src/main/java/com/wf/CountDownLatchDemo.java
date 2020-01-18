package com.wf;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        //closeDoor();

        EnumForEach();

    }

    private static void EnumForEach() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);


        for (int i = 0; i < 5; i++) {

            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t 离开了");
                //等待线程走完，
                countDownLatch.countDown();
            }, CuntryEnum.forEach(i).getRetMessae()).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t 所有线程走完了");
    }



    @Test
    public static void fina()  {
        String retMessae = CuntryEnum.forEach(1).getRetMessae();
        System.out.println(retMessae);
    }

    private static void closeDoor() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t 离开了");
                //等待线程走完，
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t 所有线程走完了");
    }
}
