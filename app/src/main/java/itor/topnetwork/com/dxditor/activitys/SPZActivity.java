package itor.topnetwork.com.dxditor.activitys;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import itor.topnetwork.com.dxditor.R;
import itor.topnetwork.com.dxditor.adapter.SpzWarningAdapter;
import itor.topnetwork.com.dxditor.bean.SpzWarning;
import itor.topnetwork.com.dxditor.presenter.spz.SpzPresenter;
import itor.topnetwork.com.dxditor.view.spz.ISpzActivityView;
import itor.topnetwork.com.dxditor.view.zt.EchartsrefreshInterface;

/**
 * @Description:声屏障监测
 * @Created by D.Han on 2018/3/19 11:33 in Peking.
 */

public class SPZActivity extends BaseActivity<SpzPresenter> implements View.OnClickListener, ISpzActivityView,EchartsrefreshInterface {

    private WebView zpz_echarts;
    private ProgressDialog dialog;
    private Button yl_but;
    private Button wy_but;
    private RecyclerView spz_warning_rv;
    private SpzWarningAdapter spzAdapter;

    @Override
    public SpzPresenter initPresent() {
        return new SpzPresenter(this,this);
    }

    @Override
    public int getLayout() {
        return R.layout.spz_activity;
    }

    @Override
    public void initView() {
        spz_warning_rv = (RecyclerView) findViewById(R.id.spz_warning_rv);


        yl_but = (Button) findViewById(R.id.yl_but);
        wy_but = (Button) findViewById(R.id.wy_but);
        yl_but.setOnClickListener(this);
        wy_but.setOnClickListener(this);
        zpz_echarts = (WebView) findViewById(R.id.zpz_echarts);
        dialog = new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage(getResources().getString(R.string.waiting));

        WebSettings webSettings = zpz_echarts.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setSupportZoom(true);
        webSettings.setDisplayZoomControls(true);
    }

    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onPrepare() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        // 设置布局管理器
        spz_warning_rv.setLayoutManager(mLayoutManager);
        spzAdapter = new SpzWarningAdapter(this, basepresenter.getSpzWarningadapterData());
        spz_warning_rv.setAdapter(spzAdapter);
        basepresenter.initData();

        zpz_echarts.loadUrl("file:///android_asset/echarts/spz_echarts.html");
        zpz_echarts.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //dialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //最好在这里调用js代码 以免网页未加载完成
                //line_echarts.loadUrl("javascript:createChart('line'," + EchartsDataBean.getInstance().getEchartsLineJson() + ");");
                /*zpz_echarts.loadUrl("javascript:createChart('ylline'," + EchartsDataBean.getInstance().spzYlEcharts() + ");");
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }*/
            }
        });
        spzAdapter.setOnItemClickListener(new SpzWarningAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                dialog.show();
                basepresenter.getSpzlineData(position);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.yl_but:
               // zpz_echarts.loadUrl("javascript:createChart('ylline'," + EchartsDataBean.getInstance().spzYlEcharts() + ");");
                yl_but.setBackgroundColor(getResources().getColor(R.color.spz_but_back_press));
                wy_but.setBackgroundColor(getResources().getColor(R.color.white));
                return;
            case R.id.wy_but:
                //zpz_echarts.loadUrl("javascript:createChart('wyline'," + EchartsDataBean.getInstance().spzWyEcharts() + ");");
                wy_but.setBackgroundColor(getResources().getColor(R.color.spz_but_back_press));
                yl_but.setBackgroundColor(getResources().getColor(R.color.white));
                return;
        }
    }
    @Override
    public void showToast(final String msg) {
        if (!SPZActivity.this.isFinishing()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(SPZActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    @Override
    public void onDataError(String error) {
        if (error.equals("01")) {
            showToast(getResources().getString(R.string.internet_error));
        }
    }

    @Override
    public void refreshWarningAdapter(final ArrayList<SpzWarning> data) {
        if (!SPZActivity.this.isFinishing()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    spzAdapter.updateData(data);
                }
            });
            basepresenter.getSpzlineData(0);
        }
    }

    @Override
    public void refresh(final String s) {
        if (!SPZActivity.this.isFinishing()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }

                    zpz_echarts.loadUrl("javascript:createChart('line'," + s + ");");

                }
            });
        }
    }
}
