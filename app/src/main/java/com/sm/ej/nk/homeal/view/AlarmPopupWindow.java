package com.sm.ej.nk.homeal.view;

import android.content.Context;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class AlarmPopupWindow extends PopupWindow {
    Context context;
    public AlarmPopupWindow(Context context){
        this.context = context;

        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setWidth(WindowManager.LayoutParams.WRAP_CONTENT);

//        View view = LayoutInflater.from(context).inflate()
    }

}
