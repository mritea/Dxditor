package itor.topnetwork.com.dxditor.model;

import java.util.ArrayList;
import java.util.List;

import itor.topnetwork.com.dxditor.bean.Gjlb;
import itor.topnetwork.com.dxditor.bean.GjxxBean;
import itor.topnetwork.com.dxditor.bean.SbxxBean;
import itor.topnetwork.com.dxditor.utils.ValueCallBack;

/**
 * Created by D.Han on 2017/11/15.
 */

public interface IMainpageModel {
    //设备信息
    void getTestData(ValueCallBack<List<SbxxBean>> callBack);
    //告警信息
    void getGjxxData(ValueCallBack<String> callBack);
    //全局信息
    void getQjxxData(ValueCallBack<String> callBack);
    //系统评分
    void getXtpfData(ValueCallBack<List<GjxxBean>> callBack);
    //告警列表
    ArrayList<Gjlb> getgjlblist();
    //告警列表
    void getgjlbData(ValueCallBack<ArrayList<Gjlb>> callBack);
}
