package com.iit.findyourdog;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.iit.findyourdog.alerts.SuccessfulAlertDetail;
import com.iit.findyourdog.alerts.TimesUpAlert;
import com.iit.findyourdog.alerts.WarningAlertDetail;
import com.iit.findyourdog.config.Config;
import com.iit.findyourdog.util.AppUtils;
import com.iit.findyourdog.util.CustomAdapter;
import com.iit.findyourdog.util.DogBreeds;

/**
 * IdentifyTheBreedActivity: Game One - Identify the Dog Breed
 */
public class IdentifyTheBreedActivity extends AppCompatActivity {

    //UI Components
    private ImageView imageView;
    private Button btnSubmit;
    private Spinner spinner;
    private ProgressBar progressBar;
    private TextView txtCount;
    private ConstraintLayout timerConstraintLayout;

    private CustomAdapter dataAdapter = null;
    private boolean btnSubmitState = false;
    private String randomBreedName = null;
    private String imageName = null;
    private CountDownTimer timer = null;
    private int remainingTime = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_the_breed);

        // bind UI components
        initializeUIComponents();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }

    private void initializeUIComponents() {
        imageView = findViewById(R.id.imageDog);
        btnSubmit = findViewById(R.id.btnSubmitDog);
        spinner = findViewById(R.id.spinner);
        progressBar = findViewById(R.id.progressCircularBreed);
        txtCount = findViewById(R.id.txtCountBreed);
        timerConstraintLayout = findViewById(R.id.constraintLayoutTimerBreed);

        setImageToView();
        createDropDownList();

        if (Config.TIMER_GAME_MODE == 0) {
            timerConstraintLayout.setVisibility((View.GONE));
        } else {
            timerConstraintLayout.setVisibility((View.VISIBLE));
            setupTimer();
        }

        // initializing Listeners
        initializeListeners();

    }

    private void initializeListeners() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playDogBreed(0);
            }
        });

    }

    // creating dropdown
    protected void createDropDownList() {
        dataAdapter = new CustomAdapter(this,
                android.R.layout.simple_spinner_item, DogBreeds.getInstance().getShowBreeds());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setSelection(dataAdapter.getCount());

    }


    // getting images from drawable and setting it to the view
    private void setImageToView() {
        randomBreedName = DogBreeds.getInstance().getRandomBreed();
        imageName = DogBreeds.getInstance().getRandomImage(randomBreedName);
        imageView.setImageDrawable(AppUtils.getDrawable(this, imageName));
    }

    // Countdown Timer
    private void setupTimer() {
        progressBar.setProgress(0);
        timer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                remainingTime--;
                System.out.println(remainingTime);
                progressBar.setProgress(remainingTime * 100 / (10000 / 1000));
                txtCount.setText(remainingTime + "");

                if (remainingTime <= 10) {
                    playSound();
                }
            }

            @Override
            public void onFinish() {
                remainingTime++;
                progressBar.setProgress(0);
                playDogBreed(1);
            }
        };

        timer.start();
    }

    // game play
    private void playDogBreed(int val) {
        if (!btnSubmitState) {
            if (spinner.getSelectedItem().equals("SELECT A BREED..")) {

                if (val == 1) {
                    if (Config.TIMER_GAME_MODE == 1) {
                        timeOut();
                    }
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Please select a breed", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                return;
            }

            if (Config.TIMER_GAME_MODE == 1) {
                progressBar.setVisibility(View.INVISIBLE);
                timer.cancel();
                txtCount.setVisibility(View.INVISIBLE);
            }

            String selectedItem = (String) spinner.getSelectedItem();
            System.out.println("Random Generated Breed: " + randomBreedName);
            System.out.println("User selected Option: " + selectedItem);

            selectedItem = selectedItem.replaceAll("\\s+", "");
            System.out.println(selectedItem);
            System.out.println(selectedItem.toLowerCase().equals(randomBreedName));


            if (selectedItem.toLowerCase().equals(randomBreedName)) {
                String answer = "ANSWER \" " + selectedItem + " \" IS CORRECT!";
                SuccessfulAlertDetail successfulAlert =
                        new SuccessfulAlertDetail(IdentifyTheBreedActivity.this, answer);
                successfulAlert.show();
                Config.CORRECT_COUNT_DOG_BREED += 1;

                Config.SCORE_IDENTIFY_DOG_BREED += 10;

                System.out.println(Config.SCORE_IDENTIFY_DOG_BREED);
                timerConstraintLayout.setVisibility(View.GONE);

            } else {
                String answer = "CORRECT BREED: " + DogBreeds.getInstance().getDogBreedMap().get(randomBreedName);
                WarningAlertDetail warningAlert =
                        new WarningAlertDetail(IdentifyTheBreedActivity.this, answer);
                warningAlert.show();

                Config.WRONG_COUNT_DOG_BREED += 1;

                timerConstraintLayout.setVisibility(View.GONE);

            }

            spinner.setEnabled(false);
            spinner.setClickable(false);
            btnSubmit.setText("Next");
            btnSubmitState = true;

        } else if (btnSubmitState) {
            Intent intent = getIntent();
            finish();
            startActivity(intent); //starting new activity
            btnSubmitState = false;
        }

    }

    // timer time out
    private void timeOut() {
        //Vibrate phone when time's up
        Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        v.vibrate(400);

        TimesUpAlert timesUpAlert =
                new TimesUpAlert(IdentifyTheBreedActivity.this);
        timesUpAlert.show();

        Config.WRONG_COUNT_DOG_BREED += 1;

        timerConstraintLayout.setVisibility(View.GONE);
        spinner.setEnabled(false);
        spinner.setClickable(false);
        btnSubmit.setText("Next");
        btnSubmitState = true;
    }

    // playing timer sound
    private void playSound() {
        MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.clock_sound);
        mPlayer.start();
    }

    //showing Game summary when user click on back button
    @Override
    public void onBackPressed() {
        Config.IS_BREED_ACTIVITY = true;
        AppUtils.scoreIdentifyBreed(IdentifyTheBreedActivity.this, Config.SCORE_IDENTIFY_DOG_BREED);
        Intent intent = new Intent(getApplicationContext(), GameSummaryActivity.class);
        startActivity(intent);
        finish();
    }
}




