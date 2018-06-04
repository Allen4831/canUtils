package com.can.mvp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by can on 2018/6/4.
 * View工具类
 */

public class ViewUtils {

    /**
     * 对图片进行质量压缩
     * @param bitmap 图片
     * @param size 压缩的大小
     * @return
     */
    public static Bitmap compressBitmap(Bitmap bitmap , int size) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int compress = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG,compress,outputStream);// 100表示不改变图片质量，把图片压缩后的数据保存到outputStream
        while(outputStream.toByteArray().length/1024 >size){//循环判断压缩后的图片是否大于压缩的大小
            outputStream.reset();
            compress -= 10; //一次减少10
            bitmap.compress(Bitmap.CompressFormat.JPEG,compress,outputStream);//继续保存压缩后的数据
        }
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());// 把压缩后的数据outputStream保存到ByteArrayInputStream
        Bitmap bitmap_new = BitmapFactory.decodeStream(inputStream);//生成新的图片
        return bitmap_new;
    }

    /**
     * 对图片进行按尺寸压缩
     * @param imagePath 图片路径
     * @param width 压缩的宽度
     * @param height 压缩的高度
     */
    public static Bitmap compressBitmap(String imagePath , int width , int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();

        int w = options.outWidth;
        int h = options.outHeight;
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > width) {// 如果宽度大的话根据宽度固定大小缩放
            be = options.outWidth / width;
        } else if (w < h && h > height) {// 如果高度高的话根据宽度固定大小缩放
            be = options.outHeight / height;
        }
        if (be <= 0)
            be = 1;
        options.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        options.inJustDecodeBounds = true;
        Bitmap bitmap_new  = BitmapFactory.decodeFile(imagePath, options);
        options.inJustDecodeBounds = false;
        return bitmap_new;
    }

    //px 转 dp
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    //dp 转 px
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    //view生成bitmap
    public static Bitmap view2Bitmap(View v) {
        v.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
        Bitmap bmp;
        int w = v.getWidth();
        int h = v.getHeight();
        bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);
        c.drawColor(Color.WHITE);
        /** 如果不设置canvas画布为白色，则生成透明 */
        v.layout(0, 0, w, h);
        v.draw(c);
        return bmp;
    }



}
