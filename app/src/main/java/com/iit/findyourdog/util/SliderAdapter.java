/**
 * dzone.com. 2020. Working With Image Sliders In Android - Dzone Mobile. [online]
 * Available at: <https://dzone.com/articles/working-with-image-slider-in-android>
 * [Accessed 8 March 2020].
 */

package com.iit.findyourdog.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.iit.findyourdog.R;

import java.util.List;

/**
 * Slider Adapter
 */
public class SliderAdapter extends PagerAdapter {
    private Context context;
    private List<String> imageList;
    private LayoutInflater inflater;


    public SliderAdapter(Context context, List<String> imageList) {
        this.context = context;
        this.imageList = imageList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }


    //initiate item
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View imageLayout = inflater.inflate(R.layout.image_slider, container, false);
        assert imageLayout != null;
        ImageView imageView =  imageLayout.findViewById(R.id.imgSlider);
        // setting image to image view
        imageView.setImageDrawable(AppUtils.getDrawable(context, imageList.get(position)));
        container.addView(imageLayout, 0);
        return imageLayout;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
     container.removeView((View) object);
    }

    //get count
    @Override
    public int getCount() {
        return imageList.size();
    }
}
