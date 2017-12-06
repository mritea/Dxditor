package itor.topnetwork.com.dxditor.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * Fragment 基类
 * Created by D.Han on 2017/12/6.
 */

public abstract class BaseFragmentActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
        onPrepare();
    }

    public abstract int getLayout();

    public abstract void initView();

    public abstract void onPrepare();
}
