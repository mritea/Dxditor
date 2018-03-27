package itor.topnetwork.com.dxditor.view.spz;

import java.util.ArrayList;

import itor.topnetwork.com.dxditor.bean.SpzWarning;

/**
 * @Description:
 * @Created by D.Han on 2018/3/27 14:56 in Peking.
 */

public interface ISpzActivityView {
    /**
     * 提示toast
     */
    void showToast(String msg);

    public void onDataError(String error);

    public void refreshWarningAdapter(ArrayList<SpzWarning> data);
}
