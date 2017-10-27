package jzg.studentmanagerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import jzg.studentmanagerdemo.api.ApiManager;

public class MainActivity extends AppCompatActivity implements ILoginView {
    TextView txtInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtInfo = (TextView) findViewById(R.id.txtInfo);
        findViewById(R.id.btnRequest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> map = new HashMap<>();
                map.put("username", "qiwx");
                map.put("password", "123456");
                LoginPresenter lp = new LoginPresenter(MainActivity.this);
                lp.login(map);
            }
        });
    }

    @Override
    public void LoginSuccess(UserInfo response) {
        Toast.makeText(MainActivity.this, "返回结果：" + response.getUserName() + "密码：" + response.getPassWord(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showError(String error) {

    }
}
