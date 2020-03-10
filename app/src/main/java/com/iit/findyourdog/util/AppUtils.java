package com.iit.findyourdog.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;

/**
 * Application Utilities
 */
public class AppUtils {
    //get drawables
    public static Drawable getDrawable(Context mContext, String name) {

        int resourceId = mContext.getResources().getIdentifier(name, "drawable", mContext.getPackageName());
        return mContext.getResources().getDrawable(resourceId);
    }

    public static void storeIdentifyDog(Context context, int score) {
        if (getScoreTheDog(context) > score) {
            return;
        }

        SharedPreferences preferences = context.getSharedPreferences("Score", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("identifyDog", score);
        editor.commit();
    }

    //get score of the dog identification
    public static int getScoreTheDog(Context context) {

        SharedPreferences preferences = context.getSharedPreferences("Score", Context.MODE_PRIVATE);
        return  preferences.getInt("identifyDog", 0);
    }

    public static void scoreIdentifyBreed(Context context, int score) {
        if (getScoreTheDogInBreed(context) > score) {
            return;
        }

        SharedPreferences preferences = context.getSharedPreferences("Score", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("identifyBreed", score);
        editor.commit();
    }

    //get score of the dog breed identification
    public static int getScoreTheDogInBreed(Context context) {

        SharedPreferences preferences = context.getSharedPreferences("Score", Context.MODE_PRIVATE);
        return preferences.getInt("identifyBreed", 0);
    }


}
