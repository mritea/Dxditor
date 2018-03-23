package itor.topnetwork.com.dxditor.model.bridge;

import java.util.ArrayList;

import itor.topnetwork.com.dxditor.bean.BridgeTrend;
import itor.topnetwork.com.dxditor.bean.BridgeWarning;
import itor.topnetwork.com.dxditor.utils.ValueCallBack;

/**
 * @Description:
 * @Created by D.Han on 2018/3/23 13:52 in Peking.
 */

public interface IBridgeModel {
    //获取桥梁告警信息
    public void getWarningData(ValueCallBack<ArrayList<BridgeWarning>> callBack);
    //获取桥梁趋势图
    public void getTrendData(ValueCallBack<ArrayList<BridgeTrend>> callBack);
}
