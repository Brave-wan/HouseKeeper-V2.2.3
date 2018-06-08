package com.jinke.housekeeper.knowledge.http;

import com.jinke.housekeeper.knowledge.bean.KnowledgeInfo;
import com.jinke.housekeeper.knowledge.bean.LibAllInfo;
import com.jinke.housekeeper.knowledge.bean.LibDetailsInfo;

import java.util.Map;
import java.util.SortedMap;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 作者：Rance on 2016/11/18 15:19
 * 邮箱：rance935@163.com
 */
public interface ApiStores {

    @FormUrlEncoded
    @POST("Appinterface/getLore")
    Observable<HttpResult<KnowledgeInfo>> getLore(@FieldMap SortedMap<String, String> map, @Header("signature") String sign);


    @FormUrlEncoded
    @POST("Appinterface/ScenePage")
    Observable<HttpResult<LibAllInfo>> getScenePageData(@FieldMap Map<String, String> map, @Header("signature") String sign);

    @FormUrlEncoded
    @POST("Appinterface/getKeyId")
    Observable<HttpResult<LibDetailsInfo>> getKeyId(@FieldMap Map<String, String> map, @Header("signature") String sign);
}
