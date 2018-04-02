package itor.topnetwork.com.dxditor.presenter;

import java.util.ArrayList;
import java.util.List;

import itor.topnetwork.com.dxditor.activitys.MainActivity;
import itor.topnetwork.com.dxditor.bean.Gjlb;
import itor.topnetwork.com.dxditor.bean.GjxxBean;
import itor.topnetwork.com.dxditor.bean.SbxxBean;
import itor.topnetwork.com.dxditor.model.MainpageModel;
import itor.topnetwork.com.dxditor.utils.ValueCallBack;
import itor.topnetwork.com.dxditor.view.IMainpageView;
import itor.topnetwork.com.dxditor.view.zt.EchartsrefreshInterface;

/**
 * Created by D.Han on 2017/11/15.
 */

public class MainpagePresenter extends BasePresenter<MainActivity> implements IMainpagePresenter {
    private IMainpageView iMainpageView;
    private MainpageModel mainpageModel;
    private EchartsrefreshInterface er;

    public MainpagePresenter(IMainpageView iMainpageView,EchartsrefreshInterface er) {
        this.iMainpageView = iMainpageView;
        this.er = er;
        this.mainpageModel = new MainpageModel();

    }

    @Override
    public ArrayList<Gjlb> getGjadapterData() {
        return mainpageModel.getgjlblist();
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
        mainpageModel.getQjxxData(new ValueCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                er.refresh(s);
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
        mainpageModel.getgjlbData(new ValueCallBack<ArrayList<Gjlb>>() {
            @Override
            public void onSuccess(ArrayList<Gjlb> gjxxBeans) {
                iMainpageView.refreshAdapter(gjxxBeans);
            }

            @Override
            public void onFail(String code) {

            }
        });
    }


}
