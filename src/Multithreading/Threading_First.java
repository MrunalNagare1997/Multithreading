package Multithreading;

class Extend_Thread_Class extends Thread{

    @Override
    public void run()
    {

        try
        {
            System.out.println(Thread.currentThread().getName()+"    "+Thread.currentThread().getId()+"Priority: "+Thread.currentThread().getPriority());

        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}

class Implement_Runable_Interface implements Runnable{


    @Override
    public void run() {
        try {

            System.out.println(Thread.currentThread().getName()+"    "+Thread.currentThread().getId());

        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}

class Threading_First extends Thread {

    public static void main(String args[])
    {

        Extend_Thread_Class thread1= new Extend_Thread_Class();

        System.out.println("Current state: "+Thread.currentThread().getName()+"Priority: "+Thread.currentThread().getPriority());
        System.out.println("State of Thread 1: "+thread1.getState());
        thread1.start();
        System.out.println("Current state:(after starting-1) "+Thread.currentThread().getName());
        System.out.println("State of Thread 1: "+thread1.getState());

        System.out.println("\n\nJoining thread 1: ");
        try
        {
            System.out.println("Current state:(before Joining-1) "+Thread.currentThread().getName());
            thread1.join();
            System.out.println("Current state:(after Joining-1) "+Thread.currentThread().getName());
            System.out.println("State of Thread 1: "+thread1.getState());

        }catch (Exception e)
        {
            e.printStackTrace();
        }





        //Daemon thread.................
        System.out.println("\n\n\n\nDaemon Thread\n\n");
        Extend_Thread_Class daemonthread= new Extend_Thread_Class();
        System.out.println("Is daemon: "+daemonthread.isDaemon()+"\nPriority: "+daemonthread.getPriority());
        daemonthread.setDaemon(true);
        daemonthread.start();
        System.out.println("Is daemon: "+daemonthread.isDaemon()+"\nPriority: "+daemonthread.getPriority()
                +"\nState: "+daemonthread.getState());

    }


}




