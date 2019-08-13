package com.can.jni.utils;

/**
 * Created by CAN on 2019/8/13.
 */
public class JniUtils {

    static {
        System.loadLibrary("jniDemo");
    }

    public static native int add(int a, int b);
}
