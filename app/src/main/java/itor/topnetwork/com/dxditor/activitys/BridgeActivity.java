package itor.topnetwork.com.dxditor.activitys;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import itor.topnetwork.com.dxditor.R;
import itor.topnetwork.com.dxditor.hybrid.bean.EchartsDataBean;
import itor.topnetwork.com.dxditor.presenter.BasePresenter;

/**
 * @Description:桥梁监测
 * @Created by D.Han on 2018/3/19 11:33 in Peking.
 */

public class BridgeActivity extends BaseActivity{

    private WebView bridge_echarts;
    private ProgressDialog dialog;

    @Override
    public BasePresenter initPresent() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.bridge_activity;
    }

    @Override
    public void initView() {
        bridge_echarts = (WebView)findViewById(R.id.bridge_echarts);
        dialog = new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage("请稍后...");

        WebSettings webSettings = bridge_echarts.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setSupportZoom(true);
        webSettings.setDisplayZoomControls(true);

    }

    @Override
    public void onPrepare() {
        bridge_echarts.loadUrl("file:///android_asset/echarts/bridge_total.html");
        bridge_echarts.setWebViewClient(new WebViewClient() {
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
                bridge_echarts.loadUrl("javascript:createChart('line'," + EchartsDataBean.getInstance().bridgeEcharts() + ");");
                System.out.println("javascript:createChart('line'," + EchartsDataBean.getInstance().bridgeEcharts() + ");");
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
    }
}
