package net.svetoven.swetko.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int poteza = 0;
    private String winner = "";
    int acticePlayer = 0;
    boolean gameActive = true;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositons = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    private android.support.v7.widget.GridLayout gridLayoutv7;

    public void dropIn (View view) {
        ImageView counter = (ImageView) view;

        Log.i("info", counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = acticePlayer;

            counter.setTranslationY(-2000);

            if (acticePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                acticePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                acticePlayer = 0;
            }
            counter.animate().translationYBy(2000).setDuration(500);

            for (int[] winningPosition : winningPositons) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    if (acticePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    gameActive = false;
                    //Toast.makeText(this, winner + " has won", Toast.LENGTH_LONG).show();

                }
            }

            if (poteza != 8) {
                poteza++;
            }
            else {
                winner = "None";
                gameActive = false;
            }

            if (gameActive == false) {
                Button playAgain = findViewById(R.id.playAgainButton);
                TextView winnerText = findViewById(R.id.winnerTextView);

                winnerText.setText(winner + " has won");
                playAgain.setVisibility(View.VISIBLE);
                winnerText.setVisibility(View.VISIBLE);
            }

        }
    }

    public void playAgain (View view) {
        Button playAgain = findViewById(R.id.playAgainButton);
        TextView winnerText = findViewById(R.id.winnerTextView);

        for(int i=0; i<gridLayoutv7.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayoutv7.getChildAt(i);

            counter.setImageDrawable(null);
        }
        acticePlayer = 0;

        gameActive = true;

        poteza = 0;

        for (int i = 0; i < gameState.length; i++){
            gameState[i] = 2;
        }

        playAgain.setVisibility(View.INVISIBLE);
        winnerText.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayoutv7 = findViewById(R.id.gridLayout);
    }
}
