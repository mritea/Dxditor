package itor.topnetwork.com.dxditor.activitys;

import android.widget.TextView;

import java.util.List;

import itor.topnetwork.com.dxditor.R;
import itor.topnetwork.com.dxditor.bean.SbxxBean;
import itor.topnetwork.com.dxditor.presenter.MainpagePresenter;
import itor.topnetwork.com.dxditor.view.IMainpageView;

/**
 * 物联网Android
 */
public class MainActivity extends BaseActivity<MainpagePresenter> implements IMainpageView<List<SbxxBean>> {


    private TextView gj, zc, lx;

    @Override
    MainpagePresenter initPresent() {
        return new MainpagePresenter(this);
    }

    @Override
    int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    void initView() {
        gj = (TextView) findViewById(R.id.gj);
        zc = (TextView) findViewById(R.id.zc);
        lx = (TextView) findViewById(R.id.lx);

    }

    @Override
    void onPrepare() {
        basepresenter.initData();
    }

    /**
     * 设置基础数据
     */
    @Override
    public void setBaseData(List<SbxxBean> sbxxBeans) {
        gj.setText(sbxxBeans.get(0).getCount()+"");
        zc.setText(sbxxBeans.get(1).getCount()+"");
        lx.setText(sbxxBeans.get(2).getCount()+"");
    }

    /**
     * 提示toast
     *
     * @param msg
     */
    @Override
    public void showToast(String msg) {

    }

    /**
     * 刷新adapter
     */
    @Override
    public void refreshAdapter() {

    }

    @Override
    public void onEmpty() {

    }
}
