package itor.topnetwork.com.dxditor.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import itor.topnetwork.com.dxditor.presenter.BaseFragmentPresenter;


/**
 * Fragment基类
 * Created by D.Han on 2017/12/6.
 */

public abstract class BaseFragment<T extends BaseFragmentPresenter> extends Fragment{
    protected T basepresenter;
    public View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(getLayout(), container, false);
        initView();
        basepresenter = initPresent();
        onPrepare();
        return view;
    }
    public abstract T initPresent();
    public abstract int getLayout();

    public abstract void initView();

    public abstract void onPrepare();

}
