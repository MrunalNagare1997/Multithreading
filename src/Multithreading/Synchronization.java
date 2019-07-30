package Multithreading;

 class IncrementTask {

    private int count;

    public synchronized void Incriment()
    {
        count++;
    }

    public int getCount() {
        return count;
    }
}


public class Synchronization {

    public static void main(String args[])throws Exception{

        IncrementTask inc=new IncrementTask();

        Thread t1= new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i=0;i<1000;i++)
                    inc.Incriment();

            }
        });

        Thread t2= new Thread(new Runnable() {
            @Override
            public void run() {

                for(int j=0;j<1000;j++)
                    inc.Incriment();

            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();



        System.out.println("Counter: "+inc.getCount());

    }
}
