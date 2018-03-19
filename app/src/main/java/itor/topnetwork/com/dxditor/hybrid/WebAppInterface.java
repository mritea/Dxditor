package itor.topnetwork.com.dxditor.hybrid;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.github.abel533.echarts.Title;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.SeriesType;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.data.Data;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Pie;

/**
 * @Description:
 * @Created by D.Han on 2018/3/19 19:14 in Peking.
 */

public class WebAppInterface {
    Context mContext;
    ProgressDialog dialog = null;
    private WebView mWebView;

    public WebAppInterface(Context c,WebView v) {
        mContext = c;
        mWebView=v;
        dialog = new ProgressDialog(c);
        dialog.setMessage("请稍后...");
    }


    @JavascriptInterface
    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }


    private void showDialog(){
        dialog.show();
    }

    private void dismissDialog(){
        dialog.dismiss();
    }
    /**
     * PieChart 或者柱状图数据示例
     * @param type 1 - 饼状图数据； 2 - 柱状图数据
     * @return
     */
    @JavascriptInterface
    public String getPieChartOptions(int type) {

        mWebView.post(new Runnable() {
            @Override
            public void run() {
                showDialog();
            }
        });
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //地址：http://echarts.baidu.com/doc/example/pie7.html
        GsonOption option = new GsonOption();
        // 设置标题
        option.title(new Title().text("某站点用户访问来源").subtext("纯属虚构").x(X.center));
        if(type == 2) {
            option.tooltip().trigger(Trigger.item).formatter("{a} <br/>{b} : {c} ({d}%)");
        }else  if(type == 1){
            option.tooltip().trigger(Trigger.item).formatter("{a} <br/>{b} : {c}");
        }
        // 设置图例
        option.legend().data("直接访问", "邮件营销", "联盟广告", "视频广告", "搜索引擎").orient(Orient.vertical).x(X.left);

        // 是否可以拖动以计算
        option.calculable(true);

        if(type == 2){
            // 构造数据
            Pie pie1 = new Pie("访问来源");
            pie1.type(SeriesType.pie).radius("45%").center("50%","60%");
            pie1.data(new Data("直接访问",335),
                    new Data("邮件营销",310),
                    new Data("联盟广告",234),
                    new Data("视频广告",135),
                    new Data("搜索引擎",1548)
            );
            option.series(pie1);
        }else  if(type ==1) {
            // 构建柱状数据
            option.xAxis(new CategoryAxis().name("访问来源").data("视频广告","搜索引擎","联盟广告","邮件营销","直接访问"));
            option.yAxis(new ValueAxis());

            Bar bar = new Bar("访问来源");
            bar.data(11,4,14,45,67,88);
            option.series(bar);
        }
        Log.d("TAG",option.toString());
        mWebView.post(new Runnable() {
            @Override
            public void run() {
                dismissDialog();
            }
        });
        return option.toString();
    }

}
