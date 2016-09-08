package com.sm.ej.nk.homeal.decoration;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Tacademy on 2016-09-08.
 */
public class GridDividerDecoration extends RecyclerView.ItemDecoration{
    private final int[] ATTRS = {android.R.attr.listDivider};

    private Drawable mDivider;
    private int mInsets;

    public GridDividerDecoration(Resources resources, int layout){
        mDivider = resources.getDrawable(layout);
        mInsets = 1;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawVertical(c, parent);
        drawHorizontal(c, parent);
    }

    private void drawVertical(Canvas c, RecyclerView parent){
        if(parent.getChildCount()==0) return;

        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final View child = parent.getChildAt(0);
        if(child.getHeight() ==0) return;

        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)child.getLayoutParams();
        int top = child.getBottom() + params.bottomMargin + mInsets;
        int bottom = top + mDivider.getIntrinsicHeight();

        final int paramBottom = parent.getHeight() - parent.getPaddingBottom();
        while(bottom < paramBottom){
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);

            top += mInsets + params.topMargin + child.getHeight() + params.bottomMargin + mInsets;
            bottom = top + mDivider.getIntrinsicHeight();
        }
    }
    private void drawHorizontal(Canvas c, RecyclerView parent){
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount= parent.getChildCount();
        for(int i=0; i<childCount; i++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin + mInsets;
            final int right = left + mDivider.getIntrinsicWidth();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(mInsets, mInsets, mInsets, mInsets);
    }
}