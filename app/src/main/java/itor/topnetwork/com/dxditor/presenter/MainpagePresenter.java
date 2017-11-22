package itor.topnetwork.com.dxditor.presenter;

import java.util.List;

import itor.topnetwork.com.dxditor.activitys.MainActivity;
import itor.topnetwork.com.dxditor.bean.GjxxBean;
import itor.topnetwork.com.dxditor.bean.SbxxBean;
import itor.topnetwork.com.dxditor.model.MainpageModel;
import itor.topnetwork.com.dxditor.utils.ValueCallBack;
import itor.topnetwork.com.dxditor.view.IMainpageView;

/**
 * Created by D.Han on 2017/11/15.
 */

public class MainpagePresenter extends BasePresenter<MainActivity> implements IMainpagePresenter {
    private IMainpageView iMainpageView;
    private MainpageModel mainpageModel;

    public MainpagePresenter(IMainpageView iMainpageView) {
        this.iMainpageView = iMainpageView;
        this.mainpageModel = new MainpageModel();

    }

    @Override
    public List<SbxxBean> getData() {
        return null;
    }

    @Override
    public void initData() {
        mainpageModel.getTestData(new ValueCallBack<List<SbxxBean>>() {
            @Override
            public void onSuccess(List<SbxxBean> sbxxBeans) {
                iMainpageView.setBaseData(sbxxBeans);
            }

            @Override
            public void onFail(String code) {

            }
        });
        mainpageModel.getGjxxData(new ValueCallBack<List<GjxxBean>>() {
            @Override
            public void onSuccess(List<GjxxBean> gjxxBeans) {
                iMainpageView.setGjxxData(gjxxBeans);
            }

            @Override
            public void onFail(String code) {

            }
        });
        mainpageModel.getXtpfData(new ValueCallBack<List<GjxxBean>>() {
            @Override
            public void onSuccess(List<GjxxBean> gjxxBeans) {
            }

            @Override
            public void onFail(String code) {

            }
        });
    }


}
