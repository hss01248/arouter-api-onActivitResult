package com.alibaba.android.arouter.facade.callback;

import android.content.Intent;


import com.alibaba.android.arouter.facade.Postcard;

/**
 * time:2020/4/29
 * author:hss
 * desription:
 */
public interface NavigationCallback {

    /**
     * Callback when find the destination.
     *
     * @param postcard meta
     */
    void onFound(Postcard postcard);

    /**
     * Callback after lose your way.
     *
     * @param postcard meta
     */
    void onLost(Postcard postcard);

    /**
     * Callback after navigation.
     *
     * @param postcard meta
     */
    void onArrival(Postcard postcard);

    /**
     * Callback on interrupt.
     *
     * @param postcard meta
     */
    void onInterrupt(Postcard postcard);

    void onActivityResult(int requestCode, int resultCode,  Intent data);
}
