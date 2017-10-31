package jzg.studentmanagerdemo.api;

import java.util.List;
import java.util.Map;


import io.reactivex.Observable;
import jzg.studentmanagerdemo.ResponseJson;
import jzg.studentmanagerdemo.bean.Book;
import jzg.studentmanagerdemo.bean.UserInfo;
import retrofit2.http.Body;
import retrofit2.http.Field;
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
    @GET("/StudentManagerServer/login")
    Observable<ResponseJson<UserInfo>> login(@QueryMap Map<String, String> params);

    //添加图书资料
    @POST("/StudentManagerServer/AddBookServlet")
    Observable<ResponseJson<List<Book>>> addBook(@Body Book book);

    //删除图书资料
    @FormUrlEncoded
    @POST("/StudentManagerServer/DeleteBookServlet")
    Observable<ResponseJson<List<Book>>> deleteBook(@Field("bookId") int bookId);
}
