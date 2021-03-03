package com.aryaman.bnhcs;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ImageAdapter extends PagerAdapter {
    private Context mcontext;
    int[] mImageIds = new int[] {R.drawable.prayers_1, R.drawable.prayers_2, R.drawable.prayers_3, R.drawable.prayers_4, R.drawable.prayers_5, R.drawable.prayers_6, R.drawable.prayers_7, R.drawable.prayers_8, R.drawable.prayers_9, R.drawable.prayers_10, R.drawable.prayers_11, R.drawable.prayers_12, R.drawable.prayers_13, R.drawable.prayers_14};
    ImageAdapter(Context context) {
        mcontext = context;
    }
    @Override
    public int getCount() {
        return mImageIds.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mcontext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(mImageIds[position]);
        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((ImageView) object);
    }
}
