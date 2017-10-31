package jzg.studentmanagerdemo.presenter;

import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import jzg.studentmanagerdemo.AbsObserver;
import jzg.studentmanagerdemo.BasePresenter;
import jzg.studentmanagerdemo.api.ApiManager;
import jzg.studentmanagerdemo.bean.Book;
import jzg.studentmanagerdemo.bean.UserInfo;
import jzg.studentmanagerdemo.presenter.view.IBookView;

/**
 * @author: qiwx
 * email: qiwx@jingzhengu.com
 * @time: 2017/10/30 16:00
 * @desc:
 */

public class BookPresenter extends BasePresenter<IBookView> {
    public BookPresenter(IBookView from) {
        super(from);
    }

    //添加图书
    public void addBook(Book book) {
        Observable observable = ApiManager.getApiManager()
                .getCommonApi().addBook(book);
        //创建业务处理对象
        AbsObserver<List<Book>> absObserver = new AbsObserver<List<Book>>(getView(), true) {
            @Override
            public void onSuccess(List<Book> response) {
                if (getView() != null) {
                    Log.e("dd", "请求成功");
                    getView().operateSuccess(response);
                }
            }

            @Override
            public void onError(String error) {
                Log.e("dd", error);
                getView().showError(error);


            }
        };
        addObservable(observable, absObserver);

    }

    //删除图书 填写图书Id就可以
    public void delete(int bookId) {
        Observable observable = ApiManager.getApiManager()
                .getCommonApi().deleteBook(bookId);
        //创建业务处理对象
        AbsObserver<List<Book>> absObserver = new AbsObserver<List<Book>>(getView(), true) {
            @Override
            public void onSuccess(List<Book> response) {
                if (getView() != null) {
                    Log.e("dd", "请求成功");
                    getView().operateSuccess(response);
                }
            }

            @Override
            public void onError(String error) {
                Log.e("dd", error);
                getView().showError(error);


            }
        };
        addObservable(observable, absObserver);

    }

    @Override
    public void initObserver(Observable observable) {

    }
}
