package itor.topnetwork.com.dxditor.activitys;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import itor.topnetwork.com.dxditor.R;
import itor.topnetwork.com.dxditor.hybrid.bean.EchartsDataBean;
import itor.topnetwork.com.dxditor.view.zt.EchartsrefreshInterface;

/**
 * @Description:坠坨
 * @Created by D.Han on 2018/3/26 10:51 in Peking.
 */

public class ZtTest extends Activity implements View.OnClickListener, EchartsrefreshInterface {
    private WebView zt_qx_echarts, test_echarts;
    private Button a_zt_but, b_zt_but, avalue_but, bvalue_but, oneday_but, threeday_but, oneweek_but, onemonth_but, start_time_bt, end_time_bt;
    private ProgressDialog dialog;
    private EchartsDataBean echartsDataBean;
    private String datas;
    private LinearLayout one_title, zt_select_ll, ab_select_ll, ztsyt_ll;
    private RelativeLayout title_include;
    public ImageView back_iv;
    private Calendar calendar;
    private int year;
    private int month;
    private int day;
    private SimpleDateFormat dateFormat;
    private Date startDate, endDate;
    private String endDateString="";
    private String startDateString="";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.zttest_activity);

        zt_qx_echarts = findViewById(R.id.zt_qx_echarts);

        a_zt_but = (Button) findViewById(R.id.a_zt_but);
        b_zt_but = (Button) findViewById(R.id.b_zt_but);
        avalue_but = (Button) findViewById(R.id.avalue_but);
        bvalue_but = (Button) findViewById(R.id.bvalue_but);

        oneday_but = (Button) findViewById(R.id.oneday_but);
        threeday_but = (Button) findViewById(R.id.threeday_but);
        oneweek_but = (Button) findViewById(R.id.oneweek_but);
        onemonth_but = (Button) findViewById(R.id.onemonth_but);

        start_time_bt = (Button) findViewById(R.id.start_time_bt);
        start_time_bt.setOnClickListener(this);
        end_time_bt = (Button) findViewById(R.id.end_time_bt);
        end_time_bt.setOnClickListener(this);

        title_include = findViewById(R.id.title_include);
        back_iv = findViewById(R.id.back_iv);
        back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        one_title = findViewById(R.id.one_title);
        zt_select_ll = findViewById(R.id.zt_select_ll);
        ab_select_ll = findViewById(R.id.ab_select_ll);
        ztsyt_ll = findViewById(R.id.ztsyt_ll);
        //横屏
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            title_include.setVisibility(View.GONE);
            one_title.setVisibility(View.GONE);
            zt_select_ll.setVisibility(View.GONE);
            ab_select_ll.setVisibility(View.GONE);
            ztsyt_ll.setVisibility(View.GONE);
        } else {//竖屏
            title_include.setVisibility(View.VISIBLE);
            one_title.setVisibility(View.VISIBLE);
            zt_select_ll.setVisibility(View.VISIBLE);
            ab_select_ll.setVisibility(View.VISIBLE);
            ztsyt_ll.setVisibility(View.VISIBLE);


        }
        if (savedInstanceState != null) {
            type_zt = savedInstanceState.getString("type_zt");
            type_abvalue = savedInstanceState.getString("type_abvalue");
            startDateString = savedInstanceState.getString("start_time_string");
            endDateString = savedInstanceState.getString("end_time_string");
            start_time_bt.setText(savedInstanceState.getString("start_time"));
            end_time_bt.setText(savedInstanceState.getString("end_time"));
            if (type_abvalue.equals("a")) {
                avalue_but.setBackgroundColor(getResources().getColor(R.color.abvalue_but_back_press));
                bvalue_but.setBackgroundColor(getResources().getColor(R.color.abvalue_but_back_normal));
            }
            if (type_zt.equals("002_01_01")) {
                a_zt_but.setBackgroundColor(getResources().getColor(R.color.spz_but_back_normal));
                b_zt_but.setBackgroundColor(getResources().getColor(R.color.spz_but_back_press));
            }
        }
        dialog = new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage(getResources().getString(R.string.waiting));

        WebSettings webSettings1 = zt_qx_echarts.getSettings();
        webSettings1.setJavaScriptEnabled(true);
        webSettings1.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings1.setSupportZoom(true);
        webSettings1.setDisplayZoomControls(true);


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

                echartsDataBean.ztLiveEcharts(type_zt,startDateString,endDateString);

            }
        });

        initDatePicker();
    }

    private void initDatePicker() {
        Date date = new Date();// 取时间
        // 获取日历对象
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        //calendar.add(calendar.DATE,2);// 把日期往后增加一天.整数往后推,负数往前移动
        // 获取当前对应的年、月、日的信息
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);

    }

    String type_abvalue = "b";
    String type_zt = "001_01_01";

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.a_zt_but:
                dialog.show();
                echartsDataBean.ztLiveEcharts("001_01_01",startDateString,endDateString);
                a_zt_but.setBackgroundColor(getResources().getColor(R.color.spz_but_back_press));
                b_zt_but.setBackgroundColor(getResources().getColor(R.color.spz_but_back_normal));
                type_zt = "001_01_01";
                break;
            case R.id.b_zt_but:
                dialog.show();
                echartsDataBean.ztLiveEcharts("002_01_01",startDateString,endDateString);
                a_zt_but.setBackgroundColor(getResources().getColor(R.color.spz_but_back_normal));
                b_zt_but.setBackgroundColor(getResources().getColor(R.color.spz_but_back_press));
                type_zt = "002_01_01";
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
            case R.id.oneday_but:
                break;
            case R.id.threeday_but:
                break;
            case R.id.oneweek_but:
                break;
            case R.id.onemonth_but:
                break;
            case R.id.start_time_bt:
                DatePickerDialog datedialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    private String startMonth;
                    private String startDay;

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if(monthOfYear + 1<10){
                             startMonth= "0"+(monthOfYear + 1);
                        }else{
                            startMonth= (monthOfYear + 1)+"";
                        }
                        if(dayOfMonth<10){
                            startDay= "0"+dayOfMonth;
                        }else{
                            startDay= dayOfMonth+"";
                        }
                        startDateString = year + startMonth +startDay;
                        start_time_bt.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                    }
                }, year, month, day);
                Calendar calendar = new GregorianCalendar();
                datedialog.getDatePicker().setMaxDate(calendar.getTime().getTime());
                datedialog.show();
                break;
            case R.id.end_time_bt:
                try {
                    startDate = dateFormat.parse(start_time_bt.getText().toString().trim());
                } catch (Exception e) {
                    System.out.println("dateFormat.parse(start_time_bt.getText().toString().trim());异常");
                }
                if (start_time_bt.getText().toString().equals(getResources().getString(R.string.start_time))) {
                    Toast.makeText(ZtTest.this, "先选择开始时间", Toast.LENGTH_SHORT).show();
                    return;
                }
                DatePickerDialog datedialog1 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    private String endMonth;
                    private String endDay;
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                       /* try {
                            endDate = dateFormat.parse(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        } catch (Exception e) {
                            System.out.println("异常");
                        }
                        long days = (endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000);
                        if (days > 7) {
                            Toast.makeText(ZtTest.this, "开始结束时间请勿超过7天", Toast.LENGTH_SHORT).show();
                            //时间差小于7天
                            return;
                        }*/
                        end_time_bt.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        if(monthOfYear + 1<10){
                            endMonth= "0"+(monthOfYear + 1);
                        }else{
                            endMonth= (monthOfYear + 1)+"";
                        }
                        if(dayOfMonth<10){
                            endDay= "0"+dayOfMonth;
                        }else{
                            endDay= dayOfMonth+"";
                        }
                        endDateString = year + endMonth +endDay;
                        echartsDataBean.ztLiveEcharts(type_zt,startDateString,endDateString);
                    }
                }, year, month, day);
                datedialog1.getDatePicker().setMinDate(startDate.getTime());
                Calendar calendar1 = new GregorianCalendar();
                if (startDate.getTime() + 6 * 24 * 60 * 60 * 1000 < calendar1.getTime().getTime()) {
                    datedialog1.getDatePicker().setMaxDate(startDate.getTime() + 6 * 24 * 60 * 60 * 1000);
                } else {
                    datedialog1.getDatePicker().setMaxDate(calendar1.getTime().getTime());
                }
                datedialog1.show();
                break;
        }
    }

    @Override
    public void refresh(String s) {
        // System.out.println("s:" + s);
        datas = s;
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("type_zt", type_zt);
        outState.putString("type_abvalue", type_abvalue);
        outState.putString("start_time_string", startDateString);
        outState.putString("start_time", start_time_bt.getText().toString());
        outState.putString("end_time_string", endDateString);
        outState.putString("end_time", end_time_bt.getText().toString());
    }
}
