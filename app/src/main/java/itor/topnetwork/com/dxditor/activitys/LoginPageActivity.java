package itor.topnetwork.com.dxditor.activitys;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import itor.topnetwork.com.dxditor.R;
import itor.topnetwork.com.dxditor.bean.User;

/**
 * Created by handong on 2017/11/23.
 */

public class LoginPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginLayoutBinding binding=DataBindingUtil.setContentView(this, R.layout.login_layout);
        User user = new User("23", "User");
        binding.setUser(user);

    }

    @Override
    void initView() {
        final TextInputLayout usernameWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        final TextInputLayout passwordWrapper = (TextInputLayout) findViewById(R.id.psdWrapper);
        usernameWrapper.setHint("用户名");
        passwordWrapper.setHint("密码");
    }


}
