package com.li.framework.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * @author Li
 * @since 2018/8/26
 */
public class Common {

    private Common() {
    }

    private static Toast toast;


    @SuppressLint("ShowToast")
    public static void toast(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    @SuppressLint("ShowToast")
    public static void toast(Context context, @StringRes int stringRes) {
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), stringRes, Toast.LENGTH_SHORT);
        } else {
            toast.setText(stringRes);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }
}
