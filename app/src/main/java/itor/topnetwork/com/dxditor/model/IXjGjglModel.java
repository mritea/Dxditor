package itor.topnetwork.com.dxditor.model;

import java.util.ArrayList;

import itor.topnetwork.com.dxditor.bean.Gjlb;
import itor.topnetwork.com.dxditor.utils.ValueCallBack;

/**
 * Created by D.Han on 2017/12/6.
 */

public interface IXjGjglModel {
    //告警列表
    ArrayList<Gjlb> getgjlblist();

    void getgjlbData(ValueCallBack<ArrayList<Gjlb>> callBack);
}
