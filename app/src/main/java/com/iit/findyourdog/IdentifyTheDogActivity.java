package com.iit.findyourdog;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.iit.findyourdog.alerts.GameSummaryAlert;
import com.iit.findyourdog.alerts.SuccessfulAlert;
import com.iit.findyourdog.alerts.TimesUpAlert;
import com.iit.findyourdog.alerts.WarningAlert;
import com.iit.findyourdog.config.Config;
import com.iit.findyourdog.util.AppUtils;
import com.iit.findyourdog.util.DogBreeds;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IdentifyTheDogActivity extends AppCompatActivity implements View.OnClickListener {

    //UI Components
    private ImageView imageOne;
    private ImageView imageTwo;
    private ImageView imageThree;
    private Button btnSubmit;
    private TextView txtBreedName;
    private ConstraintLayout imageOneBorder;
    private ConstraintLayout imageTwoBorder;
    private ConstraintLayout imageThreeBorder;
    private ProgressBar progressBar;
    private TextView txtCount;
    private ConstraintLayout timerConstraintLayout;

    //Instance Variables
    private List<String> randomGeneratedBreedList    = new ArrayList<>();
    private List<ImageView> imageViewList            = new ArrayList<>();
    private List<ConstraintLayout> imageBorderedList = new ArrayList<>();
    private int randomPickedHeadingIndex             = 0;
    private boolean btnSubmitState                   = false;
    private int selectedImageIndex                   = -1;
    private CountDownTimer timer                     = null;
    private int remainingTime = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_the_dog);

        initializeVIew();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageViewOne:

                imageOneBorder = findViewById(R.id.conImageOne);
                imageTwoBorder = findViewById(R.id.conImageTwo);
                imageThreeBorder = findViewById(R.id.conImageThree);

                imageThreeBorder.setBackgroundColor(Color.TRANSPARENT);
                imageTwoBorder.setBackgroundColor(Color.TRANSPARENT);
                imageOneBorder.setBackgroundColor(Color.YELLOW);
                selectedImageIndex = (int) imageOne.getTag();

                break;
            case R.id.imageViewTwo:

                imageOneBorder.setBackgroundColor(Color.TRANSPARENT);
                imageThreeBorder.setBackgroundColor(Color.TRANSPARENT);
                imageTwoBorder.setBackgroundColor(Color.YELLOW);
                selectedImageIndex = (int) imageTwo.getTag();

                break;
            case R.id.imageViewThree:

                imageOneBorder.setBackgroundColor(Color.TRANSPARENT);
                imageTwoBorder.setBackgroundColor(Color.TRANSPARENT);
                imageThreeBorder.setBackgroundColor(Color.YELLOW);
                selectedImageIndex = (int) imageThree.getTag();

                break;

            case R.id.btnSubmitDog:

                playTimerGameMode(0);

                break;


        }
    }

    private void initializeVIew (){
        initializeTheUIComponents();

        if (Config.TIMER_GAME_MODE == 0) {
            timerConstraintLayout.setVisibility((View.GONE));
        } else {
            timerConstraintLayout.setVisibility((View.VISIBLE));
            setupTimer();
        }
    }

    private void initializeTheUIComponents() {
        imageOne = findViewById(R.id.imageViewOne);
        imageTwo = findViewById(R.id.imageViewTwo);
        imageThree = findViewById(R.id.imageViewThree);
        btnSubmit = findViewById(R.id.btnSubmitDog);
        txtBreedName = findViewById(R.id.txtBreedName);
        imageOneBorder = findViewById(R.id.conImageOne);
        imageTwoBorder = findViewById(R.id.conImageTwo);
        imageThreeBorder = findViewById(R.id.conImageThree);
        progressBar = findViewById(R.id.progressCircularDog);
        txtCount = findViewById(R.id.txtCountDog);
        timerConstraintLayout = findViewById(R.id.constraintLayoutTimerDog);


        //clear image view and random images list
        imageViewList.clear();
        randomGeneratedBreedList.clear();

        imageViewList.add(imageOne);
        imageViewList.add(imageTwo);
        imageViewList.add(imageThree);

        imageBorderedList.add(imageOneBorder);
        imageBorderedList.add(imageTwoBorder);
        imageBorderedList.add(imageThreeBorder);


        btnSubmit.setOnClickListener(this);
        setImagesToView();
        setHeading();

    }

    private void playTimerGameMode(int val) {
        if (!btnSubmitState) {
            //validate user is select a image or not
            if (selectedImageIndex == -1) {
                if (val == 1) {
                    if (Config.TIMER_GAME_MODE == 1) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Answer marked in Blue!", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
                        timeOut();
                        toast.show();
                    }
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Please select a Dog!", Toast.LENGTH_SHORT);
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

            if (selectedImageIndex == randomPickedHeadingIndex) {
                SuccessfulAlert identifyBreedCorrectMessage = new SuccessfulAlert(IdentifyTheDogActivity.this);
                identifyBreedCorrectMessage.show();

                Config.CORRECT_COUNT_DOG += 1;
                Config.SCORE_IDENTIFY_DOG += 10;

                imageBorderedList.get(selectedImageIndex).setBackgroundColor(Color.GREEN);
            } else if (selectedImageIndex != randomPickedHeadingIndex && selectedImageIndex != -1) {
                WarningAlert identifyBreedWrongMessage = new WarningAlert(IdentifyTheDogActivity.this);
                identifyBreedWrongMessage.show();

                imageBorderedList.get(selectedImageIndex).setBackgroundColor(Color.RED);
                imageBorderedList.get(randomPickedHeadingIndex).setBackgroundColor(Color.GREEN);

                Config.WRONG_COUNT_DOG += 1;
            }
            btnSubmit.setText("Next");
            btnSubmitState = true;

        } else if (btnSubmitState) {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
            btnSubmitState = false;
        }
    }

    private void setImageToView(ImageView imageView, int position) {
        String randomBreedName = DogBreeds.getInstance().getRandomBreed();
        if (!randomGeneratedBreedList.contains(randomBreedName)) {
            randomGeneratedBreedList.add(randomBreedName);
        }
        String imageName = DogBreeds.getInstance().getRandomImage(randomBreedName);
        imageView.setImageDrawable(AppUtils.getDrawable(this, imageName));
        imageView.setTag(position);
    }

    private void setImagesToView() {
        for (int index = 0; index < 3; index++) {
            imageViewList.get(index).setOnClickListener(IdentifyTheDogActivity.this);
            setImageToView(imageViewList.get(index), index);
        }
    }

    private void setHeading() {
        Random random = new Random();
        randomPickedHeadingIndex = random.nextInt(3);
        String breedName = randomGeneratedBreedList.get(randomPickedHeadingIndex);
        txtBreedName.setText(DogBreeds.getInstance().getDogBreedMap().get(breedName));
    }

    private void timeOut(){

        //Vibrate phone when time's up
        Vibrator v = (Vibrator) getSystemService(this.VIBRATOR_SERVICE);
        v.vibrate(400);
        TimesUpAlert timesUpAlert =
                new TimesUpAlert(IdentifyTheDogActivity.this);
        timesUpAlert.show();
        Config.WRONG_COUNT_DOG += 1;
        imageBorderedList.get(randomPickedHeadingIndex).setBackgroundColor(Color.BLUE);
        timerConstraintLayout.setVisibility(View.GONE);
        btnSubmit.setText("Next");
        btnSubmitState = true;
    }

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
                selectedImageIndex = -1;
                playTimerGameMode(1);
            }
        };

        timer.start();
    }

//    https://stackoverflow.com/questions/4861859/implement-sounds-in-an-android-application
    private void playSound() {
        MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.clock_sound);
        mPlayer.start();
    }


    @Override
    public void onBackPressed() {
        String correct = "" + Config.CORRECT_COUNT_DOG;
        String wrong = "" + Config.WRONG_COUNT_DOG;
        String noAnswer = "YOUR SCORE: " + Config.SCORE_IDENTIFY_DOG;

        if (Config.HIGHEST_SCORE_IDENTIFY_DOG < Config.SCORE_IDENTIFY_DOG ||
                Config.HIGHEST_SCORE_IDENTIFY_DOG == 0){
            Config.HIGHEST_SCORE_IDENTIFY_DOG = Config.SCORE_IDENTIFY_DOG;
        }

        String highScore = "HIGHEST SCORE: " + Config.HIGHEST_SCORE_IDENTIFY_DOG;
        System.out.println(Config.HIGHEST_SCORE_IDENTIFY_DOG_BREED);

        GameSummaryAlert summaryAlert =
                new GameSummaryAlert(IdentifyTheDogActivity.this,correct, wrong,noAnswer, highScore);
        summaryAlert.show();
    }

}