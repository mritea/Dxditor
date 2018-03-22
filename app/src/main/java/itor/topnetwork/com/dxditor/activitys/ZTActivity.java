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
 * @Description:
 * @Created by D.Han on 2018/3/22 13:41 in Peking.
 */

public class ZTActivity extends BaseActivity implements View.OnClickListener{

    private WebView zt_echarts;
    private WebView zt_history_echarts;
    private ProgressDialog dialog;
    private Button a_but;
    private Button b_but;

    @Override
    public BasePresenter initPresent() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.zt_activity;
    }

    @Override
    public void initView() {
        zt_echarts = findViewById(R.id.zt_echarts);
        zt_history_echarts = findViewById(R.id.zt_history_echarts);

        dialog = new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage(getResources().getString(R.string.waiting));

        WebSettings webSettings = zt_echarts.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setSupportZoom(true);
        webSettings.setDisplayZoomControls(true);

        a_but = (Button)findViewById(R.id.a_but);
        b_but = (Button)findViewById(R.id.b_but);
        a_but.setOnClickListener(this);
        b_but.setOnClickListener(this);
    }

    @Override
    public void onPrepare() {
        zt_echarts.loadUrl("file:///android_asset/echarts/zt_top_echarts.html");
        zt_echarts.setWebViewClient(new WebViewClient() {
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
                zt_echarts.loadUrl("javascript:createChart('bar'," + EchartsDataBean.getInstance().ztTopEcharts() + ");");
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.a_but:
                zt_echarts.loadUrl("javascript:createChart('bar'," + EchartsDataBean.getInstance().ztTopEcharts() + ");");
                a_but.setBackgroundColor(getResources().getColor(R.color.spz_but_back));
                b_but.setBackgroundColor(getResources().getColor(R.color.white));

                break;
            case R.id.b_but:
                zt_echarts.loadUrl("javascript:createChart('bar'," + EchartsDataBean.getInstance().ztTopBEcharts() + ");");
                a_but.setBackgroundColor(getResources().getColor(R.color.white));
                b_but.setBackgroundColor(getResources().getColor(R.color.spz_but_back));
                break;


        }
    }
}
