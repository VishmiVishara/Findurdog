package com.iit.findyourdog;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.iit.findyourdog.util.DogBreeds;
import com.iit.findyourdog.util.SliderAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SearchDogBreedsActivity extends AppCompatActivity {

    //UI Components
    private AutoCompleteTextView search;
    private ViewPager slider;
    private Button btnSearchBreed;
    private Button btnStop;

    //Instance Variables
    private List<String> dogImagesList        = new ArrayList<>();
    private Timer swipeTimer                  = new Timer();
    private final Handler handler             = new Handler();
    private List<String> suggestionBreedNames = new ArrayList<>();
    private SliderAdapter sliderAdapter       = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_dog_breeds);

        initializeUIComponents();
    }

    private void initializeUIComponents() {
        search = findViewById(R.id.search);
        btnSearchBreed = findViewById(R.id.btnSearchBreeds);
        slider = findViewById(R.id.slider);
        btnStop = findViewById(R.id.btnSliderStop);

        suggestionBreedNames = DogBreeds.getInstance().getShowBreeds();
        suggestionBreedNames.remove(suggestionBreedNames.size() - 1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, suggestionBreedNames);

        search.setThreshold(0);
        search.setAdapter(adapter);
        initializeListeners();


        btnStop.setEnabled(false);
        search.setEnabled(true);
    }

    private void setSlider() {
        reset();

        String searchedBreedName = search.getText().toString();
        if ( searchedBreedName.isEmpty()) {
            slider.setVisibility(View.INVISIBLE);
            btnStop.setEnabled(false);
            search.setEnabled(true);
            Toast.makeText(getApplicationContext(), "Sorry! Please Type Breed Name!", Toast.LENGTH_SHORT).show();
            if (sliderAdapter != null) {
                sliderAdapter.notifyDataSetChanged();
            }
            return;
        }

        if (!DogBreeds.getInstance().getShowBreeds().contains(searchedBreedName)) {

            slider.setVisibility(View.INVISIBLE);
            btnStop.setEnabled(false);
            search.setEnabled(true);
            Toast.makeText(getApplicationContext(), "Sorry! Please Type a Valid Breed Name!", Toast.LENGTH_SHORT).show();
            btnStop.setEnabled(false);
            if (sliderAdapter != null) {
                sliderAdapter.notifyDataSetChanged();
            }


            return;
        }
        slider.setVisibility(View.VISIBLE);

        searchedBreedName = searchedBreedName.replaceAll("\\s+", "").toLowerCase();
        System.out.println(searchedBreedName);
        for (int imageIndex = 0; imageIndex < 10; imageIndex++) {
            String imageName = DogBreeds.getInstance().getRandomImage(searchedBreedName);
            dogImagesList.add(imageName);
        }

        sliderAdapter = new SliderAdapter(SearchDogBreedsActivity.this, dogImagesList);
        slider.setAdapter(sliderAdapter);

        setSliderTimer();
    }

    private void reset() {
        stopTimer();
        dogImagesList.clear();
    }

    private void initializeListeners() {
        btnSearchBreed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search.setEnabled(false);
                btnStop.setEnabled(true);
                btnStop.setClickable(true);
                setSlider();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search.setEnabled(true);
                btnStop.setEnabled(false);
                btnStop.setClickable(false);
                stopTimer();

            }
        });
        btnStop.setEnabled(false);
        btnStop.setClickable(false);

    }

    private void stopTimer() {
        if (swipeTimer != null) {
            swipeTimer.cancel();
            swipeTimer = null;
        }
    }

    private void setSliderTimer() {

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {

                if (slider.getCurrentItem() == dogImagesList.size() - 1) {
                    slider.setCurrentItem(0, true);
                } else {
                    slider.setCurrentItem(slider.getCurrentItem() + 1, true);
                }
            }
        };

        swipeTimer = new Timer();
        swipeTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 0, 5000);

    }
}