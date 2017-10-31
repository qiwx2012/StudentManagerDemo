package jzg.studentmanagerdemo.presenter.view;


import jzg.studentmanagerdemo.IBaseView;
import jzg.studentmanagerdemo.bean.UserInfo;

/**
 * @author: qiwx
 * email: qiwx@jingzhengu.com
 * @time: 2017/10/27 16:08
 * @desc:
 */

public interface ILoginView extends IBaseView {

     void LoginSuccess(UserInfo response);
}
