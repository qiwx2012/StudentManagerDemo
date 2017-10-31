package jzg.studentmanagerdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jzg.studentmanagerdemo.bean.Book;
import jzg.studentmanagerdemo.presenter.BookPresenter;
import jzg.studentmanagerdemo.presenter.view.IBookView;

/**
 * @author: qiwx
 * email: qiwx@jingzhengu.com
 * @time: 2017/10/30 15:33
 * @desc: 图书管理系统
 */

public class BookManagerAct extends AppCompatActivity implements IBookView {

    @BindView(R.id.edtBookId)
    EditText edtBookId;
    @BindView(R.id.edtBookName)
    EditText edtBookName;
    @BindView(R.id.edtBookPrice)
    EditText edtBookPrice;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    @BindView(R.id.btnDelete)
    Button btnDelete;
    @BindView(R.id.list)
    RecyclerView list;
    List<Book> data = new ArrayList<>();
    CommonAdapter<Book> adapter;
    BookPresenter bookPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_manager_layout);
        ButterKnife.bind(this);
        list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommonAdapter<Book>(BookManagerAct.this, R.layout.book_list_item, data) {
            @Override
            public void convert(ViewHolder holder, Book book) {
                holder.setText(R.id.txtBookId, book.getBookId() + "");
                holder.setText(R.id.txtBookName, book.getBookName());
                holder.setText(R.id.txtBookPrice, book.getBookPrice() + "元");

            }
        };
        bookPresenter = new BookPresenter(this);
        list.setAdapter(adapter);


    }

    private void add() {
        Book book = new Book();
        book.setBookId(Integer.valueOf(edtBookId.getText().toString()));
        book.setBookName(edtBookName.getText().toString());
        book.setBookPrice(Double.valueOf(edtBookPrice.getText().toString()));
        bookPresenter.addBook(book);
    }

    private void delete() {
        bookPresenter.delete(Integer.valueOf(edtBookId.getText().toString()));
    }

    @OnClick({R.id.btnAdd, R.id.btnDelete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                add();
                break;
            case R.id.btnDelete:
                delete();
                break;
        }
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void operateSuccess(List<Book> data) {
        this.data.clear();
        this.data.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void operateFail(List<Book> data) {

    }
}


