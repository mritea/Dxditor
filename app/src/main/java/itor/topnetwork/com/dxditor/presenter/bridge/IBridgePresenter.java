package itor.topnetwork.com.dxditor.presenter.bridge;

import java.util.ArrayList;

import itor.topnetwork.com.dxditor.bean.BridgeWarning;

/**
 * @Description:
 * @Created by D.Han on 2018/3/23 13:41 in Peking.
 */

public interface IBridgePresenter {
   public ArrayList<BridgeWarning> getBridgeWarningadapterData();
   public void getBridgelineData(int position);
}
