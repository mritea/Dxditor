package itor.topnetwork.com.dxditor.presenter.spz;

import java.util.ArrayList;

import itor.topnetwork.com.dxditor.bean.SpzWarning;
import itor.topnetwork.com.dxditor.utils.ValueCallBack;

/**
 * @Description:
 * @Created by D.Han on 2018/3/27 14:49 in Peking.
 */

public interface ISpzPresenter {
    public ArrayList<SpzWarning> getSpzWarningadapterData();

    public void getSpzlineData(int position);


}
