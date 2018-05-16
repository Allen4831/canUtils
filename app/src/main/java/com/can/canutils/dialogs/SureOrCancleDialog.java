package com.can.canutils.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


/**
 * Created by can on 2018/4/9.
 */

public class SureOrCancleDialog extends Dialog implements View.OnClickListener {

    private onMyClickListener myClickListener;

    public interface onMyClickListener{
        void setMyOnClickListener(View view);
    }

    @Override
    public void onClick(View view) {
        myClickListener.setMyOnClickListener(view);
    }

    public SureOrCancleDialog(Context context, int style, onMyClickListener mListener){
        super(context,style);
        this.myClickListener = mListener;
        DisplayMetrics metrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        View view = LayoutInflater.from(context).inflate(com.can.mvp.R.layout.dialog_sure_or_cancle,null);
        view.findViewById(com.can.mvp.R.id.btn_cancle).setOnClickListener(this);
        view.findViewById(com.can.mvp.R.id.btn_save).setOnClickListener(this);
        setContentView(view);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        params.width = width*2/3;
        window.setAttributes(params);
    }

}
