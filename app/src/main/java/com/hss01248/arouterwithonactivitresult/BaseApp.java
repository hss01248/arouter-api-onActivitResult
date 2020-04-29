package com.hss01248.arouterwithonactivitresult;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;


/**
 * time:2020/4/29
 * author:hss
 * desription:
 */
public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (true) {           // These two lines must be written before init, otherwise these configurations will be invalid in the init process
            ARouter.openLog();     // Print log
            ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        }
        ARouter.init(this);
    }
}
