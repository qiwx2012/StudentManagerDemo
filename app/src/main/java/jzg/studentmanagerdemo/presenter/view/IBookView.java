package jzg.studentmanagerdemo.presenter.view;

import java.util.List;

import jzg.studentmanagerdemo.IBaseView;
import jzg.studentmanagerdemo.bean.Book;

/**
 * @author: qiwx
 * email: qiwx@jingzhengu.com
 * @time: 2017/10/30 16:01
 * @desc:
 */

public interface IBookView extends IBaseView {
    //操作成功
    void operateSuccess(List<Book> data);

    //操作失败
    void operateFail(List<Book> data);


}
