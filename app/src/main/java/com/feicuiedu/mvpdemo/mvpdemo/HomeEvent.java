package com.feicuiedu.mvpdemo.mvpdemo;

import java.util.List;

/**
 * 作者：yuanchao on 2016/10/14 0014 10:24
 * 邮箱：yuanchao@feicuiedu.com
 */
public class HomeEvent {
    public final List<String> datas;

    public HomeEvent(){
        datas = null;
    }

    public HomeEvent(List<String> datas){
        this.datas = datas;
    }
}