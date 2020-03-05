package com.iit.findyourdog.util;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class CustomizedOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

    private String selectedItemValue = null;

//    public CustomizedOnItemSelectedListener(Spinner spinner) {
//        super();
//    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos,
                               long id) {
        selectedItemValue = parent.getItemAtPosition(pos).toString();
        System.out.println(selectedItemValue);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        selectedItemValue = "ERROR";

    }

    public String getSelectedItemValue() {
        return selectedItemValue;
    }
}