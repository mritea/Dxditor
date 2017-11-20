package itor.topnetwork.com.dxditor.presenter;

import itor.topnetwork.com.dxditor.activitys.BaseActivity;

/**
 * Created by D.Han on 2017/11/15.
 */

public abstract class BasePresenter<T extends BaseActivity> {
    abstract void initData();
}
