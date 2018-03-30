package itor.topnetwork.com.dxditor.presenter.bridge;

import java.util.ArrayList;

import itor.topnetwork.com.dxditor.activitys.BridgeActivity;
import itor.topnetwork.com.dxditor.bean.BridgeWarning;
import itor.topnetwork.com.dxditor.model.bridge.BridgeModel;
import itor.topnetwork.com.dxditor.presenter.BasePresenter;
import itor.topnetwork.com.dxditor.utils.ValueCallBack;
import itor.topnetwork.com.dxditor.view.bridge.IBridgeActivityView;
import itor.topnetwork.com.dxditor.view.zt.EchartsrefreshInterface;

/**
 * @Description:
 * @Created by D.Han on 2018/3/23 13:39 in Peking.
 */

public class BridgePresenter extends BasePresenter<BridgeActivity> implements IBridgePresenter {
    private IBridgeActivityView bv;
    private BridgeModel bridgeModel;
    private EchartsrefreshInterface er;

    public BridgePresenter(IBridgeActivityView bv, EchartsrefreshInterface er) {
        this.bv = bv;
        this.er = er;
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

    }


    @Override
    public ArrayList<BridgeWarning> getBridgeWarningadapterData() {
        return bridgeModel.getWarningList();
    }

    @Override
    public void getBridgelineData(int position) {
        bridgeModel.getTrendData(new ValueCallBack<String>() {
            @Override
            public void onSuccess(String bridgeTrends) {
                er.refresh(bridgeTrends);
            }

            @Override
            public void onFail(String code) {
                bv.onDataError(code);
            }
        }, position);
    }
}
