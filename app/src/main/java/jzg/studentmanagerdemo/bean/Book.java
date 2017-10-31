package jzg.studentmanagerdemo.bean;

import java.io.Serializable;

/**
 * @author: qiwx
 * email: qiwx@jingzhengu.com
 * @time: 2017/10/30 15:50
 * @desc:
 */

public class Book implements Serializable {
    int bookId;
    String bookName;
    double bookPrice;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }
}
