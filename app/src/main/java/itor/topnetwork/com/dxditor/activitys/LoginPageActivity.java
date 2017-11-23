package itor.topnetwork.com.dxditor.activitys;

import android.support.design.widget.TextInputLayout;

import itor.topnetwork.com.dxditor.R;
import itor.topnetwork.com.dxditor.presenter.BasePresenter;

/**
 * Created by handong on 2017/11/23.
 */

public class LoginPageActivity extends BaseActivity{
    @Override
    BasePresenter initPresent() {
        return null;
    }

    @Override
    int getLayout() {
        return R.layout.login_layout;
    }

    @Override
    void initView() {
        final TextInputLayout usernameWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        final TextInputLayout passwordWrapper = (TextInputLayout) findViewById(R.id.psdWrapper);
        usernameWrapper.setHint("用户名");
        passwordWrapper.setHint("密码");
    }

    @Override
    void onPrepare() {

    }
}
