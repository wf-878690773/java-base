package com.wf;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CycLlicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier  cyclicBarrier = new CyclicBarrier(7,() -> {
            System.out.println("准备就绪");
        });
        for (int i = 0; i < 10 ; i++) {

            final int index = i;

            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t 第"+index+"线程准备就序");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

    }
}
