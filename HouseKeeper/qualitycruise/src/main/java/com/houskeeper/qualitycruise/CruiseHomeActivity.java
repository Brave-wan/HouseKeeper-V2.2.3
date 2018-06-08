package com.houskeeper.qualitycruise;

import www.jinke.com.library.base.BaseActivity;
import www.jinke.com.library.base.BasePresenter;

/**
 * Created by root on 18-5-22.
 */

public class CruiseHomeActivity  extends BaseActivity {
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return 0;
    }

    @Override
    protected void initView() {
        QualityCruiseApplication application=new QualityCruiseApplication();
    }
}
