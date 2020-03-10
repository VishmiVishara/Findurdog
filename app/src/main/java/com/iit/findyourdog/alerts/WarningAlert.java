package com.iit.findyourdog.alerts;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.iit.findyourdog.R;

/**
 * WarningAlert: Show user selection was wrong
 */

public class WarningAlert extends AlertDialog {
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

        // alert dismiss when user clicks ok
        btnWarning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WarningAlert.this.dismiss();
            }
        });

    }

}