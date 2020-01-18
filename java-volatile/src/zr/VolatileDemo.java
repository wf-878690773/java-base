package zr;

import java.util.concurrent.TimeUnit;

/**
 * volatile 保证可见性，不保证原子性，禁止指令重排
 */
class MyData{

    volatile int number = 0;
    public void addDate(){
        this.number = 60;
    }
    public void addPus(){
        number++;
    }
}

public class VolatileDemo {
    public static void main(String[] args) {

        atomicDemo();

    }

    private static void atomicDemo() {
        MyData myData = new MyData();
        for (int i = 0; i < 20; i++) {
                new Thread(() -> {
                    for (int j = 0; j < 1000; j++) {
                        myData.addPus();
                    }
                },String.valueOf(i)).start();
        }

        //
        while (Thread.activeCount() > 2){
            // yield() 方法只是使当前线程重新回到可执行状态
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t finally number value :"+myData.number);
    }

    /**
     * 可见性
     */
    private static void method() {
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t come in");

            try {
                TimeUnit.SECONDS.sleep(3);
            }catch (Exception e){
                e.printStackTrace();
            }

            myData.addDate();
            System.out.println(Thread.currentThread().getName()+"\t update number value:" + myData.number);
        },"A").start();

        while (myData.number == 0){

        }
        System.out.println(Thread.currentThread().getName()+"\t misson is over");
    }
}
