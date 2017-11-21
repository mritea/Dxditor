package itor.topnetwork.com.dxditor.activitys;

import android.graphics.Color;
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

import java.util.ArrayList;
import java.util.List;

import itor.topnetwork.com.dxditor.R;
import itor.topnetwork.com.dxditor.bean.GjxxBean;
import itor.topnetwork.com.dxditor.bean.SbxxBean;
import itor.topnetwork.com.dxditor.presenter.MainpagePresenter;
import itor.topnetwork.com.dxditor.view.IMainpageView;

/**
 * 物联网Android
 */
public class MainActivity extends BaseActivity<MainpagePresenter> implements IMainpageView{
    private TextView gj, zc, lx;
    private PieChart gjpiechart;

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
    }



    /**
     * 设置告警信息的数据
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

        PieDataSet dataSet = new PieDataSet(entries,"");

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
        basepresenter.initData();
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
}
