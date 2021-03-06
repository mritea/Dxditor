package itor.topnetwork.com.dxditor.view;

import java.util.ArrayList;
import java.util.List;

import itor.topnetwork.com.dxditor.bean.Gjlb;
import itor.topnetwork.com.dxditor.bean.SbxxBean;

/**
 * Created by D.Han on 2017/11/15.
 */

public interface IMainpageView {
    /**
     * 提示toast
     */
    void showToast(String msg);

    /**
     * 刷新adapter
     */
    void refreshAdapter(ArrayList<Gjlb> gjxxBeans);
    /**
     * 设置基础数据
     */
    void setBaseData(List<SbxxBean> t);


    void onEmpty();

}
