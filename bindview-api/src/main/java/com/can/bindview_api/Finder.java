package com.can.bindview_api;

import android.app.Activity;
import android.view.View;

/**
 * Created by CAN on 18-8-22.
 */

public enum Finder {
    VIEW
            {
                @Override
                public View findView(Object source, int id)
                {
                    return ((View) source).findViewById(id);
                }
            },
    ACTIVITY
            {
                @Override
                public View findView(Object source, int id)
                {
                    return ((Activity) source).findViewById(id);
                }
            };

    public abstract View findView(Object source, int id);
}
