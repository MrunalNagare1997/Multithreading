package Multithreading;

public class deadLock {

    public static void main(String args[])
    {
        String one="Two", two="One";
        Thread t1= new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (one)
                {
                    System.out.println(Thread.currentThread().getName()+" got a lock on one...");
                    try{
                        Thread.sleep(200);
                    }catch (Exception e)
                    {

                    }

                    synchronized (two)
                    {
                        System.out.println(Thread.currentThread().getName()+" got a lock on two...");
                    }

                }
            }
        });

        Thread t2= new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (two)
                {
                    System.out.println(Thread.currentThread().getName()+" got a lock on two...");
                    try{
                        Thread.sleep(200);
                    }catch (Exception e)
                    {

                    }

                    synchronized (one)
                    {
                        System.out.println(Thread.currentThread().getName()+" got a lock on one...");
                    }

                }
            }
        });

        t1.start();
        t2.start();
    }

}
