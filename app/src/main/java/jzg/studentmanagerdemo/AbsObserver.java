package jzg.studentmanagerdemo;

import android.text.TextUtils;


import java.net.SocketTimeoutException;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;


/**
 * @author: qiwx
 * email: qiwx@jingzhengu.com
 * @time: 2017/9/7 11:10
 * @desc:
 */

public abstract class AbsObserver<T> extends DisposableObserver<ResponseJson<T>> {
    private IBaseView view;
    private boolean showLoading = false;
    private String loadingText;

    public AbsObserver(IBaseView iBaseView) {
        view = iBaseView;
    }

    public AbsObserver(IBaseView iBaseView, boolean showLoading) {
        this(iBaseView);
        this.showLoading = showLoading;
    }

    public AbsObserver(IBaseView iBaseView, boolean showLoading, String loadingText) {
        this(iBaseView, showLoading);
        this.loadingText = loadingText;
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (view == null)
            return;

    }

    @Override
    public void onNext(@NonNull ResponseJson<T> t) {
        if (view != null) {

            if (t.getStatus() == 200) {
                onSuccess(t.getMemberValue());

            } else {
                if (view != null)
                    view.showError(t.getMsg());
                onError(t.getMsg());
            }

        }

    }

    @Override
    public void onError(@NonNull Throwable e) {
        if (view == null) {
            return;
        }

        String error = "";
        if (e instanceof SocketTimeoutException) {
            error = "请求超时";
        }
        error = e.getMessage();
        view.showError(error);//
        onError(error);
    }

    //请求成功
    public abstract void onSuccess(T response);

    //请求失败
    public abstract void onError(String error);
    //取消请求

    public void handleDisposableObserver() {
        super.dispose();
    }


    @Override
    public void onComplete() {
        if (view == null)
            return;
    }
}
