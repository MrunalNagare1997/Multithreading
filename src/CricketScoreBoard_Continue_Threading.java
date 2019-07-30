import java.util.*;


class Players{

    private int runs;
    private int half_Centuries;
    private int centuries;

    public Players() {

        runs=0;
        centuries=0;
        half_Centuries=0;
    }

    public int getRuns() {
        return runs;
    }


    public void setRuns(int runs) {
        this.runs += runs;
    }

    public void setHalf_Centuries(int half_Centuries) {
        this.half_Centuries = half_Centuries;
    }

    public void setCenturies(int centuries) {
        this.centuries = centuries;
    }

    public void showScore()
    {
        System.out.println("Total Runs: "+runs+"\nTotal centuries: "+centuries+
                "\nTotal Half centuries: "+half_Centuries);

    }
}

class Match{


    private Players[] player_List;

    private boolean match_On;
    private Scanner sc;
    private int totalruns;
    private int total_Centuries;
    private int total_Half_Centuries;
    private int wickets;
    private int player_no;
    private int century_count;
    private int half_century_Count;
    private int balls;

    public Match()
    {
        player_List= new Players[10];


        for(int i=0; i<10;i++)
        {
            player_List[i]= new Players();
        }

        match_On=true;
        sc= new Scanner(System.in);
        totalruns=0;
        total_Centuries=0;
        total_Half_Centuries=0;
        wickets=0;
        player_no=0;
        century_count=0;
        half_century_Count=0;
        balls=0;


    }

    public void showScore()
    {
        System.out.println(".........Inning score.......\n");
        System.out.println("Total Runs: "+totalruns+"\nTotal centuries: "+total_Centuries+
                "\nTotal Half centuries: "+total_Half_Centuries+"\n\nPlayers score\n\n");
        for(int i=0;i<10;i++)
        {
            System.out.println("Player"+(i+1)+": \n");
            player_List[i].showScore();
            System.out.println();
        }

    }

    public void ball()
    {
       int run=0;

        while(true) {
            synchronized (this) {

                if(wickets==10 || balls==10)
                {
                    match_On=false;
                    notify();
                    break;
                }
                balls++;
                System.out.println("Enter runs...");
                run=sc.nextInt();

                if(run==-1)
                {
                    wickets++;
                    run=0;
                    player_no++;
                    half_century_Count=0;
                    century_count=0;
                    continue;

                }

                totalruns+=run;
                player_List[player_no].setRuns(run);

                try {
                    System.out.println("total runs: "+totalruns);
                    notify();
                    wait();

                    //System.out.println(Thread.currentThread().getName()+"Enter runs...");
                }
                catch (InterruptedException e)
                {

                    e.printStackTrace();

                }

                try {

                    Thread.sleep(100);

                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }
        }
        System.out.println("Inning over...\nFollowing are the scores...\n");
    }

    public void updateScore() {

        while (true) {
            synchronized (this) {
                if(!match_On) {
                    showScore();
                    break;
                }
                if (player_List[player_no].getRuns()>=50 && half_century_Count<1) {
                    player_List[player_no].setHalf_Centuries(1);
                    half_century_Count++;
                    total_Half_Centuries++;
                }
                if (player_List[player_no].getRuns()>=100 && century_count<1) {
                    player_List[player_no].setCenturies(1);
                    player_List[player_no].setHalf_Centuries(0);
                    century_count++;
                    total_Half_Centuries--;
                    total_Centuries++;
                }
                try {

                    notify();
                    wait();

                    //System.out.println(Thread.currentThread().getName()+"Is back...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {

                    Thread.sleep(100);
                } catch (InterruptedException e) {

                    e.printStackTrace();

                }
            }

        }
        //System.out.println("Second inning...");
    }

}
public class CricketScoreBoard_Continue_Threading {

    public static void main(String args[])
    {

        Match firstInning = new Match();
        Match secondInning = new Match();
        System.out.println("First inning...");
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                firstInning.ball();
            }
            }, "One");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                firstInning.updateScore();
            }
            }, "Two");

        t1.start();
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("Second inning...");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                secondInning.ball();
            }
        }, "One");

        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                secondInning.updateScore();
            }
        }, "Two");

        t3.start();
        t4.start();

    }

}
