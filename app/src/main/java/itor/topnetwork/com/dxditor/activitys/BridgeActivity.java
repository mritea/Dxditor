package itor.topnetwork.com.dxditor.activitys;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.CountDownTimer;
import android.view.animation.AlphaAnimation;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import itor.topnetwork.com.dxditor.R;
import itor.topnetwork.com.dxditor.hybrid.bean.EchartsDataBean;
import itor.topnetwork.com.dxditor.presenter.BasePresenter;

/**
 * @Description:桥梁监测
 * @Created by D.Han on 2018/3/19 11:33 in Peking.
 */

public class BridgeActivity extends BaseActivity {

    private WebView bridge_echarts;
    private ProgressDialog dialog;
    private ImageView num1back_iv;
    private ImageView num2back_iv;
    private ImageView num3back_iv;
    private ImageView num4back_iv;
    private ImageView num5back_iv;

    @Override
    public BasePresenter initPresent() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.bridge_activity;
    }

    private boolean isAlphaZero = true;

    @Override
    public void initView() {
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



        TimeCount time = new TimeCount(24*3600*1000, 300);

        time.start();

    }
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if (isAlphaZero) {
                AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);//初始化操作，参数传入0和1，即由透明度0变化到透明度为1
                num1back_iv.startAnimation(alphaAnimation);//开始动画
                num2back_iv.startAnimation(alphaAnimation);//开始动画
                num3back_iv.startAnimation(alphaAnimation);//开始动画
                num4back_iv.startAnimation(alphaAnimation);//开始动画
                num5back_iv.startAnimation(alphaAnimation);//开始动画
                alphaAnimation.setFillAfter(true);//动画结束后保持状态
                alphaAnimation.setDuration(300);//动画持续时间，单位为毫秒
                isAlphaZero = false;//标识位
            } else {
                AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);//初始化操作，参数传入1和0，即由透明度1变化到透明度为0
                num1back_iv.startAnimation(alphaAnimation);//开始动画
                num2back_iv.startAnimation(alphaAnimation);//开始动画
                num3back_iv.startAnimation(alphaAnimation);//开始动画
                num4back_iv.startAnimation(alphaAnimation);//开始动画
                num5back_iv.startAnimation(alphaAnimation);//开始动画
                isAlphaZero = true;//标识位
                alphaAnimation.setFillAfter(true);//动画结束后保持状态
                alphaAnimation.setDuration(300);//动画持续时间
            }
        }

        @Override
        public void onFinish() {
            num1back_iv.setBackground(getResources().getDrawable(R.drawable.red_back));

        }
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
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
    }
}
