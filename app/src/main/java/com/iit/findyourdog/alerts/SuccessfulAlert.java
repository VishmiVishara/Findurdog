package com.iit.findyourdog.alerts;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.iit.findyourdog.R;


/**
 * SuccessfulAlert: Show user selection was successful
 */

public class SuccessfulAlert extends AlertDialog {
    private Activity current;
    private Button btnSuccess;

    public SuccessfulAlert(Activity current) {
        super(current);
        this.current = current;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.successfull_alert);

        // alert dismiss when user clicks ok
        btnSuccess = findViewById(R.id.btnSuccess);
        btnSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SuccessfulAlert.this.dismiss();
            }
        });

    }

}
