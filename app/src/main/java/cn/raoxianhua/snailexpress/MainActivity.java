package cn.raoxianhua.snailexpress;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private DrawerLayout mDrawerLayout;

    private List<Express> expressList = new ArrayList();

    private ExpressAdapter adapter;

    private SwipeRefreshLayout swipeRefresh;

    private SearchView searchView;

    private Spinner spinner;

    private String shippercode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        final NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        spinner = (Spinner) findViewById(R.id.company);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view,
//                                       int pos, long id) {
//
//                String[] languages = getResources().getStringArray(R.array.company);
//                shippercode=languages[pos];
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                // Another interface callback
//            }
//        });
        // 建立数据源
        String[] mItems = getResources().getStringArray(R.array.company);
// 建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//绑定 Adapter到控件
        spinner .setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] languages = getResources().getStringArray(R.array.company);
                shippercode=languages[pos];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
//        ActionBar actionBar = getSupportActionBar();
//        if(actionBar != null){
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
//        }
        navView.setCheckedItem(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.login:
                        Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent1);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.register:
                        Intent intent2 = new Intent(MainActivity.this, RegisterActivity.class);
                        startActivity(intent2);
                        mDrawerLayout.closeDrawers();
                        break;
                    default:
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);

        //设置搜索输入框的步骤

        //1.查找指定的MemuItem
        MenuItem menuItem = menu.findItem(R.id.search);

      /*  //2.设置SearchView v4 包方式
        View view = SearchViewCompat.newSearchView(this);
//        menuItem.setActionView(view);
        MenuItemCompat.setActionView(menuItem, view);*/

        //2.设置SearchView v7包方式
        View view = MenuItemCompat.getActionView(menuItem);
        if (view != null) {
            searchView = (SearchView) view;
            //4.设置SearchView 的查询回调接口
            searchView.setOnQueryTextListener(this);

            //在搜索输入框没有显示的时候 点击Action ,回调这个接口，并且显示输入框
//            searchView.setOnSearchClickListener();
            //当自动补全的内容被选中的时候回调接口
//            searchView.setOnSuggestionListener();

            //可以设置搜索的自动补全，或者实现搜索历史
//            searchView.setSuggestionsAdapter();

        }

        return true;
    }


    /**
     * 当用户在输入法中点击搜索按钮时,或者输入回车时,调用这个方法，发起实际的搜索功能
     * @param query
     * @return
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
//        Toast.makeText(MainActivity.this, "Submit" + query, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, ExpressActivity.class);
        intent.putExtra("ShipperCode",shippercode);
        intent.putExtra("eid",query);
        startActivity(intent);
        searchView.clearFocus();
        return true;
    }

    /**
     * 每一次输入字符，都会调用这个方法，实现搜索的联想功能
     * @param newText
     * @return
     */
    @Override
    public boolean onQueryTextChange(String newText) {
//        Toast.makeText(MainActivity.this, "" + newText, Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.menu:
                Toast.makeText(this, R.string.menu, Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
            default:
        }
        return  true;
    }
}