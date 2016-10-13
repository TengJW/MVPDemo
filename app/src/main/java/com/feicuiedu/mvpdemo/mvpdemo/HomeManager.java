package com.feicuiedu.mvpdemo.mvpdemo;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Home页面相关业务
 *
 * </p>
 * <li/>1 做了业务处理  : doInBackground里面的模拟（3秒）获取加载数据
 * <li/>2 做了业务和视图的协调处理: 调用了视图上的一些“方法”（进行了视图的刷新）
 * </p>
 * 他太累了,他的工作太多了，在工作太多时，它就容易出错 -- 不方便修改,不明确工作职责,容易出BUG，出了BUG还不容易找
 * </p>
 * 我们希望它的工作再拆分一下,拆分成为,业务和协调,也就是Model和Presenter,View就是HomeView也就是Activity或Fragmnet,View,ViewGroup)
 * </p>
 * 作者：yuanchao on 2016/10/13 0013 11:13
 * 邮箱：yuanchao@feicuiedu.com
 */ 
public class HomeManager {

    // 视图接口对象
    private HomeView mHomeView;

    public HomeManager(HomeView homeView) {
        mHomeView = homeView;
    }

    public void loadData() {
        new LoadTask().execute();
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
            mHomeView.showLoading();
        }

        // onPreExecute之后，后台线程调用的
        @Override protected List<String> doInBackground(Void... voids) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                mHomeView.showMessage("未知错误!");
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
            mHomeView.hideLoading();
            // 数据加载结束 - 成功: 应该通知视图刷新数据了
            mHomeView.refreshListView(strings);
        }
    }

    // 在这个AsyncTask里面做了业务的实现，还做了视图上的一些调用
    // 做了业务处理  : doInBackground里面的模拟（3秒）获取加载数据
    // 做了业务和视图的协调处理: 调用了视图上的一些“方法”（进行了视图的刷新）

    // Activity: 是视图V ,对不对？
    // AsyncTask： 是M+P

    // 问题就是：我们还没有将它们拆分出来

    // 1. 将AsyncTask的代码写出去(叫HomeManager)
}
