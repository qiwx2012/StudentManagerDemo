package jzg.studentmanagerdemo;

import com.google.gson.annotations.SerializedName;

/**
 * @author: qiwx
 * email: qiwx@jingzhengu.com
 * @time: 2017/7/11 16:14
 * @desc: 接口返回参数结构类
 */

public class ResponseJson<T> {
    @SerializedName("Data")
    private T MemberValue;
    @SerializedName("Status")
    private int status;
    @SerializedName("Message")
    private String msg;

    public T getMemberValue() {
        return MemberValue;
    }

    public void setMemberValue(T MemberValue) {
        this.MemberValue = MemberValue;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
