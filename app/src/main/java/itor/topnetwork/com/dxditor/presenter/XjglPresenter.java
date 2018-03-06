package itor.topnetwork.com.dxditor.presenter;

import java.util.ArrayList;

import itor.topnetwork.com.dxditor.bean.Gjlb;
import itor.topnetwork.com.dxditor.fragment.xj.XJGjglFragment;
import itor.topnetwork.com.dxditor.model.XjGjglModel;
import itor.topnetwork.com.dxditor.utils.ValueCallBack;
import itor.topnetwork.com.dxditor.view.IXjGjglView;

/**
 * 线夹告警管理
 * Created by D.Han on 2017/12/6.
 */

public class XjglPresenter extends BaseFragmentPresenter<XJGjglFragment>{
    private IXjGjglView iXjGjglView;
    private XjGjglModel xjGjglModel;

    public XjglPresenter(IXjGjglView iXjGjglView) {
        this.iXjGjglView = iXjGjglView;
        this.xjGjglModel = new XjGjglModel();

    }
    public ArrayList<Gjlb> getGjadapterData() {
        return xjGjglModel.getgjlblist();
    }
    @Override
    public void initData() {
        xjGjglModel.getgjlbData(new ValueCallBack<ArrayList<Gjlb>>() {
            @Override
            public void onSuccess(ArrayList<Gjlb> gjxxBeans) {
                iXjGjglView.refreshAdapter(gjxxBeans);
            }

            @Override
            public void onFail(String code) {

            }
        });
    }

}
