package com.jinke.housekeeper.housemanger.http;

import com.jinke.housekeeper.housemanger.bean.IdVerificationBean;
import com.jinke.housekeeper.housemanger.bean.SessionBean;
import com.jinke.housekeeper.housemanger.bean.VerificationDetailsBean;

import java.util.Map;

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

    //获取OpenID
    @FormUrlEncoded
    @POST("house/getTokenAndUser")
    Observable<HousingResult<SessionBean>> getToken(@FieldMap Map<String, String> map, @Header("signature") String sign);

    @FormUrlEncoded
    @POST("houseKeeper/getCheckList")
    Observable<HousingResult<IdVerificationBean>> getCheckList(@FieldMap Map<String, String> map, @Header("signature") String sign);

    @FormUrlEncoded
    @POST("houseKeeper/queryOwnerCheck")
    Observable<HousingResult<VerificationDetailsBean>> getQueryOwnerCheck(@FieldMap Map<String, String> map, @Header("signature") String sign);

    @FormUrlEncoded
    @POST("houseKeeper/checkOwner")
    Observable<HousingResult<VerificationDetailsBean>> getCheckOwner(@FieldMap Map<String, String> map, @Header("signature") String sign);


}
