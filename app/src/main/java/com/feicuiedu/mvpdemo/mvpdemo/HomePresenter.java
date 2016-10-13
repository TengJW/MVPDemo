package com.feicuiedu.mvpdemo.mvpdemo;

import android.support.annotation.UiThread;

import java.util.List;

/**
 * Home页面协调人, 实现业务层接口,持有视图层接口,实现两者间的"协调"
 * <p>
 * 作者：yuanchao on 2016/10/13 0013 14:46
 * 邮箱：yuanchao@feicuiedu.com
 */
public class HomePresenter implements HomeModel.Model {

    private HomeView mHomeView;

    public HomePresenter(HomeView homeView) {
        mHomeView = homeView;
    }

    @UiThread
    public void loadData() {
        mHomeView.showLoading();
        new HomeModel(this).asyncLoadData();
    }

    @UiThread
    @Override public void setData(List<String> datas) {
        mHomeView.hideLoading();
        if (datas == null) {
            mHomeView.showMessage("未知错识!");
            return;
        }
        mHomeView.refreshListView(datas);
    }
}