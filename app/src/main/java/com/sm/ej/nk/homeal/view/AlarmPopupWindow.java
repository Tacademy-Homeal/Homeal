package com.sm.ej.nk.homeal.view;

import android.content.Context;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Created by Tacademy on 2016-08-24.
 */
public class AlarmPopupWindow extends PopupWindow {
    Context context;
    public AlarmPopupWindow(Context context){
        super(context);
        this.context = context;

        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

//        View view = LayoutInflater.from(context).inflate(R.layout , null);
//        setContentView(view);
//
//        View = view.findViewById().....
    }
}
