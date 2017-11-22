package itor.topnetwork.com.dxditor.model;

import java.util.ArrayList;
import java.util.List;

import itor.topnetwork.com.dxditor.bean.GjxxBean;
import itor.topnetwork.com.dxditor.bean.SbxxBean;
import itor.topnetwork.com.dxditor.utils.ValueCallBack;

/**
 * Created by D.Han on 2017/11/15.
 */

public class MainpageModel implements IMainpageModel{

    @Override
    public void getTestData(ValueCallBack<List<SbxxBean>> callBack) {
        List<SbxxBean> list = new ArrayList<>();
        list.add(new SbxxBean(3));
        list.add(new SbxxBean(57));
        list.add(new SbxxBean(31));

        callBack.onSuccess(list);
    }

    @Override
    public void getGjxxData(ValueCallBack<List<GjxxBean>> callBack) {
        List<GjxxBean> list = new ArrayList<>();
        list.add(new GjxxBean("未处理",10));
        list.add(new GjxxBean("已处理",20));
        list.add(new GjxxBean("处理中",30));

        callBack.onSuccess(list);
    }

    @Override
    public void getXtpfData(ValueCallBack<List<GjxxBean>> callBack) {
        List<GjxxBean> list = new ArrayList<>();
        list.add(new GjxxBean("在线率占比",10));
        list.add(new GjxxBean("告警率占比",20));
        list.add(new GjxxBean("预警率占比",30));
        list.add(new GjxxBean("突增率占比",40));

        callBack.onSuccess(list);
    }
}
