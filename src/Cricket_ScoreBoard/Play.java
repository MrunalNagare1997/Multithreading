package Cricket_ScoreBoard;

public class Play {
    public static void main(String args[]){

        Match match1=new Match();

        while(!match1.isOver()) {
            match1.ball();
        }

    }


}
