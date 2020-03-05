package com.iit.findyourdog.alerts;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.iit.findyourdog.R;

public class WarningAlert extends Dialog {
    private Activity current;
    private Button btnWarning;

    public WarningAlert(Activity current) {
        super(current);
        this.current = current;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.warning_alert);

        btnWarning = findViewById(R.id.btnWarning);
        btnWarning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                WarningAlert.this.dismiss();
            }
        });

    }

}
