package itor.topnetwork.com.dxditor.activitys;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
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
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import itor.topnetwork.com.dxditor.R;
import itor.topnetwork.com.dxditor.adapter.GjAdapter;
import itor.topnetwork.com.dxditor.bean.GjxxBean;
import itor.topnetwork.com.dxditor.bean.SbxxBean;
import itor.topnetwork.com.dxditor.presenter.MainpagePresenter;
import itor.topnetwork.com.dxditor.view.IMainpageView;

/**
 * 物联网Android
 */
public class MainActivity extends BaseActivity<MainpagePresenter> implements IMainpageView {
    private TextView gj, zc, lx;
    private PieChart gjpiechart;
    private NavigationView navigationView;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private DrawerLayout main_drawerlayout;
    private GjAdapter gjAdapter;

    @Override
    MainpagePresenter initPresent() {
        return new MainpagePresenter(this);
    }

    @Override
    int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    void initView() {
        gj = (TextView) findViewById(R.id.gj);
        zc = (TextView) findViewById(R.id.zc);
        lx = (TextView) findViewById(R.id.lx);
        //告警信息
        gjpiechart = (PieChart) findViewById(R.id.gjxx);
        initGjxxView();

        main_drawerlayout = findViewById(R.id.main_drawerlayout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        setNavigationMenuLineStyle(navigationView,Color.parseColor("#5D6891"),2);
        Resources resource=(Resources)getBaseContext().getResources();
        ColorStateList csl=(ColorStateList)resource.getColorStateList(R.color.navigation_menu_item_color);
        navigationView.setItemTextColor(csl);
//**设置MenuItem默认选中项**//*
       // navigationView.getMenu().getItem(0).setChecked(false);
        initnavigation();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);


    }

    private void initnavigation() {
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                System.out.println(item.getItemId());
                switch (item.getItemId()) {
                    case R.id.mainpage:
                        main_drawerlayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.gdmap:
                        startActivity(new Intent(MainActivity.this, MapPageActivity.class));
                        main_drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.mine:
                        startActivity(new Intent(MainActivity.this, LoginPageActivity.class));
                        main_drawerlayout.closeDrawer(GravityCompat.START);
                        break;
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


    /**
     * 设置告警信息的数据
     *
     * @param gjxxlist
     */
    @Override
    public void setGjxxData(List<GjxxBean> gjxxlist) {
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < gjxxlist.size(); i++) {
            entries.add(new PieEntry((float) gjxxlist.get(i).getPersent(),
                    gjxxlist.get(i).getName()));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        //26个颜色
        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        gjpiechart.setData(data);

        // undo all highlights
        gjpiechart.highlightValues(null);

        gjpiechart.invalidate();
    }

    @Override
    void onPrepare() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        gjAdapter = new GjAdapter(this, basepresenter.getGjadapterData());
        mRecyclerView.setAdapter(gjAdapter);

        basepresenter.initData();
    }

    @Override
    public GjAdapter getgjAdapter() {
        return gjAdapter;
    }

    /**
     * 设置顶部三个圆的基础数据
     */
    @Override
    public void setBaseData(List<SbxxBean> sbxxBeans) {
        gj.setText(sbxxBeans.get(0).getCount() + "");
        zc.setText(sbxxBeans.get(1).getCount() + "");
        lx.setText(sbxxBeans.get(2).getCount() + "");
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
    public void refreshAdapter() {

    }

    @Override
    public void onEmpty() {

    }

    private void initGjxxView() {

        gjpiechart.setUsePercentValues(true);
        gjpiechart.getDescription().setEnabled(false);
        gjpiechart.setExtraOffsets(5, 10, 5, 5);

        gjpiechart.setDragDecelerationFrictionCoef(0.95f);

        gjpiechart.setCenterText("告警信息");

        gjpiechart.setDrawHoleEnabled(true);
        gjpiechart.setHoleColor(Color.WHITE);

        gjpiechart.setTransparentCircleColor(Color.WHITE);
        gjpiechart.setTransparentCircleAlpha(110);

        gjpiechart.setHoleRadius(58f);
        gjpiechart.setTransparentCircleRadius(61f);

        gjpiechart.setDrawCenterText(true);

        gjpiechart.setRotationAngle(0);
        // enable rotation of the chart by touch
        gjpiechart.setRotationEnabled(true);
        gjpiechart.setHighlightPerTapEnabled(true);

        // gjpiechart.setUnit(" €");
        // gjpiechart.setDrawUnitsInChart(true);

        // add a selection listener点击事件
        //gjpiechart.setOnChartValueSelectedListener(this);


        gjpiechart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        Legend l = gjpiechart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        gjpiechart.setEntryLabelColor(Color.WHITE);
        gjpiechart.setEntryLabelTextSize(12f);
    }

    /**
     * 给 NavigationView 的Menu的分割线 设置高度和颜色
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
}
