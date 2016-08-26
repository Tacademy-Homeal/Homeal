package com.sm.ej.nk.homeal.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Tacademy on 2016-08-26.
 */
public class ViewPagerAdapter extends PagerAdapter {
    int size;
    Context context;

    public ViewPagerAdapter(Context context,int size){
        this.context = context;
        this.size = size;
    }
    @Override
    public int getCount() {
        return size;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

}
