package itor.topnetwork.com.dxditor.view.bridge;

import java.util.ArrayList;

import itor.topnetwork.com.dxditor.bean.BridgeWarning;

/**
 * @Description:
 * @Created by D.Han on 2018/3/23 13:49 in Peking.
 */

public interface IBridgeActivityView {
    /**
     * 提示toast
     */
    void showToast(String msg);

    public void onDataError(String error);

    public void refreshWarningAdapter(ArrayList<BridgeWarning> data);


}
