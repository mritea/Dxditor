package itor.topnetwork.com.dxditor.hybrid.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import itor.topnetwork.com.dxditor.hybrid.bean.bridge.BridgeBean;
import itor.topnetwork.com.dxditor.hybrid.bean.spz.SpzEchartsBean;
import itor.topnetwork.com.dxditor.hybrid.bean.total.Bar;
import itor.topnetwork.com.dxditor.hybrid.bean.total.Line;
import itor.topnetwork.com.dxditor.hybrid.bean.total.TotalBean;
import itor.topnetwork.com.dxditor.hybrid.bean.zt.ZtLiveBean;
import itor.topnetwork.com.dxditor.hybrid.bean.zt.ZtLiveEchartsBean;
import itor.topnetwork.com.dxditor.hybrid.bean.zt.ZtTopEchartsBean;
import itor.topnetwork.com.dxditor.utils.Constants;
import itor.topnetwork.com.dxditor.view.zt.EchartsrefreshInterface;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dell1 on 2017/5/29.
 */
public class EchartsDataBean {

    private static Gson gson;
    private static EchartsLineBean lineBean;
    private static EchartsBarBean barBean;
    private static EchartsPieBean pieBean;
    private static EchartsDataBean echartsDataBean;
    private static TotalBean totalBean;
    private static BridgeBean bridgeBean;
    private static SpzEchartsBean spzEchartsBean;
    private static ZtTopEchartsBean ztTopEchartsBean;
    private static ZtLiveEchartsBean ztLiveEchartsBean;
    private static OkHttpClient okHttpClient;
    private EchartsrefreshInterface ei;

    private EchartsDataBean() {
    }

    public EchartsDataBean(EchartsrefreshInterface ei) {
        this.ei = ei;
        ztLiveEchartsBean = new ZtLiveEchartsBean();
    }

