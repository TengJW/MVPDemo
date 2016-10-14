package com.feicuiedu.mvpdemo.mvpdemo;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Home页面相关业务
 * <p>
 * 作者：yuanchao on 2016/10/13 0013 14:36
 * 邮箱：yuanchao@feicuiedu.com
 */
public class HomeModel {

    private Thread mThread;

    public void asyncLoadData() {
        if (mThread != null) {
            mThread.interrupt();
            mThread = null;
        }
        mThread = new Thread(new Runnable() {
            @Override public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // TODO: 2016/10/13 0013 只是模拟数据加载
                // 错误情况
                if (System.currentTimeMillis() % 2 == 0) {
                    // 反馈M层数据
                    EventBus.getDefault().post(new HomeEvent());
                }
                // 正确情况
                else {
                    List<String> datas = new ArrayList<>();
                    for (int i = 0; i < 20; i++) {
                        datas.add("我是第 " + i + " 条数据");
                    }
                    // 反馈M层数据
                    EventBus.getDefault().post(new HomeEvent(datas));
                }
            }
        });
        mThread.start();
    }
}