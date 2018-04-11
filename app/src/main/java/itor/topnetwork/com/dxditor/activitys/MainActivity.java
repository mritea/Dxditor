package itor.topnetwork.com.dxditor.activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuPresenter;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import itor.topnetwork.com.dxditor.R;
import itor.topnetwork.com.dxditor.adapter.GjAdapter;
import itor.topnetwork.com.dxditor.bean.Gjlb;
import itor.topnetwork.com.dxditor.bean.SbxxBean;
import itor.topnetwork.com.dxditor.hybrid.bean.EchartsDataBean;
import itor.topnetwork.com.dxditor.myview.CircleView;
import itor.topnetwork.com.dxditor.myview.RaderView;
import itor.topnetwork.com.dxditor.presenter.MainpagePresenter;
import itor.topnetwork.com.dxditor.utils.Constants;
import itor.topnetwork.com.dxditor.view.IMainpageView;
import itor.topnetwork.com.dxditor.view.zt.EchartsrefreshInterface;

/**
 * 物联网Android
 */
public class MainActivity extends BaseActivity<MainpagePresenter> implements IMainpageView, EchartsrefreshInterface {
    private TextView gj, zc, lx;
    private NavigationView navigationView;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private DrawerLayout main_drawerlayout;
    private GjAdapter gjAdapter;
    private ProgressDialog dialog;
    private WebView line_echarts;
    private CircleView gj_circleview, zc_circleview, lx_circleview;
    private WebView gj_pie;

