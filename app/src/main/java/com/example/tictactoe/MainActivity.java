package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // Active Player
    // 0 - X
    // 1 - O
    int activePlayer = 0;
    int updateCount = 0;
    boolean isGameActive = true;
    // Block Status
    // 0 - X
    // 1 - O
    // 2 - Null
    int []gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2,};

    int [][]winConditions = {{0,1,2}, {3,4,5}, {6,7,8},
                             {0,3,6}, {1,4,7}, {2,5,8},
                             {0,4,8}, {2,4,6}};
    public void playerClick(View view){
        ImageView img = (ImageView) view;
        TextView status = findViewById(R.id.status);

        int activeBlock = Integer.parseInt(img.getTag().toString());
        if(isGameActive && gameState[activeBlock] == 2){
            if(activePlayer == 0){
                gameState[activeBlock] = 0;
                img.setImageResource(R.drawable.tictactoecross1);
                updateCount++;
                if(updateCount < 9){
                    activePlayer = 1;
                    status.setText("Player 2's Turn");
                }else{
                    status.setText("Draw");
                    isGameActive = false;
                }

            }else{
                gameState[activeBlock] = 1;
                updateCount++;
                img.setImageResource(R.drawable.tictactoecircle1);
                if(updateCount < 9){
                    activePlayer = 0;
                    status.setText("Player 1's Turn");
                }else{
                    status.setText("Draw");
                    isGameActive = false;
                }
            }
        }
        for(int[] winPosition: winConditions){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2){
                if(activePlayer == 1){
                    status.setText("Player 1 Wins");
                    isGameActive = false;
                }else{
                    status.setText("Player 2 Wins");
                    isGameActive = false;
                }
            }
        }

    }

    public void gameReset(View view){
        TextView status = findViewById(R.id.status);

        int[] imageViewIds = {
                R.id.imageView1, R.id.imageView2, R.id.imageView3,
                R.id.imageView4, R.id.imageView5, R.id.imageView6,
                R.id.imageView7, R.id.imageView8, R.id.imageView9
        };

        for (int imageViewId : imageViewIds) {
            ((ImageView) findViewById(imageViewId)).setImageResource(0);
        }

        activePlayer = 0;
        isGameActive = true;
        Arrays.fill(gameState, 2);
        status.setText("Start the Game");
        updateCount = 0;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }


}