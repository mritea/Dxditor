package itor.topnetwork.com.dxditor.view;

/**
 * Created by D.Han on 2017/11/15.
 */

public interface IMainpageView<T> {
    /**
     * 提示toast
     */
    void showToast(String msg);

    /**
     * 刷新adapter
     */
    void refreshAdapter();
    /**
     * 设置基础数据
     */
    void setBaseData(T t);

    void onEmpty();
}
