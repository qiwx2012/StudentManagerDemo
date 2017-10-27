package jzg.studentmanagerdemo.api;

import java.util.Map;

import io.reactivex.Observable;
import jzg.studentmanagerdemo.ResponseJson;
import jzg.studentmanagerdemo.UserInfo;
import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * @author: qiwx
 * email: qiwx@jingzhengu.com
 * @time: 2017/7/10 14:38
 * @desc: 基础服务Api
 */

public interface CommonApi {

    //获取车的车系信息
    @GET("/Struct1.x_Intro1/login.do")
    Observable<ResponseJson<UserInfo>> login(@QueryMap Map<String, String> params);

}
