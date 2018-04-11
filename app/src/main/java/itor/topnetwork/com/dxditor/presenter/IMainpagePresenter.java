package itor.topnetwork.com.dxditor.presenter;

import java.util.ArrayList;
import java.util.List;

import itor.topnetwork.com.dxditor.bean.Gjlb;
import itor.topnetwork.com.dxditor.bean.SbxxBean;

/**
 * Created by D.Han on 2017/11/15.
 */

public interface IMainpagePresenter {
        List<SbxxBean> getData();
        ArrayList<Gjlb> getGjadapterData();
       void getGjData();
}