    @Override
    public MainpagePresenter initPresent() {
        return new MainpagePresenter(this, this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        back_iv.setVisibility(View.GONE);


        // gj = (TextView) findViewById(R.id.gj);
        gj_circleview = (CircleView) findViewById(R.id.gj_circleview);
        zc_circleview = (CircleView) findViewById(R.id.zc_circleview);
        lx_circleview = (CircleView) findViewById(R.id.lx_circleview);
        // zc = (TextView) findViewById(R.id.zc);
        //lx = (TextView) findViewById(R.id.lx);
        //告警信息
        //gjpiechart = (PieChart) findViewById(R.id.gjxx);
        gj_pie = findViewById(R.id.gj_pie);


        main_drawerlayout = findViewById(R.id.main_drawerlayout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        setNavigationMenuLineStyle(navigationView, Color.parseColor("#5D6891"), 2);
        Resources resource = (Resources) getBaseContext().getResources();
        ColorStateList csl = (ColorStateList) resource.getColorStateList(R.color.navigation_menu_item_color);
        navigationView.setItemTextColor(csl);
//**设置MenuItem默认选中项**//*
        // navigationView.getMenu().getItem(0).setChecked(false);
        initnavigation();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);


        line_echarts = (WebView) findViewById(R.id.line_echarts);

        dialog = new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage(getResources().getString(R.string.waiting));

        WebSettings webSettings = line_echarts.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setSupportZoom(true);
        webSettings.setDisplayZoomControls(true);
        line_echarts.loadUrl("file:///android_asset/echarts/mainpage_total.html");
        line_echarts.setWebViewClient(new WebViewClient() {
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
                basepresenter.initData();
                if (Constants.testData) {
                    line_echarts.loadUrl("javascript:createChart('createbarlineChart'," + EchartsDataBean.getInstance().getEchartsTotalJson() + ");");
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }

            }
        });

        WebSettings webSettings1 = gj_pie.getSettings();
        webSettings1.setJavaScriptEnabled(true);
        webSettings1.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings1.setSupportZoom(true);
        webSettings1.setDisplayZoomControls(true);

        gj_pie.loadUrl("file:///android_asset/echarts/gj_pie.html");
        gj_pie.setWebViewClient(new WebViewClient() {
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
                basepresenter.getGjData();

            }
        });
    }

    private void initnavigation() {
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mainpage:
                        main_drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.brigemanager://桥梁管理
                        startActivity(new Intent(MainActivity.this, BridgeActivity.class));
                        // main_drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.SPZmanager://声屏障管理
                        startActivity(new Intent(MainActivity.this, SPZActivity.class));
                        //main_drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                    /*case R.id.xjmanager://线夹管理
                        startActivity(new Intent(MainActivity.this, XJActivity.class));
                        //main_drawerlayout.closeDrawer(GravityCompat.START);
                        break;*/
                    case R.id.ztmanager://坠坨管理
                        startActivity(new Intent(MainActivity.this, ZtTest.class));
                        //startActivity(new Intent(MainActivity.this, ZTActivity.class));
                        // main_drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.GDBmanager://轨道板管理
                        startActivity(new Intent(MainActivity.this, GDBActivity.class));
                        // main_drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.mine:
                        startActivity(new Intent(MainActivity.this, RaderView.class));
                        main_drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                    /*case R.id.zt_test:

                        //main_drawerlayout.closeDrawer(GravityCompat.START);
                        break;*/
                }


                return true;
            }
        });

        View drawerView = navigationView.getHeaderView(0);
        LinearLayout help_ll = (LinearLayout) drawerView.findViewById(R.id.help_ll);
        help_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "帮助", Snackbar.LENGTH_LONG).show();
            }
        });
    }




    @Override
    public void onPrepare() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        gjAdapter = new GjAdapter(this, basepresenter.getGjadapterData());
        mRecyclerView.setAdapter(gjAdapter);

    }


    /**
     * 设置顶部三个圆的基础数据
     */
    @Override
    public void setBaseData(final List<SbxxBean> sbxxBeans) {
        if (!MainActivity.this.isFinishing()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < sbxxBeans.size(); i++) {
                        if (sbxxBeans.get(i).getTypeCode() == 0) {
                            zc_circleview.setData(80, sbxxBeans.get(i).getTypeCount() + "", "#0ABB6D");
                        } else if (sbxxBeans.get(i).getTypeCode() == 1) {
                            // gj.setText(sbxxBeans.get(i).getTypeCount());
                            gj_circleview.setData(10, sbxxBeans.get(i).getTypeCount() + "", "#F5B400");
                        } else if (sbxxBeans.get(i).getTypeCode() == 2) {
                            lx_circleview.setData(10, sbxxBeans.get(i).getTypeCount() + "", "#00AFDC");
                        }
                    }
                }
            });
        }
    }

    /**
     * 提示toast
     *
     * @param msg
     */
    @Override
    public void showToast(String msg) {

    }

    /**
     * 刷新adapter
     */
    @Override
    public void refreshAdapter(ArrayList<Gjlb> gjxxBeans) {

        gjAdapter.updateData(gjxxBeans);
    }

    @Override
    public void onEmpty() {

    }


    /**
     * 给 NavigationView 的Menu的分割线 设置高度和颜色
     *
     * @param navigationView
     * @param color
     * @param height
     */
    public static void setNavigationMenuLineStyle(NavigationView navigationView, @ColorInt final int color, final int height) {
        try {
            Field fieldByPressenter = navigationView.getClass().getDeclaredField("mPresenter");
            fieldByPressenter.setAccessible(true);
            NavigationMenuPresenter menuPresenter = (NavigationMenuPresenter) fieldByPressenter.get(navigationView);
            Field fieldByMenuView = menuPresenter.getClass().getDeclaredField("mMenuView");
            fieldByMenuView.setAccessible(true);
            final NavigationMenuView mMenuView = (NavigationMenuView) fieldByMenuView.get(menuPresenter);
            mMenuView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
                @Override
                public void onChildViewAttachedToWindow(View view) {
                    RecyclerView.ViewHolder viewHolder = mMenuView.getChildViewHolder(view);
                    if (viewHolder != null && "SeparatorViewHolder".equals(viewHolder.getClass().getSimpleName()) && viewHolder.itemView != null) {
                        if (viewHolder.itemView instanceof FrameLayout) {
                            FrameLayout frameLayout = (FrameLayout) viewHolder.itemView;
                            View line = frameLayout.getChildAt(0);
                            line.setBackgroundColor(color);
                            line.getLayoutParams().height = height;
                            line.setLayoutParams(line.getLayoutParams());
                        }
                    }
                }

                @Override
                public void onChildViewDetachedFromWindow(View view) {

                }
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refresh(final String responseCode, final String s) {
        if (!MainActivity.this.isFinishing()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    if (responseCode.equals("1")) {
                        line_echarts.loadUrl("javascript:createChart('createbarlineChart'," + s + ");");
                    } else if (responseCode.equals("2")) {
                        gj_pie.loadUrl("javascript:createChart('pie'," + s + ");");
                    }

                }
            });
        }
    }
}
