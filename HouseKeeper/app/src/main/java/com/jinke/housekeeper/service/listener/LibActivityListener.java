package com.jinke.housekeeper.service.listener;

import com.jinke.housekeeper.knowledge.bean.KnowledgeInfo;

/**
 * Created by Administrator on 2017/9/11.
 */

public interface LibActivityListener {
    void getLoreonNext(KnowledgeInfo info);

    void getLoreonError(String code, String msg);
}
