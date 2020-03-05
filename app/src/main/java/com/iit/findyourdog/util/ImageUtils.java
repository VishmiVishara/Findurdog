package com.iit.findyourdog.util;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class ImageUtils {
    public static Drawable getDrawable(Context mContext, String name) {
        int resourceId = mContext.getResources().getIdentifier(name, "drawable", mContext.getPackageName());
        return mContext.getResources().getDrawable(resourceId);
    }
}
