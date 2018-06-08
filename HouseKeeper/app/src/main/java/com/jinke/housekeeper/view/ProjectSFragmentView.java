package com.jinke.housekeeper.view;

import com.jinke.housekeeper.base.BaseView;
import com.jinke.housekeeper.bean.ChangTotalInfo;
import com.jinke.housekeeper.bean.StatisticsInfo;
import com.jinke.housekeeper.bean.StatisticstInfo;

/**
 * Created by Administrator on 2017/9/7.
 */

public interface ProjectSFragmentView extends BaseView{
    void getChangTotalNext(ChangTotalInfo info);

    void getChangTotalError(String code, String msg);

    void getStatisticsNext(StatisticsInfo info);

    void getStatisticsError(String code, String msg);

    void getStatisticstNext(StatisticstInfo info);

    void getStatisticstError(String code, String msg);
}
