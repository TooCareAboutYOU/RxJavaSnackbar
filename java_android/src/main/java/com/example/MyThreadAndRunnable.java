package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.InputMismatchException;

/**
 * Created by Administrator on 2017/3/6 0006.
 */

public class MyThreadAndRunnable {

    /*
    * 示例代码中，notifyTest()1和notifyTest2()是“多对象多线程”，
    * 尽管notifyTest2()中的线程实现了Runnable接口，但是它里面定义Thread数组时，
    * 每个元素都使用了一个新的Runnable实例。notifyTest3()属于“单对象多线程”，
    * 因为我们只定义了一个Runnable实例，所有的线程都会使用这个实例。
    * notifyAll方法适用于“单对象多线程”的情景，因为notify方法只会随机唤醒对象上的一个线程。
    * */

    public static void main(String[] args) throws InterruptedException {
        MyThreadAndRunnable main=new MyThreadAndRunnable();
        //main.notifyTest1();
        //main.notifyTest2();
        //main.notifyTest3();
        main.sleepTest();
    }

    private void notifyTest1() throws InterruptedException{
        MyThread[] myThreads=new MyThread[3];
        for (int i = 0; i < myThreads.length; i++) {
            myThreads[i]=new MyThread();
            myThreads[i].id=i;
            /*isDaemon，这个属性用来控制父子线程的关系，如果设置为true，当父线程结束后，
            * 其下所有子线程也结束，反之，子线程的生命周期不受父线程影响。
            */

            myThreads[i].setDaemon(true);
            myThreads[i].start();
        }
        Thread.sleep(500);
        for (int i = 0; i < myThreads.length; i++) {
            synchronized (myThreads[i]){
                myThreads[i].notify();
            }
        }
    }

    private void notifyTest2() throws InterruptedException {
        MyRunner[] arrMyRunners = new MyRunner[3];
        Thread[] arrThreads = new Thread[3];
        for (int i = 0; i < arrThreads.length; i++) {
            arrMyRunners[i] = new MyRunner();
            arrMyRunners[i].id = i;
            arrThreads[i] = new Thread(arrMyRunners[i]);
            arrThreads[i].setDaemon(true);
            arrThreads[i].start();
            }
        Thread.sleep(500);
        for (int i = 0; i < arrMyRunners.length; i++) {
            synchronized(arrMyRunners[i]) {
             arrMyRunners[i].notify();
            }
        }
    }

    private void notifyTest3() throws InterruptedException {
        MyRunner runner = new MyRunner();
        Thread[] arrThreads = new Thread[3];
        for (int i = 0; i < arrThreads.length; i++){
            arrThreads[i] = new Thread(runner);
            arrThreads[i].setDaemon(true);
            arrThreads[i].start();
         }
        Thread.sleep(500);

        synchronized(runner) {
            runner.notifyAll();
        }
    }

    /*
    * 线程方式一
    * */
    class MyThread extends Thread{
        private int id=0;

        @Override
        public void run() {
            System.out.println("第"+id+"个线程准备休眠5分钟");
            try {
                synchronized (this){
                    this.wait(5*60*1000);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("第"+id+"个线程被唤醒");
        }
    }

    /*
    * 线程方式二
    * */
    class MyRunner implements Runnable{

        private int id=0;


        @Override
        public void run() {
            System.out.println("第"+id+"个线程准备休眠5分钟");
            try {
                synchronized (this){
                    this.wait(5*60*1000);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("第"+id+"个线程被唤醒");
        }
    }


    /*
    * 线程等待与唤醒这里主要使用Object.wait和Object.notify方法，
    * 请参见上面的notify实例。需要注意的是，wait和notify都必须针对同一个对象，
    * 当我们使用实现Runnable接口的方式来创建线程时，应该是在Runnable对象而非Thread对象上使用这两个方法。
    * 线程在休眠过程中，我们可以使用Thread.interrupt将其唤醒，这时线程会抛出InterruptedException。
    * */

    private void sleepTest() throws InterruptedException {
        Thread thread=new Thread(){
            @Override
            public void run() {
                System.out.println("线程"+Thread.currentThread().getName()+"线程休眠5分钟");
                try {
                    synchronized (this){
                        this.wait(5*60*1000);
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                    System.out.println("线程"+Thread.currentThread().getName()+"线程休眠中断");
                }
                System.out.println("线程"+Thread.currentThread().getName()+"线程结束");
            }
        };
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }

    /*
    * 线程的终止
    * 虽然有Thread.stop方法，但该方法是不被推荐使用的，我们可以利用上面休眠与唤醒的机制，
    * 让线程在处理IterruptedException时，结束线程。
    * */

    private void stopTest() throws InterruptedException {
        Thread thread=new Thread(){
            @Override
            public void run() {
                System.out.println("线程运行中");
                try {
                    Thread.sleep(1*60*1000);
                } catch (InterruptedException e) {
                    System.out.println("线程中断，结束线程");
                    return;
                }
                System.out.println("线程正常结束");
            }
        };
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }

    /*
    * 线程的同步等待
    * 当我们在主线程中创建了10个子线程，然后我们期望10个子线程全部结束后，
    * 主线程在执行接下来的逻辑，这时，就该Thread.join登场了。
    *  可以试着将thread.join();注释或者删除，再次运行程序，就可以发现不同了。
    * */

    private void JoinTest() throws InterruptedException {
        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 5 ; i++) {
                        System.out.println("线程在进行。。。");
                        Thread.sleep(1000);
                    }
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(1000);
        thread.join();
        System.out.println("主线程正常结束。");
    }


    /*
    * 一个进程下面的所有线程是共享内存空间的，那么我们如何在不同的线程之间传递消息呢？
    * 在回顾 Java I/O时，我们谈到了PipedStream和PipedReader
    * */
    private void communicationTest() throws InterruptedException, IOException {
        final PipedOutputStream pos=new PipedOutputStream();
        final PipedInputStream pis=new PipedInputStream();

        Thread thread1=new Thread(){
            @Override
            public void run() {
                BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

                try {
                    while (true) {
                        String message = br.readLine();
                        pos.write(message.getBytes());
                        if (message.equals("end")) break;
                    }
                    br.close();
                    pos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread2=new Thread(){
            @Override
            public void run() {
                byte[] buffer=new byte[1024];
                int bytesRead=0;
                try {
                    while ((bytesRead=pis.read(buffer,0,buffer.length)) != -1){
                        System.out.println(new String(buffer));
                        if (new String(buffer).equals("end")) break;
                        buffer = null;
                        buffer = new byte[1024];
                    }
                    pis.close();
                    buffer = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        thread1.setDaemon(true);
        thread2.setDaemon(true);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }

    private void communicationTest2() throws InterruptedException,IOException {
        final PipedWriter pw = new PipedWriter();
        final PipedReader pr = new PipedReader();
        final BufferedWriter bw = new BufferedWriter(pw);
        final BufferedReader br = new BufferedReader(pr);

        Thread thread1 = new Thread(){
            @Override
            public void run() {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                    try {
                        while (true) {
                            String message = br.readLine();
                            bw.write(message);
                            bw.newLine();
                            bw.flush();
                            if (message.equals("end")) break;
                        }
                        br.close();
                        pw.close();
                        bw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        };


        Thread thread2 = new Thread(){
            @Override
            public void run() {
               String line = null;

                try {
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                        if (line.equals("end")) break;
                    }
                    br.close();
                    pr.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread1.setDaemon(true);
        thread2.setDaemon(true);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

    }

}
