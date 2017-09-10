package com.example.karthiksingh.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int activePlayer=0;

    boolean winned = false;
    boolean gameIsActive = true;

    int gameState[]={2,2,2,2,2,2,2,2,2};//2 means unplayed

    int winningPositions[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropin(View view) {
       //int loop = 0;


        ImageView counter = (ImageView) view;


        int tappedcounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedcounter] == 2 && gameIsActive) { // gamestate[tappedcounter] == 2 allows only to put the red and yellow in unplayed boxes only

            gameState[tappedcounter]=activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
               // Log.i("check","yellow has been set now setted to red");
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
                //Log.i("check","red has been set now setted to yellow");
            }
            counter.animate().translationYBy(1000f).rotation(180).setDuration(300);

            for(int[] winningPosition : winningPositions ) {
                //loop++;


                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2 && gameState[winningPosition[1]]!=2 && gameState[winningPosition[2]]!=2) {
                    System.out.println(gameState[winningPosition[0]]);
                    Log.i("check","entered the if loop");
                    gameIsActive = false;
                    String winner = "";
                    if (gameState[winningPosition[0]] == 0 && gameState[winningPosition[1]] == 0 && gameState[winningPosition[2]] == 0) {
                        winner = "Yellow";
                        winned = true;
                        Log.i("check",String.valueOf(winningPosition[0]));
                        Log.i("check",String.valueOf(winningPosition[1]));
                        Log.i("check",String.valueOf(winningPosition[2]));
                        Log.i("check",String.valueOf(activePlayer));

                    }
                    else {

                        if (gameState[winningPosition[0]] == 1 && gameState[winningPosition[1]] == 1 && gameState[winningPosition[2]] == 1) {
                            winner = "Red";
                            winned = true;
//                            Log.i("check",String.valueOf(winningPosition[0]));
//                            Log.i("check",String.valueOf(winningPosition[1]));
//                            Log.i("check",String.valueOf(winningPosition[2]));
//                            Log.i("check",String.valueOf(activePlayer));
                       }
                    }

                    TextView winnermessage = (TextView) findViewById(R.id.winnerMessage);

                    winnermessage.setText(winner + " has won!");

                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);

                    layout.setVisibility(View.VISIBLE);


                }


                    else{
                        boolean gameisover = true;
                        for (int counterstate : gameState) {
                            //Log.i("draw",String.valueOf(counterstate));
                            if (counterstate == 2){
                                gameisover = false;
                            }
                        }
                        //Log.i("draw",String.valueOf(gameisover));
                   // Log.i("draw",String.valueOf(winned));

                        if (gameisover&& winned==false) {
                            TextView winnermessage = (TextView) findViewById(R.id.winnerMessage);

                            winnermessage.setText("It's a draw!");

                            LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);

                            layout.setVisibility(View.VISIBLE);
                        }
                    }


                }
            }


    }
    public void playAgain(View view){
        gameIsActive=true;
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for(int i=0;i<9;i++){
            gameState[i]=2;
        }

        GridLayout gridlayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0;i<gridlayout.getChildCount();i++){
            ((ImageView) gridlayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
