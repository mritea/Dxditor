package itor.topnetwork.com.dxditor.presenter.bridge;

import java.util.ArrayList;

import itor.topnetwork.com.dxditor.activitys.BridgeActivity;
import itor.topnetwork.com.dxditor.bean.BridgeTrend;
import itor.topnetwork.com.dxditor.bean.BridgeWarning;
import itor.topnetwork.com.dxditor.model.bridge.BridgeModel;
import itor.topnetwork.com.dxditor.presenter.BasePresenter;
import itor.topnetwork.com.dxditor.utils.ValueCallBack;
import itor.topnetwork.com.dxditor.view.bridge.IBridgeActivityView;

/**
 * @Description:
 * @Created by D.Han on 2018/3/23 13:39 in Peking.
 */

public class BridgePresenter extends BasePresenter<BridgeActivity> implements IBridgePresenter {
    IBridgeActivityView bv;
    BridgeModel bridgeModel;

    public BridgePresenter(IBridgeActivityView bv) {
        this.bv = bv;
        this.bridgeModel = new BridgeModel();

    }

    @Override
    public void initData() {
        bridgeModel.getWarningData(new ValueCallBack<ArrayList<BridgeWarning>>() {
            @Override
            public void onSuccess(ArrayList<BridgeWarning> bridgeWarnings) {

bv.refreshWarningAdapter(bridgeWarnings);
            }

            @Override
            public void onFail(String code) {

    bv.onDataError(code);

            }
        });
        bridgeModel.getTrendData(new ValueCallBack<ArrayList<BridgeTrend>>() {
            @Override
            public void onSuccess(ArrayList<BridgeTrend> bridgeTrends) {

            }

            @Override
            public void onFail(String code) {

            }
        });
    }


    @Override
    public ArrayList<BridgeWarning> getBridgeWarningadapterData() {
        return bridgeModel.getWarningList();
    }
}
