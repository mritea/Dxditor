package itor.topnetwork.com.dxditor.activitys;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import itor.topnetwork.com.dxditor.R;
import itor.topnetwork.com.dxditor.hybrid.bean.EchartsDataBean;
import itor.topnetwork.com.dxditor.view.zt.EchartsrefreshInterface;

/**
 * @Description:
 * @Created by D.Han on 2018/3/26 10:51 in Peking.
 */

public class ZtTest extends Activity implements View.OnClickListener, EchartsrefreshInterface {
    private WebView zt_qx_echarts;
    private Button a_zt_but, b_zt_but, avalue_but, bvalue_but;
    private ProgressDialog dialog;
    private EchartsDataBean echartsDataBean;
    private String datas;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.zttest_activity);

        zt_qx_echarts = findViewById(R.id.zt_qx_echarts);

        dialog = new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage(getResources().getString(R.string.waiting));

        WebSettings webSettings1 = zt_qx_echarts.getSettings();
        webSettings1.setJavaScriptEnabled(true);
        webSettings1.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings1.setSupportZoom(true);
        webSettings1.setDisplayZoomControls(true);

        a_zt_but = (Button) findViewById(R.id.a_zt_but);
        b_zt_but = (Button) findViewById(R.id.b_zt_but);
        avalue_but = (Button) findViewById(R.id.avalue_but);
        bvalue_but = (Button) findViewById(R.id.bvalue_but);
        a_zt_but.setOnClickListener(this);
        b_zt_but.setOnClickListener(this);
        avalue_but.setOnClickListener(this);
        bvalue_but.setOnClickListener(this);

        echartsDataBean = new EchartsDataBean(this);
        zt_qx_echarts.loadUrl("file:///android_asset/echarts/ztLive.html");
        zt_qx_echarts.setWebViewClient(new WebViewClient() {
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
                echartsDataBean.ztLiveEcharts("001_01_01");

            }
        });

    }

    String type_abvalue = "a";

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.a_zt_but:
                dialog.show();
                echartsDataBean.ztLiveEcharts("001_01_01");
                a_zt_but.setBackgroundColor(getResources().getColor(R.color.spz_but_back_press));
                b_zt_but.setBackgroundColor(getResources().getColor(R.color.spz_but_back_normal));
                break;
            case R.id.b_zt_but:
                dialog.show();
                echartsDataBean.ztLiveEcharts("002_01_01");
                a_zt_but.setBackgroundColor(getResources().getColor(R.color.spz_but_back_normal));
                b_zt_but.setBackgroundColor(getResources().getColor(R.color.spz_but_back_press));
                break;
            case R.id.avalue_but:
                zt_qx_echarts.loadUrl("javascript:createChart('aline'," + datas + ");");
                avalue_but.setBackgroundColor(getResources().getColor(R.color.abvalue_but_back_press));
                bvalue_but.setBackgroundColor(getResources().getColor(R.color.abvalue_but_back_normal));
                type_abvalue = "a";
                break;
            case R.id.bvalue_but:
                zt_qx_echarts.loadUrl("javascript:createChart('bline'," + datas + ");");
                avalue_but.setBackgroundColor(getResources().getColor(R.color.abvalue_but_back_normal));
                bvalue_but.setBackgroundColor(getResources().getColor(R.color.abvalue_but_back_press));
                type_abvalue = "b";
                break;
        }
    }

    @Override
    public void refresh(String s) {
        datas = s;
        System.out.println("datas:"+datas);
        if (!ZtTest.this.isFinishing()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    if (type_abvalue.equals("a")) {
                        zt_qx_echarts.loadUrl("javascript:createChart('aline'," + datas + ");");
                    } else if (type_abvalue.equals("b")) {
                        zt_qx_echarts.loadUrl("javascript:createChart('bline'," + datas + ");");
                    }
                }
            });
        }
    }
}
