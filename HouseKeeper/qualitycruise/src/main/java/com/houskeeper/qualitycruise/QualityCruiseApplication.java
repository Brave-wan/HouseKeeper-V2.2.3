package com.houskeeper.qualitycruise;

import android.app.Application;

/**
 * Created by root on 18-5-22.
 */

public class QualityCruiseApplication extends Application {
    public static QualityCruiseApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;

    }
}
