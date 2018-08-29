package com.li.framework.base.activity;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * @author Li
 * @since 2018/8/29
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Toast toast;

    @SuppressLint("ShowToast")
    protected void toast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(getApplication(), msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    @SuppressLint("ShowToast")
    protected void toast(int stringRes) {
        if (toast == null) {
            toast = Toast.makeText(getApplication(), stringRes, Toast.LENGTH_SHORT);
        } else {
            toast.setText(stringRes);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }
}
