package itor.topnetwork.com.dxditor.activitys;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import itor.topnetwork.com.dxditor.R;
import itor.topnetwork.com.dxditor.adapter.BridgeWarningAdapter;
import itor.topnetwork.com.dxditor.bean.BridgeWarning;
import itor.topnetwork.com.dxditor.presenter.bridge.BridgePresenter;
import itor.topnetwork.com.dxditor.view.bridge.IBridgeActivityView;
import itor.topnetwork.com.dxditor.view.zt.EchartsrefreshInterface;

/**
 * @Description:桥梁监测
 * @Created by D.Han on 2018/3/19 11:33 in Peking.
 */

public class BridgeActivity extends BaseActivity<BridgePresenter> implements IBridgeActivityView, EchartsrefreshInterface {

    private WebView bridge_echarts;
    private ProgressDialog dialog;
    private ImageView num1back_iv;
    private ImageView num2back_iv;
    private ImageView num3back_iv;
    private ImageView num4back_iv;
    private ImageView num5back_iv;
    private RecyclerView warning_rv;
    private BridgeWarningAdapter bwAdapter;

    @Override
    public BridgePresenter initPresent() {

        return new BridgePresenter(this, this);
    }

    @Override
    public int getLayout() {
        return R.layout.bridge_activity;
    }


    @Override
    public void initView() {
        warning_rv = (RecyclerView) findViewById(R.id.warning_rv);
        bridge_echarts = (WebView) findViewById(R.id.bridge_echarts);
        dialog = new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage(getResources().getString(R.string.waiting));

        WebSettings webSettings = bridge_echarts.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setSupportZoom(true);
        webSettings.setDisplayZoomControls(true);

        num1back_iv = (ImageView) findViewById(R.id.num1back_iv);
        num2back_iv = (ImageView) findViewById(R.id.num2back_iv);
        num3back_iv = (ImageView) findViewById(R.id.num3back_iv);
        num4back_iv = (ImageView) findViewById(R.id.num4back_iv);
        num5back_iv = (ImageView) findViewById(R.id.num5back_iv);

//闪烁动画
     /*   int duiation = 2000;
        ObjectAnimator obj = ObjectAnimator.ofFloat(num1back_iv, "alpha", 0, 1, 0);
        obj.setDuration(duiation);
        obj.setRepeatCount(-1);
        obj.start();
        ObjectAnimator obj1 = ObjectAnimator.ofFloat(num2back_iv, "alpha", 0, 1, 0);
        obj1.setDuration(duiation);
        obj1.setRepeatCount(-1);
        obj1.start();
        ObjectAnimator obj2 = ObjectAnimator.ofFloat(num3back_iv, "alpha", 0, 1, 0);
        obj2.setDuration(duiation);
        obj2.setRepeatCount(-1);
        obj2.start();
        ObjectAnimator obj3 = ObjectAnimator.ofFloat(num4back_iv, "alpha", 0, 1, 0);
        obj3.setDuration(duiation);
        obj3.setRepeatCount(-1);
        obj3.start();
        ObjectAnimator obj4 = ObjectAnimator.ofFloat(num5back_iv, "alpha", 0, 1, 0);
        obj4.setDuration(duiation);
        obj4.setRepeatCount(-1);
        obj4.start();*/

    }

    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onPrepare() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        // 设置布局管理器
        warning_rv.setLayoutManager(mLayoutManager);
        bwAdapter = new BridgeWarningAdapter(this, basepresenter.getBridgeWarningadapterData());
        warning_rv.setAdapter(bwAdapter);


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
                // dialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //最好在这里调用js代码 以免网页未加载完成
               /* bridge_echarts.loadUrl("javascript:createChart('line'," + EchartsDataBean.getInstance().bridgeEcharts() + ");");
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }*/
                basepresenter.initData();
            }
        });

        bwAdapter.setOnItemClickListener(new BridgeWarningAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                dialog.show();
                basepresenter.getBridgelineData(position);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showToast(final String msg) {
        if (!BridgeActivity.this.isFinishing()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(BridgeActivity.this, msg, Toast.LENGTH_SHORT).show();
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
    public void refreshWarningAdapter(final ArrayList<BridgeWarning> bridgeWarnings) {
        if (!BridgeActivity.this.isFinishing()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    bwAdapter.updateData(bridgeWarnings);
                }
            });
            basepresenter.getBridgelineData(0);
        }
    }

    @Override
    public void refresh(final String s) {
        if (!BridgeActivity.this.isFinishing()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    bridge_echarts.loadUrl("javascript:createChart('line'," + s + ");");

                }
            });
        }
    }
}
