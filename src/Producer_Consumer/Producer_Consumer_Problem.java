package Producer_Consumer;

import java.util.ArrayList;

class PC{

    private ArrayList<Integer> products;
    private int capacity;
    private int value;

    public PC()
    {
        products= new ArrayList<>();
        capacity=5;
        value=0;

    }

    public void produce()
    {
        while(true) {
            synchronized (this) {
                if (products.size() == capacity) {
                    try {
                        System.out.println("Full...");
                        wait();
                        System.out.println(Thread.currentThread().getName()+"Is back...");
                    } catch (InterruptedException e) {

                        e.printStackTrace();

                    }
                }

                products.add(++value);
                System.out.println(value + ": Is produced...");

                notify();

                try {

                    Thread.sleep(100);

                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }
        }
    }

    public void consume()
    {
        while (true) {
            synchronized (this) {
                if (products.size() == 0) {
                    try {
                        System.out.println("No Products...");
                        wait();
                        System.out.println(Thread.currentThread().getName()+"Is back...");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("Produce: " + products.remove(0) + " Consumed");

                notify();

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }

}

public class Producer_Consumer_Problem {


    public static void main(String args[])
    {

        PC PC = new PC();

        Thread t1= new Thread(new Runnable() {
            @Override
            public void run() {
                {
                    PC.produce();
                }

            }

        },"One");


        Thread t2= new Thread(new Runnable() {
            @Override
            public void run() {
                {
                    PC.consume();
                }
            }
        },"Two");

        t1.start();
        t2.start();
      /*try {

            t1.join();
            t2.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }

}
