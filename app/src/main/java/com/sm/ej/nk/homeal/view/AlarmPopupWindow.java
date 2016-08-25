package com.sm.ej.nk.homeal.view;

import android.content.Context;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class AlarmPopupWindow extends PopupWindow {
    Context context;
    private static AlarmPopupWindow instance;
    public static AlarmPopupWindow getinstance(Context context){
        if(instance!=null){
            instance = new AlarmPopupWindow(context);
        }
        return instance;
    }

    public AlarmPopupWindow(Context context){
        this.context = context;

        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        setOutsideTouchable(true);
        setFocusable(true);

//        View view = LayoutInflater.from(context).inflate()
    }

}
