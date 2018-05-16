package com.can.mvp.utils;

import com.can.mvp.base.BaseFragment;

import java.util.Stack;

/**
 * Created by can on 2018/3/6.
 * fragment管理工具
 */

public class FragmentManagerUtil {
    private static Stack<BaseFragment> FragmentStack;
    private static FragmentManagerUtil instance;

    private FragmentManagerUtil() {
    }

    /**
     * 单一实例
     */
    public static FragmentManagerUtil getInstance() {
        if (instance == null) {
            instance = new FragmentManagerUtil();
        }

        if (FragmentStack == null) {
            FragmentStack = new Stack<>();
        }

        return instance;
    }


    /**
     * 添加Fragment到堆栈
     */
    public void pushFragment(BaseFragment fragment) {
        FragmentStack.add(fragment);
    }

    /**
     * 移除Fragment
     */
    public void removeFragment(BaseFragment fragment) {
        FragmentStack.remove(fragment);
    }

    /**
     * 获取当前Fragment（堆栈中最后一个压入的）
     */
    public BaseFragment currentFragment() {
        BaseFragment fragment = FragmentStack.lastElement();
        return fragment;
    }
}
