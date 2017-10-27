package jzg.studentmanagerdemo.api;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: qiwx
 * email: qiwx@jingzhengu.com
 * @time: 2017/7/10 14:35
 * @desc: 接口管理类
 */

public class ApiManager {

    public static final String COMMON_BASE_URL = "http://192.168.40.1:8080";//公共接口域名
    private static final String TAG = "ApiManager";
    private static final int DEFAULT_TIMEOUT = 60;
    public static OkHttpClient client;
    /*初始化*/
    private static ApiManager apiManager;
    private static CommonApi commonApi;

    public static ApiManager getApiManager() {
        if (apiManager == null) {
            synchronized (ApiManager.class) {
                apiManager = new ApiManager();
            }
        }
        return apiManager;
    }

    public static OkHttpClient getClient() {
        if (client == null) {
            create();
        }
        return client;
    }



    private static void create() {

        //网络日志拦截器
        Interceptor LogInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                if (chain != null) {
                    Request request = chain.request(); //request还有其他方法没有用
                    if (request != null) {
                        long t1 = System.nanoTime();
//                        Log.e("intercept",Thread.currentThread().getName());
//                        Log.e("request", request.headers().toString());
                        Response response = chain.proceed(request);
                        long t2 = System.nanoTime();
                        if (response != null) {
                            String responseString = new String(response.body().bytes());
                            Log.e(TAG, String.format("请求路径: %s", request.url())
                                    + "\n" + String.format("服务器响应时间 %.1fms ,返回值: %s", (t2 - t1) / 1e6d, responseString));
                            return response.newBuilder().body(ResponseBody.create(response.body().contentType(), responseString)).build();
                        }
                    }
                }
                return null;

            }
        };

        client = new OkHttpClient.Builder()
                .addInterceptor(LogInterceptor)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();


    }



    //公共接口
    public CommonApi getCommonApi() {
        synchronized (CommonApi.class) {
            Retrofit retrofit = getRetrofit(COMMON_BASE_URL);
            commonApi = retrofit.create(CommonApi.class);
        }
        return commonApi;
    }



    //获取retrofit对象
    private Retrofit getRetrofit(String baseUrl) {
        OkHttpClient okHttpClient = getClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                //.addCallAdapterFactory(smartFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }


}
