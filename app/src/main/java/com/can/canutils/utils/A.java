package com.can.canutils.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CAN on 2020/4/21.
 */
public class A {

    public void test() {
        long time1 = System.currentTimeMillis();
        String str = "";
        List list = new ArrayList();
        StringBuilder sb = new StringBuilder();
        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < 10000; i++) {
            str.concat(i+"");
        }
        System.out.println("total time = " + (System.currentTimeMillis() - time1));
    }
}
