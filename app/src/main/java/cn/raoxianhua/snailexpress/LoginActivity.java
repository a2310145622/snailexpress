package cn.raoxianhua.snailexpress;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by raoxianhua on 2018/1/12.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        login =(Button)findViewById(R.id.login);
        register =(Button)findViewById(R.id.register);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = username.getText().toString();
                String pwd = password.getText().toString();
                sendRequestWithOkHttp();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址是电脑本机
                            .url("http://www.raoxianhua.cn/snail/doLogin?cid="+username.getText()+"&cpwd="+password.getText())
                            .build();
                    Response response = client.newCall(request).execute();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(response.body().byteStream()));
                    int a=Integer.parseInt(reader.readLine());
//                    String responseData = response.body().string();
//                    parseJSONWithGSON(responseData);
//                    parseJSONWithJSONObject(responseData);
//                    parseXMLWithSAX(responseData);
//                    parseXMLWithPull(responseData);
                    showResponse(a);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void parseJSONWithGSON(String jsonData) {
        Gson gson = new Gson();
        List<User> appList = gson.fromJson(jsonData, new TypeToken<List<User>>() {}.getType());
        for (User app : appList) {
            Log.d("MainActivity", "username is " + app.getUsername());
            Log.d("MainActivity", "password is " + app.getPassword());
        }
    }

    private void showResponse(final int response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 在这里进行UI操作，将结果显示到界面上
                System.out.println(response);

                if (response==1) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "登录失败！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
