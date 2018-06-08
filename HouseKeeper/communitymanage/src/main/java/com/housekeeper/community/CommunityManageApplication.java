package com.housekeeper.community;

import android.app.Application;

/**
 * Created by root on 18-5-22.
 */

public class CommunityManageApplication extends Application {

    public static CommunityManageApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
