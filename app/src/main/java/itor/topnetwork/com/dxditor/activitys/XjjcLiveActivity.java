package itor.topnetwork.com.dxditor.activitys;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import itor.topnetwork.com.dxditor.R;
import itor.topnetwork.com.dxditor.bean.Pv;
import itor.topnetwork.com.dxditor.bean.XjBean;
import itor.topnetwork.com.dxditor.presenter.xj.XjjcPresenter;
import itor.topnetwork.com.dxditor.view.xj.IXjjcView;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * 线夹监测Activity
 * Created by D.Han on 2017/12/6.
 */

public class XjjcLiveActivity extends BaseActivity<XjjcPresenter> implements IXjjcView {
    private LineChartView xj_ChartView;
    String mText;
    public static String PARAM_KEY_TEXT = "param_key_text";

    private Axis axisX; // X轴
    private Axis axisY; // Y轴

    private LineChartData lineChartData; // 折线图显示的数据（包括坐标上的点）
    private List<Line> linesList;
    private List<PointValue> pointValueList;
    int i = 0;
    private TextView title;
    private String sbid;


    @Override
    public XjjcPresenter initPresent() {
        return new XjjcPresenter(this);
    }

    @Override
    public int getLayout() {
        sbid = getIntent().getStringExtra("sbid");
        return R.layout.xjjc_layout;
    }

    @Override
    public void initView() {
        title = (TextView) findViewById(R.id.title);
        if (!TextUtils.isEmpty(sbid)) {
            title.setText(sbid + "温度实时监控");
        } else {
            title.setText("00000" + "温度实时监控");
        }
        xj_ChartView = (LineChartView) findViewById(R.id.xj_chart);
        initAxisView();
    }

    @Override
    public void onPrepare() {
        basepresenter.initData();
    }

    /**
     * 初始化显示坐标轴
     */
    private void initAxisView() {

            pointValueList = new ArrayList<PointValue>();

        linesList = new ArrayList<Line>();
        /** 初始化Y轴 */
        axisY = new Axis();
        axisY.setName("温度（单位：℃）"); // 添加Y轴的名称
        axisY.setHasLines(true); // Y轴分割线
        axisY.setTextSize(10); // 设置字体大小
        // axisY.setTextColor(Color.parseColor("#AFEEEE")); //设置Y轴颜色，默认浅灰色
        lineChartData = new LineChartData(linesList);
        lineChartData.setAxisYLeft(axisY); // 设置Y轴在左边

        /** 初始化X轴 */
        axisX = new Axis();
        axisX.setHasTiltedLabels(false); // X坐标轴字体是斜的显示还是直的，true是斜的显示
        // axisX.setTextColor(Color.CYAN); //设置X轴颜色
        axisX.setName("时间（单位：s）"); // X轴名称
        axisX.setHasLines(true); // X轴分割线
        axisX.setTextSize(10); // 设置字体大小
        axisX.setMaxLabelChars(0); // 设置0的话X轴坐标值就间隔为1
        List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();
        for (int i = 0; i < 10000; i++) {
            //间隔5秒
            //mAxisXValues.add(new AxisValue(i).setLabel(i * 5 + ""));
            //间隔1秒
            mAxisXValues.add(new AxisValue(i).setLabel(i + ""));
        }
        axisX.setValues(mAxisXValues); // 填充X轴的坐标名称
        lineChartData.setAxisXBottom(axisX); // X轴在底部

        xj_ChartView.setLineChartData(lineChartData);

        Viewport port = initViewPort(0, 10); // 初始化X轴10个间隔坐标
        xj_ChartView.setCurrentViewportWithAnimation(port);
        xj_ChartView.setInteractive(false); // 设置不可交互
        xj_ChartView.setScrollEnabled(true);
        xj_ChartView.setValueTouchEnabled(false);
        xj_ChartView.setFocusableInTouchMode(false);
        xj_ChartView.setViewportCalculationEnabled(false);
        xj_ChartView.setContainerScrollEnabled(true,
                ContainerScrollType.HORIZONTAL);
        xj_ChartView.startDataAnimation();
    }

    private Viewport initViewPort(float left, float right) {
        Viewport port = new Viewport();
        port.top = 150; // Y轴上限，固定(不固定上下限的话，Y轴坐标值可自适应变化)
        port.bottom = 0; // Y轴下限，固定
        port.left = left; // X轴左边界，变化
        port.right = right; // X轴右边界，变化
        return port;
    }

    @Override
    public void refreshData(XjBean xjBeans) {
        addpoint(Integer.parseInt(xjBeans.getTem()));
        i++;
    }

    private void addpoint(int point) {
        PointValue pointValuetemp = new PointValue(i, point);
        pointValueList.add(pointValuetemp); // 实时添加新的点
        if (pointValueList.size() > 11) {
            pointValueList.remove(0);
        }
        // System.out.println(i);
        // 根据新的点的集合画出新的线
        Line line = new Line(pointValueList);
        line.setColor(Color.parseColor("#017ED1")); // 设置折线颜色
        line.setShape(ValueShape.CIRCLE); // 设置折线图上数据点形状为 圆形 （共有三种
        // ：ValueShape.SQUARE
        // ValueShape.CIRCLE
        // ValueShape.DIAMOND）
        line.setCubic(true); // 曲线是否平滑，true是平滑曲线，false是折线
        line.setHasLabels(true); // 数据是否有标注
        // line.setHasLabelsOnlyForSelected(true);
        // //点击数据坐标提示数据,设置了line.setHasLabels(true);之后点击无效
        line.setHasLines(true); // 是否用线显示，如果为false则没有曲线只有点显示
        line.setHasPoints(true); // 是否显示圆点 ，如果为false则没有原点只有点显示（每个数据点都是个大圆点）
        linesList.clear();
        linesList.add(line);

        // System.out.println("linesList:"+linesList.size());
        // lineChartData.setLines(new ArrayList<Line>(linesList));
        lineChartData = new LineChartData(linesList);
        lineChartData.setAxisYLeft(axisY); // 设置Y轴在左
        lineChartData.setAxisXBottom(axisX); // X轴在底部
        xj_ChartView.setLineChartData(lineChartData);

        float xAxisValue = pointValuetemp.getX();
        // 根据点的横坐标实时变换X坐标轴的视图范围
        Viewport port;
        if (xAxisValue > 10) {
            port = initViewPort(xAxisValue - 10, xAxisValue);
        } else {
            port = initViewPort(0, 10);
        }
        xj_ChartView.setMaximumViewport(port);
        xj_ChartView.setCurrentViewport(port);

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("i", i);
        if(pointValueList.size()>0){
            ArrayList<Pv> pvs = new ArrayList<>();
            for(int i=0;i<pointValueList.size();i++){
                Pv pv = new Pv();
                pv.set(pointValueList.get(i).getX(),pointValueList.get(i).getY());
                pvs.add(pv);
            }
            savedInstanceState.putSerializable("pvs",pvs);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        i = savedInstanceState.getInt("i");
        ArrayList<Pv> pvs=(ArrayList<Pv>)savedInstanceState.getSerializable("pvs");
        for(int i=0;i<pvs.size();i++){
            PointValue pointValue = new PointValue();
            pointValue.set(pvs.get(i).getX(),pvs.get(i).getY());
            pointValueList.add(pointValue);
        }
    }
}
