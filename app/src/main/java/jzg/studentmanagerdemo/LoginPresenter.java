package jzg.studentmanagerdemo;

import android.util.Log;
import android.widget.Toast;

import java.util.Map;

import io.reactivex.Observable;
import jzg.studentmanagerdemo.api.ApiManager;
import retrofit2.Response;

/**
 * @author: qiwx
 * email: qiwx@jingzhengu.com
 * @time: 2017/10/27 16:08
 * @desc:
 */

public class LoginPresenter extends BasePresenter<ILoginView> {
    public LoginPresenter(ILoginView from) {
        super(from);
    }

    //登录
    public void login(Map<String, String> params) {
        Observable observable = ApiManager.getApiManager()
                .getCommonApi()
                .login(params);

        //创建业务处理对象
        AbsObserver<UserInfo> absObserver = new AbsObserver<UserInfo>(getView(), true) {
            @Override
            public void onSuccess(UserInfo response) {
                if (getView() != null) {
                    Log.e("dd", "请求成功");
                    getView().LoginSuccess(response);
                }
            }

            @Override
            public void onError(String error) {
                Log.e("dd", "请求失败");


            }
        };
        addObservable(observable, absObserver);
    }

    @Override
    public void initObserver(Observable observable) {

    }
}
