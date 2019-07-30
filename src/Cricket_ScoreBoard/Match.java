package Cricket_ScoreBoard;

import java.util.Scanner;


class Player{

    private  int runs,centuries,halfCenturies;

    public Player() {
        runs=0;
        centuries=0;
        halfCenturies=0;
    }

    public void setRuns(int runs)
    {
        this.runs+=runs;
    }

    public void updateScore()
    {
        int temp=runs;
        while (temp>0)
        {
            if(temp%50==0)
                halfCenturies++;
            temp-=50;
        }

        if(halfCenturies>=2) {
            while (halfCenturies > 1) {
                if (halfCenturies % 2 == 0)
                    centuries++;
                halfCenturies -= 2;
            }
        }
    }

    public void displayPlayersScore()
    {
        System.out.println("Runs: "+runs+"\nHalf Centuries: "+halfCenturies+"\nCenturies: "+centuries+"\n\n");
    }

}

class ScoreBoard  {


    private  int runs,centuries,halfCenturies;


    public void updateScore(int runs)
    {
        this.runs += runs;

        if(this.runs!=0) {
            if (this.runs % 100 == 0) {
                this.centuries++;
            } else if (this.runs % 50 == 0) {
                this.halfCenturies++;
            }
        }

    }


    public void displayScore()
    {
        System.out.println("Final score: ");
        System.out.println("Runs: "+runs+"\nCenturies: "+centuries+"\nHalf Centuries: "+halfCenturies);
    }


}

class Match {

    private Scanner sc;
    private Player [] players = new Player[10];
    private ScoreBoard score;

    int wickets;
    int runs;
    char wicket;
    int player=0;

    public Match()
    {
        sc= new Scanner(System.in);
        score= new ScoreBoard();

        for(int i=0;i<10;i++)
        {
            players[i]=new Player();
        }
    }

    public void ball ()
    {

        if(isOver()) {
            return;
        }

        System.out.println("Wicket?");
        wicket=sc.next().charAt(0);

        if(wicket!= 'y'&& wicket!= 'Y' ) {
            System.out.println("Enter the runs...");
            runs = sc.nextInt();
            players[player].setRuns(runs);
        }
        else {
            Thread thread= new Thread(new Runnable() {
                @Override
                public void run() {
                    players[player].updateScore();
                }
            });
            thread.start();

            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            player++;
            runs = 0;
            wickets++;
        }

        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {

                score.updateScore(runs);
            }
        });


        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public boolean isOver()
    {
        if(wickets==10)
        {
            score.displayScore();
            System.out.println("\n\n\n");
            for(int i=0;i<10;i++) {
                System.out.println("Player: "+(i+1)+" Score....");
                players[i].displayPlayersScore();

            }
            return true;
        }
        return false;
    }

}
