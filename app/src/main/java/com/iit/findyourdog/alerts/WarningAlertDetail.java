package com.iit.findyourdog.alerts;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.iit.findyourdog.R;

public class WarningAlertDetail extends AlertDialog {
    private Activity current;
    private Button btnWarning;
    private TextView txtAnswer;

    private String message;

    public WarningAlertDetail(Activity current, String message) {
        super(current);
        this.current = current;
        this.message = message;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.warning_message);

        btnWarning = findViewById(R.id.btnWrong);
        txtAnswer = findViewById(R.id.txtAnswerWrong);

        txtAnswer.setText(message);
        btnWarning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                WarningAlertDetail.this.dismiss();
            }
        });

    }

}
