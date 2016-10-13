package com.feicuiedu.mvpdemo.mvpdemo;

import java.util.List;

/**
 * 作者：yuanchao on 2016/10/13 0013 11:23
 * 邮箱：yuanchao@feicuiedu.com
 */

public interface HomeView {

    /** 当开始加载数据时，将来调用的视图方法*/
    void showLoading();

    /** 当结束加载数据后，将来调用的视图方法*/
    void hideLoading();

    /** 当结束加载数据后，将来调用的视图方法，用来刷新列表数据*/
    void refreshListView(List<String> datas);

    /** 当业务执行过程中,出现错误,将来调用的视图方法*/
    void showMessage(String msg);
}
