package com.iit.findyourdog.alerts;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.iit.findyourdog.R;

/**
 * SuccessfulAlertDetail: Show selection was successful in detail
 */

public class SuccessfulAlertDetail extends AlertDialog {

    private Activity current;
    private Button btnSuccess;
    private TextView txtMessage;

    private String message;

    public SuccessfulAlertDetail(Activity current, String message) {
        super(current);
        this.current = current;
        this.message = message;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success_message);

        btnSuccess = findViewById(R.id.btnSuccess);
        txtMessage = findViewById(R.id.txtAnswer);
        txtMessage.setText(message); //set message to text field

        // alert dismiss when user clicks ok
        btnSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SuccessfulAlertDetail.this.dismiss();
            }
        });

    }

}
