package itor.topnetwork.com.dxditor.fragment.zt;

import android.widget.TextView;

import itor.topnetwork.com.dxditor.R;
import itor.topnetwork.com.dxditor.fragment.BaseFragment;
import itor.topnetwork.com.dxditor.presenter.BaseFragmentPresenter;

/**
 * Created by D.Han on 2017/12/6.
 */

public class ZTGjfxFragment extends BaseFragment {
    String mText;
    public static String PARAM_KEY_TEXT="param_key_text";
    private TextView textView;

    @Override
    public BaseFragmentPresenter initPresent() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_item;
    }

    @Override
    public void initView() {
        textView = (TextView) view.findViewById(R.id.textview);

    }

    @Override
    public void onPrepare() {
        mText = (getArguments() != null) ? getArguments().getString(PARAM_KEY_TEXT) : "";
        textView.setText(mText);
    }
}