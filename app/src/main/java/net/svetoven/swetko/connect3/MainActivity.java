package net.svetoven.swetko.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private GridLayout gridLayout;
    int acticePlayer = 0;

    boolean gameActive = true;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositons = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

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
                    String winner = "";
                    if (acticePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    gameActive = false;
                    //Toast.makeText(this, winner + " has won", Toast.LENGTH_LONG).show();

                    Button playAgain = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerText = (TextView) findViewById(R.id.winnerTextView);

                    winnerText.setText(winner + " has won");
                    playAgain.setVisibility(View.VISIBLE);
                    winnerText.setVisibility(View.VISIBLE);

                }
            }
        }
    }

    public void playAgain (View view) {
        Button playAgain = (Button) findViewById(R.id.playAgainButton);
        TextView winnerText = (TextView) findViewById(R.id.winnerTextView);

        GridLayout gLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i=0; i<gLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gLayout.getChildAt(i);

            counter.setImageDrawable(null);
        }
        /*
        setContentView(R.layout.activity_main);
        gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);

        }
        */
        acticePlayer = 0;

        gameActive = true;

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
    }
}
