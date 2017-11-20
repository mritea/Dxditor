package itor.topnetwork.com.dxditor.model;

import java.util.ArrayList;
import java.util.List;

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
}
