package com.jinke.housekeeper.knowledge;

import android.app.Application;

/**
 * Created by root on 18-5-17.
 */

public class KnowledgeApplication extends Application {
    public static KnowledgeApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
