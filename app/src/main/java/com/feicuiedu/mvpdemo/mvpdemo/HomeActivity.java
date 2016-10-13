package com.feicuiedu.mvpdemo.mvpdemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/***
 * Home页面相关视图实现
 * <p>
 * 视图一般是"消极的", 你想，视图的变化基本都由于数据的变化，或用户的操作而变化的
 * 视图一般是不会自己来调用的
 */
public class HomeActivity extends AppCompatActivity {

    // 展现数据的列表控件
    @BindView(R.id.listView) ListView listView;
    // 触发获取数据的按钮,默认显示,loading后将gone
    @BindView(R.id.btn_refresh) Button btnRefresh;
    // 数据获取中的loading控件,默认gone
    @BindView(R.id.prg_loading) ProgressBar prgLoading;

    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        // 设置ListView的适配器,我们使用的是arrayAdapter
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(arrayAdapter);
    }

    @OnClick(R.id.btn_refresh)
    public void refreshData() {
        new LoadTask().execute();
    }

    // 视图上的工作如下 (其实这种情况可以先做一个接口出来)
    public void showLoading() {
        prgLoading.setVisibility(View.VISIBLE);
        btnRefresh.setVisibility(View.GONE);
        // 什么时候应该来调用显示加载中呢？
    }

    public void hideLoading() {
        prgLoading.setVisibility(View.GONE);
        btnRefresh.setVisibility(View.VISIBLE);
    }

    public void refreshListView(List<String> datas) {
        arrayAdapter.clear();
        arrayAdapter.addAll(datas);
        arrayAdapter.notifyDataSetChanged();
    }

    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    // 在做业务数据处理的同时, 应该都要有视图的调用(视图要刷新页面)

    // 这里是业务(用例及视图业务)相关工作
    // Params：参数的类型(基本数据类型，引用数据类开)
    // Progress: 进度。。。
    // Result: 结果的类型....
    private class LoadTask extends AsyncTask<Void, Void, List<String>> {
        // doInBackground之前UI线程调用
        @Override protected void onPreExecute() {
            super.onPreExecute();
            showLoading();
        }

        // onPreExecute之后，后台线程调用的
        @Override protected List<String> doInBackground(Void... voids) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                showMessage("未知错误!");
            }
            List<String> datas = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                datas.add("我是第 " + i + " 条数据");
            }
            return datas;
        }

        // doInBackground之后UI线程调用
        @Override protected void onPostExecute(List<String> strings) {
            super.onPostExecute(strings);
            hideLoading();
            // 数据加载结束 - 成功: 应该通知视图刷新数据了
            refreshListView(strings);
        }
    }
}
