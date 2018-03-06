package itor.topnetwork.com.dxditor.model.xj;

import java.util.ArrayList;

import itor.topnetwork.com.dxditor.bean.Gjlb;
import itor.topnetwork.com.dxditor.utils.ValueCallBack;

/**
 * 线夹告警管理Model
 * Created by D.Han on 2017/12/6.
 */

public class XjGjglModel implements IXjGjglModel {
    private ArrayList<Gjlb> listData;

    public XjGjglModel() {
        this.listData = new ArrayList<Gjlb>();
    }

    @Override
    public ArrayList<Gjlb> getgjlblist() {
        return listData;
    }

    @Override
    public void getgjlbData(ValueCallBack<ArrayList<Gjlb>> callBack) {
        ArrayList<Gjlb> list = new ArrayList<>();
        list.add(new Gjlb("京沪", "K101+345", "线夹", "4601400000000024", "20.5", "2017-11-24 09:23:35"));
        list.add(new Gjlb("京广", "K564+345", "线夹", "4601400000000025", "45.5", "2017-11-24 09:23:35"));
        list.add(new Gjlb("京哈", "K43+345", "线夹", "4601400000000026", "36.5", "2017-11-24 09:23:35"));
        list.add(new Gjlb("京九", "K458+345", "线夹", "4601400000000027", "31.5", "2017-11-24 09:23:35"));
        list.add(new Gjlb("京沪", "K101+345", "线夹", "4601400000000024", "20.5", "2017-11-24 09:23:35"));
        list.add(new Gjlb("京广", "K564+345", "线夹", "4601400000000025", "45.5", "2017-11-24 09:23:35"));
        list.add(new Gjlb("京哈", "K43+345", "线夹", "4601400000000026", "36.5", "2017-11-24 09:23:35"));
        list.add(new Gjlb("京九", "K458+345", "线夹", "4601400000000027", "31.5", "2017-11-24 09:23:35"));
        list.add(new Gjlb("京沪", "K101+345", "线夹", "4601400000000024", "20.5", "2017-11-24 09:23:35"));
        list.add(new Gjlb("京广", "K564+345", "线夹", "4601400000000025", "45.5", "2017-11-24 09:23:35"));
        list.add(new Gjlb("京哈", "K43+345", "线夹", "4601400000000026", "36.5", "2017-11-24 09:23:35"));
        list.add(new Gjlb("京九", "K458+345", "线夹", "4601400000000027", "31.5", "2017-11-24 09:23:35"));

        callBack.onSuccess(list);
    }
}
