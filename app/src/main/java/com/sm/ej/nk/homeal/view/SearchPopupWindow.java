package com.sm.ej.nk.homeal.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.sm.ej.nk.homeal.R;

/**
 * Created by Tacademy on 2016-08-24.
 */
public class SearchPopupWindow extends PopupWindow {
    Context context;
    public SearchPopupWindow(Context context){
        super(context);
        this.context = context;

        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        View view = LayoutInflater.from(context).inflate(R.layout.view_popup_search , null);
        setContentView(view);

        Button btn =(Button) view.findViewById(R.id.btn_popup_search);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.onSearchPopupClick(view);
                }
                dismiss();
            }
        });
    }

    public interface OnSearchPopupClickListener{
        public void onSearchPopupClick(View view);
    }

    OnSearchPopupClickListener listener;

    public void setOnSearchPopupClickListener(OnSearchPopupClickListener listener){
        this.listener = listener;
    }
}
