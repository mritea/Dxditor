package itor.topnetwork.com.dxditor.presenter;

import itor.topnetwork.com.dxditor.fragment.BaseFragment;

/**
 * Created by D.Han on 2017/12/6.
 */

public abstract class BaseFragmentPresenter <T extends BaseFragment> {
    public abstract void initData();
}
