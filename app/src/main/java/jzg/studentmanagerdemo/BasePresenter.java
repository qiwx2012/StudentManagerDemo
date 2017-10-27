package jzg.studentmanagerdemo;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: qiwx
 * email: qiwx@jingzhengu.com
 * @time: 2017/7/10 10:23
 * @desc: p层基类
 */

public abstract class BasePresenter<T> {
    List<AbsObserver> observersQueue = new ArrayList<>();
    protected String TAG = getClass().getName();
    public WeakReference<T> baseView;//防止内存泄漏

    public BasePresenter(T from) {
        if (from == null) return;
        this.baseView = new WeakReference<>(from);
    }

    public void attachView(T view) {
        if (view == null) return;
        baseView = new WeakReference<>(view);
    }

    public void detachView() {
        cancelQueue();
        if (baseView != null) {
            baseView.clear();
            baseView = null;
        }
    }

    //添加消息请求队列
    public void addQueue(AbsObserver queue) {
        observersQueue.add(queue);
    }

    //删除消息队列
    public void cancelQueue() {
        for (AbsObserver call : observersQueue) {
            if (call != null)
                call.dispose();

        }
    }

    public T getView() {
        if (baseView == null) {
            return null;
        }
        return baseView.get();
    }



    //添加监听事件
    public void addObservable(@NonNull Observable<T> observable, @NonNull AbsObserver absObserver) {
        //将监听者对象放到list中，页面销毁时将关闭接收流管道，不再接收上游数据
        addQueue(absObserver);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(absObserver);

    }
    //创建业务处理对象
    public abstract void initObserver(Observable observable);

    protected boolean checkNullView() {
        return getView() == null;
    }

}
