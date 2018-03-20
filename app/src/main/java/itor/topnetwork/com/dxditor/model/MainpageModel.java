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

public class MainpageModel implements IMainpageModel{
    private ArrayList<Gjlb> listData;

    public MainpageModel() {
        this.listData = new ArrayList<Gjlb>();
    }

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

    @Override
    public ArrayList<Gjlb> getgjlblist() {
        return listData;
    }

    @Override
    public void getgjlbData(ValueCallBack<ArrayList<Gjlb>> callBack) {
        ArrayList<Gjlb> list = new ArrayList<>();
        list.add(new Gjlb("京沪","K101+345","线夹","4601400000000024","20.5","2017-11-24 09:23:35"));
        list.add(new Gjlb("京广","K564+345","线夹","4601400000000025","45.5","2017-11-24 09:23:35"));
        list.add(new Gjlb("京哈","K43+345","线夹","4601400000000026","36.5","2017-11-24 09:23:35"));
        list.add(new Gjlb("京九","K458+345","线夹","4601400000000027","31.5","2017-11-24 09:23:35"));
        list.add(new Gjlb("京九","K458+345","线夹","4601400000000027","31.5","2017-11-24 09:23:35"));
        list.add(new Gjlb("京九","K458+345","线夹","4601400000000027","31.5","2017-11-24 09:23:35"));
        list.add(new Gjlb("京九","K458+345","线夹","4601400000000027","31.5","2017-11-24 09:23:35"));
        list.add(new Gjlb("京九","K458+345","线夹","4601400000000027","31.5","2017-11-24 09:23:35"));
        list.add(new Gjlb("京九","K458+345","线夹","4601400000000027","31.5","2017-11-24 09:23:35"));
        list.add(new Gjlb("京九","K458+345","线夹","4601400000000027","31.5","2017-11-24 09:23:35"));
        list.add(new Gjlb("京九","K458+345","线夹","4601400000000027","31.5","2017-11-24 09:23:35"));
        list.add(new Gjlb("京九","K458+345","线夹","4601400000000027","31.5","2017-11-24 09:23:35"));
        list.add(new Gjlb("京九","K458+345","线夹","4601400000000027","31.5","2017-11-24 09:23:35"));
        list.add(new Gjlb("京九","K458+345","线夹","4601400000000027","31.5","2017-11-24 09:23:35"));
        list.add(new Gjlb("京九","K458+345","线夹","4601400000000027","31.5","2017-11-24 09:23:35"));

        callBack.onSuccess(list);
    }
}
