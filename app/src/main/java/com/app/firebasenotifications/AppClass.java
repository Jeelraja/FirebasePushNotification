package com.app.firebasenotifications;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

public class AppClass extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static final String TAG = AppClass.class
            .getSimpleName();

    private static AppClass mInstance;

    private Activity mCurrentActivity = null;


    public static synchronized AppClass getInstance() {
        return mInstance;
    }

    public Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    public void setCurrentActivity(Activity mCurrentActivity) {
        this.mCurrentActivity = mCurrentActivity;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //MultiDex.install(this);
        mInstance = this;

    }
}