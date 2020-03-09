package com.iit.findyourdog.alerts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.iit.findyourdog.MainActivity;
import com.iit.findyourdog.R;
import com.iit.findyourdog.config.Config;

public class GameSummaryAlert extends AlertDialog {
    private Activity current;
    private Button btnSuccess;
    private TextView txtWarong;
    private TextView txtCorrect;
    private TextView txtScore;
    private TextView txtHighestScore;

    private String correct;
    private String wrong;
    private String score;
    private String highestScore;

    public GameSummaryAlert(Activity current, String correct, String wrong, String score, String highestScore) {
        super(current);
        this.current = current;
        this.correct = correct;
        this.wrong = wrong;
        this.score = score;
        this.highestScore = highestScore;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_summary);

        btnSuccess = findViewById(R.id.btnCongrats);
        txtCorrect = findViewById(R.id.txtCorrect);
        txtWarong = findViewById(R.id.txtWrong);
        txtScore = findViewById(R.id.txtScore);
        txtHighestScore = findViewById(R.id.txtHighScore);

        txtCorrect.setText(correct);
        txtWarong.setText(wrong);
        txtScore.setText(score);
        txtHighestScore.setText(highestScore);

        btnSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              https://stackoverflow.com/questions/9974564/how-to-start-a-new-activity-class-in-alertdialog-popup-by-choosing-ok-button
                Config.TIMER_GAME_MODE = 0;

                Config.CORRECT_COUNT_DOG_BREED = 0;
                Config.WRONG_COUNT_DOG_BREED = 0;
                Config.SCORE_IDENTIFY_DOG_BREED = 0;

                Config.CORRECT_COUNT_DOG = 0;
                Config.WRONG_COUNT_DOG = 0;
                Config.SCORE_IDENTIFY_DOG = 0;

                Intent intent = new Intent(getContext(),MainActivity.class);
                current.startActivity(intent);
                GameSummaryAlert.this.dismiss();
            }
        });

    }

}
