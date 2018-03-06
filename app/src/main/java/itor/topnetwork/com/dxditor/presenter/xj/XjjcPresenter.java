package itor.topnetwork.com.dxditor.presenter.xj;

import itor.topnetwork.com.dxditor.bean.XjBean;
import itor.topnetwork.com.dxditor.fragment.xj.XjXjjcFragment;
import itor.topnetwork.com.dxditor.model.xj.XjjcModel;
import itor.topnetwork.com.dxditor.presenter.BaseFragmentPresenter;
import itor.topnetwork.com.dxditor.utils.ValueCallBack;
import itor.topnetwork.com.dxditor.view.xj.IXjjcView;

/**
 * Created by kimi on 2018/3/6.
 */

public class XjjcPresenter extends BaseFragmentPresenter<XjXjjcFragment> {
    private IXjjcView iXjjcView;
    private XjjcModel xjjcModel;

    public XjjcPresenter(IXjjcView iXjjcView) {
        this.iXjjcView = iXjjcView;
        this.xjjcModel = new XjjcModel();

    }

    @Override
    public void initData() {
        xjjcModel.xjjcData(new ValueCallBack<XjBean>() {
            @Override
            public void onSuccess(XjBean xj) {
                iXjjcView.refreshData(xj);
            }

            @Override
            public void onFail(String code) {

            }
        });
    }
}
