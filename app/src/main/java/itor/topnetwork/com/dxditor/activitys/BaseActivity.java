package itor.topnetwork.com.dxditor.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import itor.topnetwork.com.dxditor.presenter.BasePresenter;

/**
 * Created by D.Han on 2017/11/15.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    protected T basepresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
        basepresenter = initPresent();
        onPrepare();
    }
    public abstract T initPresent();

    public abstract int getLayout();

    public abstract void initView();

    public abstract void onPrepare();
}
