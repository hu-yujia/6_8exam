package com.bw.a6_8exam;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openDebug();
        ARouter.openLog();
        ARouter.init(this);
    }
}
