package com.iit.findyourdog.alerts;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.iit.findyourdog.R;

/**
 * TimesUpAlert: Show user time is over
 */

public class TimesUpAlert extends AlertDialog {
    private Activity current;
    private Button btnSuccess;
    private ConstraintLayout constraintLayoutView;

    public TimesUpAlert(Activity current) {
        super(current);
        this.current = current;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.times_up_message);

        btnSuccess = findViewById(R.id.btnSuccess);
        constraintLayoutView = findViewById(R.id.constraintLayout4);

        // added animation for the alert
        final Animation animShake = AnimationUtils.loadAnimation(getContext(), R.anim.vibrate);
        constraintLayoutView.startAnimation(animShake);

        // alert dismiss when user clicks ok
        btnSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimesUpAlert.this.dismiss();
            }
        });


    }

}
