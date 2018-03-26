package itor.topnetwork.com.dxditor.activitys;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import itor.topnetwork.com.dxditor.R;
import itor.topnetwork.com.dxditor.hybrid.bean.EchartsDataBean;
import itor.topnetwork.com.dxditor.presenter.BasePresenter;

/**
 * @Description:声屏障监测
 * @Created by D.Han on 2018/3/19 11:33 in Peking.
 */

public class SPZActivity extends BaseActivity implements View.OnClickListener{

    private WebView zpz_echarts;
    private ProgressDialog dialog;
    private Button yl_but;
    private Button wy_but;

    @Override
    public BasePresenter initPresent() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.spz_activity;
    }

    @Override
    public void initView() {
        yl_but = (Button)findViewById(R.id.yl_but);
        wy_but = (Button)findViewById(R.id.wy_but);
        yl_but.setOnClickListener(this);
        wy_but.setOnClickListener(this);
        zpz_echarts = (WebView)findViewById(R.id.zpz_echarts);
        dialog = new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage(getResources().getString(R.string.waiting));

        WebSettings webSettings = zpz_echarts.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setSupportZoom(true);
        webSettings.setDisplayZoomControls(true);
    }

    @Override
    public void onPrepare() {
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
                dialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //最好在这里调用js代码 以免网页未加载完成
                //line_echarts.loadUrl("javascript:createChart('line'," + EchartsDataBean.getInstance().getEchartsLineJson() + ");");
                zpz_echarts.loadUrl("javascript:createChart('ylline'," + EchartsDataBean.getInstance().spzYlEcharts() + ");");
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.yl_but:
                zpz_echarts.loadUrl("javascript:createChart('ylline'," + EchartsDataBean.getInstance().spzYlEcharts() + ");");
                yl_but.setBackgroundColor(getResources().getColor(R.color.spz_but_back_press));
                wy_but.setBackgroundColor(getResources().getColor(R.color.white));
                return;
            case R.id.wy_but:
                zpz_echarts.loadUrl("javascript:createChart('wyline'," + EchartsDataBean.getInstance().spzWyEcharts() + ");");
                wy_but.setBackgroundColor(getResources().getColor(R.color.spz_but_back_press));
                yl_but.setBackgroundColor(getResources().getColor(R.color.white));
                return;
        }
    }
}
