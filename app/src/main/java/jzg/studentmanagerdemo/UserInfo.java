package jzg.studentmanagerdemo;

import java.io.Serializable;

/**
 *@Author qiwx
 *@time  2017年10月27日 下午6:10:22
 *@Des
 **/
public class UserInfo implements Serializable{
	
	String userName;
	String passWord;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	

}
