package com.iit.findyourdog.alerts;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.iit.findyourdog.R;

public class successfulAlert extends Dialog {

    private Activity current;
    private Button btnSuccess;

    public successfulAlert(Activity current) {
        super(current);
        this.current = current;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.successfull_alert);

        btnSuccess = findViewById(R.id.btnSuccess);
        btnSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                successfulAlert.this.dismiss();
            }
        });

    }

}
