package com.attosoft.mvpdemo.util;

import android.widget.Toast;

import com.attosoft.mvpdemo.App;

/**
 * Created by ruyaya on 16/1/24.
 */
public class ToastUtil {

    public void show(String msg){
        mToast.setText(msg);
        mToast.show();
    }

    private ToastUtil() {
        mToast = Toast.makeText(App.getAppContext(),"",Toast.LENGTH_SHORT);
    }

    private static volatile ToastUtil mInstance = null;
    private Toast mToast;

    public static ToastUtil getInstance() {
        ToastUtil localInstance = mInstance;
        if (localInstance == null) {
            synchronized (ToastUtil.class) {
                localInstance = mInstance;
                if (localInstance == null) {
                    mInstance = localInstance = new ToastUtil();
                }
            }
        }
        return localInstance;
    }
}
