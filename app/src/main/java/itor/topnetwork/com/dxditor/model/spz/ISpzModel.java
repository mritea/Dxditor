package itor.topnetwork.com.dxditor.model.spz;

import java.util.ArrayList;

import itor.topnetwork.com.dxditor.bean.SpzWarning;
import itor.topnetwork.com.dxditor.utils.ValueCallBack;

/**
 * @Description:
 * @Created by D.Han on 2018/3/27 15:03 in Peking.
 */

public interface ISpzModel {
    //获取声屏障告警信息
    public void getWarningData(ValueCallBack<ArrayList<SpzWarning>> callBack);
    //获取声屏障趋势图
    public void getTrendData(ValueCallBack<String> callBack, int position);
}
