package com.sm.ej.nk.homeal.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sm.ej.nk.homeal.R;

/**
 * Created by Tacademy on 2016-08-26.
 */
public class ViewPagerAdapter extends PagerAdapter {
    String[] datas;
    Context context;
    ImageView imageView;

    public ViewPagerAdapter(Context context,String[] data){
        this.context = context;
        this.datas = data;
    }
    @Override
    public int getCount() {
        return datas.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null;
        view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_info_ck_detail_viewpager, container, false);
        imageView = (ImageView)view.findViewById(R.id.image_ck_detail_viewpager);
        Glide.with(container.getContext()).load(datas[position]).into(imageView);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
