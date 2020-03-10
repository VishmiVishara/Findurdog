package com.iit.findyourdog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.iit.findyourdog.config.Config;
import com.iit.findyourdog.util.AppUtils;

public class GameSummaryActivity extends AppCompatActivity {
    private Activity current;
    private Button btnSuccess;
    private TextView txtWrong;
    private TextView txtCorrect;
    private TextView txtScore;
    private TextView txtHighestScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_summary);
        initializeUIComponents();

        // return to ActivityMain
        btnSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initializeUIComponents() {
        btnSuccess = findViewById(R.id.btnCongrats);
        txtCorrect = findViewById(R.id.txtCorrect);
        txtWrong = findViewById(R.id.txtWrong);
        txtScore = findViewById(R.id.txtScore);
        txtHighestScore = findViewById(R.id.txtHighScore);

        if (Config.IS_BREED_ACTIVITY) {
            String correct = "0";
            String wrong = "0";
            String score = "0";
            String highestScore = "0";

            correct = Config.CORRECT_COUNT_DOG_BREED + "";
            wrong = Config.WRONG_COUNT_DOG_BREED + "";
            score = Config.SCORE_IDENTIFY_DOG_BREED + "";
            highestScore = AppUtils.getScoreTheDogInBreed(this) + "";

            txtCorrect.setText(correct);
            txtWrong.setText(wrong);
            txtScore.setText(score);
            txtHighestScore.setText(highestScore);

            System.out.println(correct);
            System.out.println(wrong);
            System.out.println(score);
            System.out.println(highestScore);

        } else {
            String correct = "0";
            String wrong = "0";
            String score = "0";
            String highestScore = "0";

            correct = Config.CORRECT_COUNT_DOG + "";
            wrong = Config.WRONG_COUNT_DOG + "";
            score = Config.SCORE_IDENTIFY_DOG + "";
            highestScore = AppUtils.getScoreTheDog(this) + "";

            txtCorrect.setText(correct);
            txtWrong.setText(wrong);
            txtScore.setText(score);
            txtHighestScore.setText(highestScore);

            System.out.println(Config.CORRECT_COUNT_DOG + "");
            System.out.println(Config.WRONG_COUNT_DOG + "");
            System.out.println(Config.SCORE_IDENTIFY_DOG + "");

        }

        Config.CORRECT_COUNT_DOG_BREED = 0;
        Config.WRONG_COUNT_DOG_BREED = 0;
        Config.SCORE_IDENTIFY_DOG_BREED = 0;

        Config.CORRECT_COUNT_DOG = 0;
        Config.WRONG_COUNT_DOG = 0;
        Config.SCORE_IDENTIFY_DOG = 0;

    }
}
