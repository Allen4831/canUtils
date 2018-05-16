package com.can.mvp.utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by can on 2018/4/9.
 */

public class OtherUtils {

    /**
     * 关闭软键盘
     * @param activity
     */
    public static void closeKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(inputMethodManager != null && activity.getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 2);
        }

    }

}
