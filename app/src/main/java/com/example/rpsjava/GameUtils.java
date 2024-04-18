package com.example.rpsjava;

import android.content.Context;

import java.util.Random;

public class GameUtils {
    private int winning_score;
    private int my_score;
    private int opp_score;
    private String myMove;
    private String oppMove;
    private Random random;

    public GameUtils(int winning_score, Context context){
        this.winning_score = winning_score;
        my_score           = 0;
        opp_score          = 0;
        this.random        = new Random();
    }

    public String play(String move){
        this.myMove     = move;
        this.oppMove    = getOpponentMove();
        String result   = getResult();
        return  result;
    }

    private String getOpponentMove(){
        String[] moves = {"Rock","Paper", "Scissors"};
        int index = random.nextInt(moves.length);
        return moves[index];
    }
    private String getResult(){
        if(myMove.equals(oppMove)){
            return "Tie";
        }else if((myMove.equals("Rock") && oppMove.equals("Scissors"))
                || (myMove.equals("Paper") && oppMove.equals("Rock"))
                || (myMove.equals("Scissors") && oppMove.equals("Paper"))){
            my_score++;
            return "You Win";
        }else{
            opp_score++;
            return "Opponent Wins";
        }
    }

    public int getMy_score(){
        return my_score;
    }
    public int getOpp_score(){
        return opp_score;
    }
    public int getWinning_score(){
        return winning_score;
    }
    public String getGameResult(){
        if(my_score>= winning_score){
            return "YOU WIN";
        }else if(opp_score>=winning_score){
            return "OPPONENT WINS";
        }else{
            return "GAME IN PROGRESS";
        }
    }

}
