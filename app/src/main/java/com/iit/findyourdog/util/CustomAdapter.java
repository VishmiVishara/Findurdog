package com.iit.findyourdog.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.iit.findyourdog.R;

import java.util.List;

//REF: https://stackoverflow.com/questions/867518/how-to-make-an-android-spinner-with-initial-text-select-one
public class CustomAdapter extends ArrayAdapter<String> {

    private Typeface font ;

    public CustomAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);

        font  = ResourcesCompat.getFont(context, R.font.font_2);

    }

    @Override
    public int getCount() {
        // not to display last item since it's the hint
        int count = super.getCount();
        return count > 0 ? count - 1 : count;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        TextView textView = (TextView)super.getView(position, convertView, parent);
        textView.setTypeface(font);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(20);
        return textView;

    }
}