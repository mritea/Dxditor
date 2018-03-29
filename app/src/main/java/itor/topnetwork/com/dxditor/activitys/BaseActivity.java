package itor.topnetwork.com.dxditor.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import itor.topnetwork.com.dxditor.R;
import itor.topnetwork.com.dxditor.presenter.BasePresenter;

/**
 * Created by D.Han on 2017/11/15.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    protected T basepresenter;
    public ImageView back_iv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        back_iv = findViewById(R.id.back_iv);
        initView();
        basepresenter = initPresent();
        onPrepare();
        back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }
    public abstract T initPresent();

    public abstract int getLayout();

    public abstract void initView();

    public abstract void onPrepare();
}