    public synchronized static EchartsDataBean getInstance() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
            echartsDataBean = new EchartsDataBean();
            gson = new Gson();
            lineBean = new EchartsLineBean();
            barBean = new EchartsBarBean();
            pieBean = new EchartsPieBean();
            totalBean = new TotalBean();
            bridgeBean = new BridgeBean();
            spzEchartsBean = new SpzEchartsBean();
            ztTopEchartsBean = new ZtTopEchartsBean();

        }
        return echartsDataBean;
    }

    public String getEchartsLineJson() {

        lineBean.title = "运动步数统计";
        lineBean.type = "line";
        lineBean.times =
                new String[]{"8:00", "8:10", "8:20", "8:30", "8:40", "8:50", "9:00", "9:10",
                        "9:20", "9:30", "9:40", "9:50", "10:00", "10:10", "10:20", "10:30",
                        "10:40", "10:50", "11:00", "11:10", "11:20", "11:30", "11:40", "11:50",
                        "12:00", "12:10", "12:20", "12:30", "12:40", "12:50", "13:00", "13:10"};
        lineBean.steps =
                new int[]{4, 40, 4, 40, 45, 5, 57, 7, 88, 1, 16, 1, 16, 10, 0, 0, 34, 5, 34, 54, 43,
                        40, 4, 40, 6, 60, 6, 90, 13, 16, 4, 5};

        return gson.toJson(lineBean);
    }

    public String getEchartsBarJson() {
        barBean.title = "上海市蒸发量和降水量";
        barBean.type1 = "蒸发量";
        barBean.type2 = "降水量";
        barBean.times = new String[]{
                "1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"
        };
        barBean.data1 = new float[]{
                2.0f, 4.9f, 7.0f, 23.2f, 25.6f, 76.7f, 135.6f, 162.2f, 32.6f, 20.0f, 6.4f, 3.3f
        };
        barBean.data2 = new float[]{
                2.6f, 5.9f, 9.0f, 26.4f, 28.7f, 70.7f, 175.6f, 182.2f, 48.7f, 18.8f, 6.0f, 2.3f
        };
        return gson.toJson(barBean);
    }

    public String getEchartsPieJson() {
        pieBean.title = "支付宝用户访问来源";
        pieBean.type = "pie";
        pieBean.names = new String[]{"直接访问", "邮件营销", "联盟广告", "视频广告", "搜索引擎"};
        pieBean.values = new ArrayList<>();
        EchartsPieBean.Value value;
        for (int i = 0; i < pieBean.names.length; i++) {
            value = new EchartsPieBean.Value();
            value.name = pieBean.names[i];
            value.value = 20 * i;
            pieBean.values.add(value);
        }
        return gson.toJson(pieBean);
    }

    final Random random = new Random();

    public String getEchartsTotalJson() {
        if (Constants.testDtat) {
            totalBean.legendData = new String[]{"桥梁", "声屏障", "坠坨", "线夹", "轨道板", "损坏量"};
            totalBean.xData = new String[]{"京广线", "京沪线", "京九线", "京津线", "京哈线", "京张铁路", "京通线", "张集线"};
            totalBean.series = new ArrayList<>();

            for (int i = 0; i < totalBean.legendData.length - 1; i++) {
                Bar bar = new Bar();
                bar.name = totalBean.legendData[i];
                bar.type = "bar";
                bar.yAxisIndex = 0;
                bar.stack = "a";
                int[] d = new int[totalBean.xData.length];
                for (int j = 0; j < totalBean.xData.length; j++) {
                    d[j] = random.nextInt(150) + 1;

                }
                bar.data = d;
                totalBean.series.add(bar);
            }
            Line line = new Line();
            line.name = "损坏量";
            line.type = "line";
            line.yAxisIndex = 1;
            line.data = new int[]{23, 34, 42, 24, 32, 23, 23, 23};
            totalBean.series.add(line);
        }
        return gson.toJson(totalBean);

    }

    /*public String bridgeEcharts() {
        if (Constants.testDtat) {
            bridgeBean.xData = new String[]{"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};
            List<Integer> seriesData = new ArrayList<>();
            for (int i = 0; i < bridgeBean.xData.length; i++) {
                seriesData.add(random.nextInt(100) + 1);
            }
            List<Integer> beginData = new ArrayList<>();
            for (int i = 0; i < bridgeBean.xData.length; i++) {
                beginData.add(seriesData.get(0));
            }
            bridgeBean.seriesData = seriesData;
            bridgeBean.linebegin = beginData;

        }
        return gson.toJson(bridgeBean);

    }*/

    //应力
    /*public String spzYlEcharts() {
        if (Constants.testDtat) {
            spzEchartsBean.xData = new String[]{"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};
            List<Integer> seriesData = new ArrayList<>();
            for (int i = 0; i < spzEchartsBean.xData.length; i++) {
                seriesData.add(random.nextInt(100) + 1);
            }
            spzEchartsBean.seriesData = seriesData;
        }
        return gson.toJson(spzEchartsBean);

    }

    //位移
    public String spzWyEcharts() {
        if (Constants.testDtat) {
            spzEchartsBean.xData = new String[]{"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};
            List<Integer> seriesData = new ArrayList<>();
            for (int i = 0; i < spzEchartsBean.xData.length; i++) {
                seriesData.add(random.nextInt(100) + 1);
            }
            spzEchartsBean.seriesData = seriesData;
        }
        return gson.toJson(spzEchartsBean);

    }*/

    //排名，top20 A值
    public String ztTopEcharts() {
        if (Constants.testDtat) {
            ztTopEchartsBean.xData = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
            List<Integer> seriesData = new ArrayList<>();
            for (int i = 0; i < ztTopEchartsBean.xData.length; i++) {
                seriesData.add(random.nextInt(100) + 1);
            }
            ztTopEchartsBean.seriesData = seriesData;
        }
        return gson.toJson(ztTopEchartsBean);

    }

    //排名，top20 B值
    public String ztTopBEcharts() {
        if (Constants.testDtat) {
            ztTopEchartsBean.xData = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
            List<Integer> seriesData = new ArrayList<>();
            for (int i = 0; i < ztTopEchartsBean.xData.length; i++) {
                seriesData.add(random.nextInt(100) + 1);
            }
            ztTopEchartsBean.seriesData = seriesData;
        }
        return gson.toJson(ztTopEchartsBean);

    }

    public void ztLiveEcharts(String code) {
        FormBody formBody = new FormBody
                .Builder()
                .add("weightsCode", code)//设置参数名称和参数值
                .build();
        Request request = new Request
                .Builder()
                .post(formBody)
                .url(Constants.getAppWeightsTrendInfo)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                //System.out.println("res："+res);
                try {
                    JSONObject js = new JSONObject(res);
                    if (js.getBoolean("success")) {
                        ArrayList<ZtLiveBean> ZtLiveBeans = gson.fromJson(js.getString("data"), new TypeToken<ArrayList<ZtLiveBean>>() {
                        }.getType());
                        System.out.println("ZtLiveBeans.size():"+ZtLiveBeans.size());
                        ArrayList<String> xdata = new ArrayList<String>();
                        for (int i = 0; i < ZtLiveBeans.size(); i++) {
                            String time = ZtLiveBeans.get(i).getTimeStamp().replace("T", " ");
                            xdata.add(time);
                        }
                        ztLiveEchartsBean.xData = xdata;

                        ArrayList<Float> seriesdata = new ArrayList<Float>();
                        for (int i = 0; i < ZtLiveBeans.size(); i++) {
                            Float h = ZtLiveBeans.get(i).getbValue() + ZtLiveBeans.get(i).getWeightsHeight();
                            seriesdata.add(h);
                        }
                        ztLiveEchartsBean.seriesData = seriesdata;

                        ArrayList<Float> aValues = new ArrayList<Float>();
                        for (int i = 0; i < ZtLiveBeans.size(); i++) {
                            aValues.add(ZtLiveBeans.get(i).getaValue());
                        }
                        ztLiveEchartsBean.aValues = aValues;

                        ArrayList<Float> bValues = new ArrayList<Float>();
                        for (int i = 0; i < ZtLiveBeans.size(); i++) {
                            bValues.add(ZtLiveBeans.get(i).getbValue());
                        }
                        ztLiveEchartsBean.bValues = bValues;

                        ArrayList<Integer> temps = new ArrayList<Integer>();
                        for (int i = 0; i < ZtLiveBeans.size(); i++) {
                            temps.add(ZtLiveBeans.get(i).getTemperature());
                        }
                        ztLiveEchartsBean.temps = temps;

                    } else {
                    }
                    ei.refresh(gson.toJson(ztLiveEchartsBean));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
