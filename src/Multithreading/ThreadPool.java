package Multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class Task implements Runnable
{
    static int num=0;
    @Override
    public void run() {
        num++;
        System.out.println("Thread name: "+Thread.currentThread().getName()+"\tThread state: "+Thread.currentThread().getState());
        System.out.println("num= "+num);
        try {
            Thread.currentThread().sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ThreadPool {


    public static void main(String args[]) {


        //Fixed Thread pool...
        ExecutorService executor = Executors.newFixedThreadPool(7);

        for (int i = 0; i < 5; i++) {
            executor.submit(new Task());
        }
        executor.shutdown();



        //scheduled Thread Pool...
        ExecutorService executeSequencually = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            executeSequencually.submit(new Task());
        }

        executeSequencually.shutdown();

        //Schedule Thread Pool...
        ScheduledExecutorService executeWithSchedule = Executors.newScheduledThreadPool(5);

        executeWithSchedule.scheduleAtFixedRate(new Task(),0, 1, TimeUnit.SECONDS);







    }


}
