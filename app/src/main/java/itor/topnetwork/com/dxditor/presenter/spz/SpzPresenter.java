package itor.topnetwork.com.dxditor.presenter.spz;

import java.util.ArrayList;

import itor.topnetwork.com.dxditor.activitys.SPZActivity;
import itor.topnetwork.com.dxditor.bean.SpzWarning;
import itor.topnetwork.com.dxditor.model.spz.SpzModel;
import itor.topnetwork.com.dxditor.presenter.BasePresenter;
import itor.topnetwork.com.dxditor.utils.ValueCallBack;
import itor.topnetwork.com.dxditor.view.spz.ISpzActivityView;
import itor.topnetwork.com.dxditor.view.zt.EchartsrefreshInterface;

/**
 * @Description:
 * @Created by D.Han on 2018/3/27 14:48 in Peking.
 */

public class SpzPresenter extends BasePresenter<SPZActivity> implements ISpzPresenter {
    private ISpzActivityView pv;
    private final SpzModel spzModel;
    private EchartsrefreshInterface er;

    public SpzPresenter(ISpzActivityView pv,EchartsrefreshInterface er) {
        this.pv = pv;
        spzModel = new SpzModel();
        this.er=er;
    }

    @Override
    public ArrayList<SpzWarning> getSpzWarningadapterData() {
        return spzModel.getWarningList();
    }



    @Override
    public void initData() {
        spzModel.getWarningData(new ValueCallBack<ArrayList<SpzWarning>>() {
            @Override
            public void onSuccess(ArrayList<SpzWarning> spzWarnings) {
                pv.refreshWarningAdapter(spzWarnings);
            }

            @Override
            public void onFail(String code) {
                if (code.equals("01")) {
                    pv.onDataError("01");
                }
            }
        });
    }
    @Override
    public void getSpzlineData(int position) {
        spzModel.getTrendData(new ValueCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                er.refresh("1",s);
            }

            @Override
            public void onFail(String code) {
                if (code.equals("01")) {
                    pv.onDataError("01");
                }

            }
        },position);

    }




}
