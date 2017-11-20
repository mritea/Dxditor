package itor.topnetwork.com.dxditor.model;

import java.util.List;

import itor.topnetwork.com.dxditor.bean.GjxxBean;
import itor.topnetwork.com.dxditor.bean.SbxxBean;
import itor.topnetwork.com.dxditor.utils.ValueCallBack;

/**
 * Created by D.Han on 2017/11/15.
 */

public interface IMainpageModel {
    void getTestData(ValueCallBack<List<SbxxBean>> callBack);
    void getGjxxData(ValueCallBack<List<GjxxBean>> callBack);
}
