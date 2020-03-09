package com.iit.findyourdog.alerts;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.iit.findyourdog.R;

public class TimesUpAlert extends AlertDialog {

    private Activity current;
    private Button btnSuccess;

    public TimesUpAlert(Activity current) {
        super(current);
        this.current = current;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.times_up_message);

        btnSuccess = findViewById(R.id.btnSuccess);

        btnSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimesUpAlert.this.dismiss();
            }
        });

    }

}
