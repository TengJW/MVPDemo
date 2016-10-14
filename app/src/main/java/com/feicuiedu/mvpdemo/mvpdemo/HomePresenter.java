package com.feicuiedu.mvpdemo.mvpdemo;

import android.support.annotation.UiThread;

import com.feicuiedu.mvpdemo.mvpdemo.basemvp.MvpPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Home页面协调人, 实现业务层接口,持有视图层接口,实现两者间的"协调"
 * <p>
 * 作者：yuanchao on 2016/10/13 0013 14:46
 * 邮箱：yuanchao@feicuiedu.com
 */
public class HomePresenter extends MvpPresenter<HomeView>{

    @UiThread
    public void loadData() {
        getView().showLoading();
        new HomeModel().asyncLoadData();
    }

    @Subscribe(threadMode=ThreadMode.MAIN)
    public void onEvent(HomeEvent homeEvent){
        getView().hideLoading();
        if (homeEvent.datas == null) {
            getView().showMessage("未知错识!数据获取失败!");
            return;
        }
        getView().refreshListView(homeEvent.datas);
    }

    // 一个HomeView接口(视图接口)空的实现
    protected final HomeView getNullObject(){
        HomeView homeView = new HomeView() {
            @Override public void showLoading() {}

            @Override public void hideLoading() {}

            @Override public void refreshListView(List<String> datas) {}

            @Override public void showMessage(String msg) {}
        };
        return homeView;
    }
}