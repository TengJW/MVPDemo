package com.feicuiedu.mvpdemo.mvpdemo;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Home页面相关业务
 * <p>
 * 作者：yuanchao on 2016/10/13 0013 14:36
 * 邮箱：yuanchao@feicuiedu.com
 */
public class HomeModel {

    /** 业务层接口*/
    public interface Model {
        void setData(List<String> datas);
    }

    private Thread mThread;
    private Model mModel;
    private Handler mHandler;

    public HomeModel(Model model) {
        mModel = model;
        mHandler = new Handler(Looper.getMainLooper()) {
            @Override public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    List<String> datas = (List<String>) msg.obj;
                    mModel.setData(datas);
                }
            }
        };
    }

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
                List<String> datas = new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    datas.add("我是第 " + i + " 条数据");
                }
                // 反馈M层数据
                Message message = Message.obtain();
                message.what = 1;
                message.obj = datas;
                mHandler.sendMessage(message);
            }
        });
        mThread.start();
    }
}