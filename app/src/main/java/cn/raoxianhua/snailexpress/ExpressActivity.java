package cn.raoxianhua.snailexpress;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by raoxianhua on 2018/1/12.
 */

public class ExpressActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private List<Express> expressList = new ArrayList();
    private ExpressAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;
    String ShipperCode;
    String eid;
    TextView textView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express);
        Intent intent = getIntent();
        ShipperCode = intent.getStringExtra("ShipperCode");
        eid = intent.getStringExtra("eid");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        final NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        swipeRefresh = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initExpresses();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
        }
        return  true;
    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址是电脑本机
                            .url("http://www.raoxianhua.cn/snail/doSearch?ShipperCode=&eid="+eid)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithGSON(responseData);
//                    parseJSONWithJSONObject(responseData);
//                    parseXMLWithSAX(responseData);
//                    parseXMLWithPull(responseData);
                    showResponse(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initExpresses(){
//        expressList.clear();
//        for (int i = 0; i < 50; i++){
//            Random random =new Random();
//            int index = random.nextInt(expresses.length);
//            expressList.add(expresses[index]);
//        }
        sendRequestWithOkHttp();
    }

    private void parseJSONWithGSON(String jsonData) {
        Gson gson = new Gson();
        expressList = gson.fromJson(jsonData, new TypeToken<List<Express>>() {}.getType());
        System.out.println(jsonData.toString());
        for (Express app : expressList) {
            Log.d("MainActivity", "id is " + app.getExpressTime());
            Log.d("MainActivity", "name is " + app.getExpressDetails());
        }
    }

    private void refreshExpresses(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        expressList.clear();
                        sendRequestWithOkHttp();
//                        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
//                        LinearLayoutManager layoutManager=new LinearLayoutManager(ExpressActivity.this);
////        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
//                        recyclerView.setLayoutManager(layoutManager);
//                        adapter = new ExpressAdapter(expressList);
//                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();

    }

    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 在这里进行UI操作，将结果显示到界面上

                RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
                LinearLayoutManager layoutManager=new LinearLayoutManager(ExpressActivity.this);
//        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
                recyclerView.setLayoutManager(layoutManager);
                adapter = new ExpressAdapter(expressList);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}
