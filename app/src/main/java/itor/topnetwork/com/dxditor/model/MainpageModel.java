package itor.topnetwork.com.dxditor.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import itor.topnetwork.com.dxditor.bean.Gjlb;
import itor.topnetwork.com.dxditor.bean.GjxxBean;
import itor.topnetwork.com.dxditor.bean.QjxxBean;
import itor.topnetwork.com.dxditor.bean.SbxxBean;
import itor.topnetwork.com.dxditor.hybrid.bean.total.Bar;
import itor.topnetwork.com.dxditor.hybrid.bean.total.Line;
import itor.topnetwork.com.dxditor.hybrid.bean.total.TotalBean;
import itor.topnetwork.com.dxditor.utils.Constants;
import itor.topnetwork.com.dxditor.utils.ValueCallBack;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by D.Han on 2017/11/15.
 */

public class MainpageModel implements IMainpageModel {
    private ArrayList<Gjlb> listData;
    private OkHttpClient okHttpClient;
    private Gson gson;
    private List<SbxxBean> list;
    private List<GjxxBean> gjxxList;
    private ArrayList<QjxxBean> qjxxList;

    public MainpageModel() {
        okHttpClient = new OkHttpClient();
        this.listData = new ArrayList<Gjlb>();
        gson = new Gson();
    }

    @Override
    public void getTestData(final ValueCallBack<List<SbxxBean>> callBack) {
        list = new ArrayList<SbxxBean>();
        if (!Constants.testData) {
            list.add(new SbxxBean(0, "", "3"));
            list.add(new SbxxBean(1, "", "57"));
            list.add(new SbxxBean(2, "", "31"));
            callBack.onSuccess(list);
        } else {
            Request request = new Request
                    .Builder()
                    .url(Constants.getAppDeviceStausCount)
                    .build();

            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String res = response.body().string();
                   System.out.println("getAppDeviceStausCount:" + res);

                    try {
                        JSONObject js = new JSONObject(res);
                        if (js.getBoolean("success")) {
                            list = gson.fromJson(js.getString("data"), new TypeToken<ArrayList<SbxxBean>>() {
                            }.getType());
                            callBack.onSuccess(list);
                        } else {
                            callBack.onFail("01");
                        }

                    } catch (Exception e) {
                        callBack.onFail("01");
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    @Override
    public void getGjxxData(final ValueCallBack<List<GjxxBean>> callBack) {
        gjxxList = new ArrayList<GjxxBean>();
        if (Constants.testData) {
            gjxxList.add(new GjxxBean(1, "已恢复", 11, 0.27f));
            gjxxList.add(new GjxxBean(2, "已处理", 19, 0.46f));
            gjxxList.add(new GjxxBean(0, "告警中", 11, 0.27f));
            callBack.onSuccess(gjxxList);
        } else {
            Request request = new Request
                    .Builder()
                    .url(Constants.getAppDeviceAlarmProportion)
                    .build();

            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String res = response.body().string();
                    System.out.println("getAppDeviceAlarmProportion:" + res);

                    try {
                        JSONObject js = new JSONObject(res);
                        if (js.getBoolean("success")) {
                            gjxxList = gson.fromJson(js.getString("data"), new TypeToken<ArrayList<GjxxBean>>() {
                            }.getType());
                            callBack.onSuccess(gjxxList);
                        } else {
                            callBack.onFail("01");
                        }

                    } catch (Exception e) {
                        callBack.onFail("01");
                        e.printStackTrace();
                    }
                }
            });


        }
    }

    //全局信息
    @Override
    public void getQjxxData(final ValueCallBack<String> callBack) {
        qjxxList = new ArrayList<QjxxBean>();
        if (Constants.testData) {
            Request request = new Request
                    .Builder()
                    .url(Constants.getAppDeviceCount)
                    .build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String res = response.body().string();
                    System.out.println("getAppDeviceCount:" + res);

                    try {
                        JSONObject js = new JSONObject(res);
                        if (js.getBoolean("success")) {

                           /* QjxxBean qjxxbean = gson.fromJson(js.getString("data"), new TypeToken<QjxxBean>() {
                            }.getType());*/
                            QjxxBean qjxxbean = gson.fromJson(js.getString("data"),QjxxBean.class);
                            TotalBean totalBean = new TotalBean();
                            ArrayList<String> legendList = new ArrayList<String>();
                            for (int i = 0; i < qjxxbean.getIotrappdeviceList().size(); i++) {
                                if (!legendList.contains(qjxxbean.getIotrappdeviceList().get(i).getTypeCode())) {
                                    legendList.add(qjxxbean.getIotrappdeviceList().get(i).getTypeCode());
                                }
                            }
                            legendList.add("告警数");
                            String[] arrayt =new String[legendList.size()];
                            totalBean.legendData = legendList.toArray(arrayt);

                            ArrayList<String> xData = new ArrayList<String>();
                            for (int i = 0; i < qjxxbean.getIotrappdeviceList().size(); i++) {
                                if (!xData.contains(qjxxbean.getIotrappdeviceList().get(i).getLineName())) {
                                    xData.add(qjxxbean.getIotrappdeviceList().get(i).getLineName());
                                }
                            }
                            String[] arrayp =new String[xData.size()];
                            totalBean.xData =  xData.toArray(arrayp);

                            totalBean.series = new ArrayList<>();

                            for (int i = 0; i < totalBean.legendData.length - 1; i++) {
                                Bar bar = new Bar();
                                bar.name = totalBean.legendData[i];
                                bar.type = "bar";
                                bar.yAxisIndex = 0;
                                bar.stack = "a";
                                int[] d = new int[totalBean.xData.length];
                                for (int j = 0; j < totalBean.xData.length; j++) {
                                    for (QjxxBean.Qjxx q : qjxxbean.getIotrappdeviceList()) {
                                        if (q.getTypeCode().equals(totalBean.legendData[i]) && q.getLineName().equals(totalBean.xData[j])) {
                                            d[j] = q.getCount();
                                        }
                                    }

                                }
                                bar.data = d;
                                totalBean.series.add(bar);
                            }
                            Line line = new Line();
                            line.name = "告警数";
                            line.type = "line";
                            line.yAxisIndex = 1;
                            int[] gjs = new int[totalBean.xData.length];
                            for(int j = 0; j < totalBean.xData.length; j++){
                                for (QjxxBean.GjCountBean g : qjxxbean.getLineAlarmConutList()) {
                                    if(g.getLineName().equals(totalBean.xData[j])){
                                        gjs[j]=g.getAlarmCount();
                                    }
                                }
                            }
                            line.data =gjs;
                            totalBean.series.add(line);


                            callBack.onSuccess(gson.toJson(totalBean));
                        } else {
                            callBack.onFail("01");
                        }

                    } catch (Exception e) {
                        callBack.onFail("01");
                        e.printStackTrace();
                    }
                }
            });

        }
    }

    @Override
    public void getXtpfData(ValueCallBack<List<GjxxBean>> callBack) {
       /* List<GjxxBean> list = new ArrayList<>();
        list.add(new GjxxBean("在线率占比", 10));
        list.add(new GjxxBean("告警率占比", 20));
        list.add(new GjxxBean("预警率占比", 30));
        list.add(new GjxxBean("突增率占比", 40));

        callBack.onSuccess(list);*/
    }

    @Override
    public ArrayList<Gjlb> getgjlblist() {
        return listData;
    }

    @Override
    public void getgjlbData(ValueCallBack<ArrayList<Gjlb>> callBack) {
        ArrayList<Gjlb> list = new ArrayList<>();
        list.add(new Gjlb("京沪", "K101+345", "线夹", "4601400000000024", "20.5", "2017-11-24 09:23:35"));
        list.add(new Gjlb("京广", "K564+345", "线夹", "4601400000000025", "45.5", "2017-11-24 09:23:35"));
        list.add(new Gjlb("京哈", "K43+345", "线夹", "4601400000000026", "36.5", "2017-11-24 09:23:35"));
        list.add(new Gjlb("京九", "K458+345", "线夹", "4601400000000027", "31.5", "2017-11-24 09:23:35"));
        list.add(new Gjlb("京九", "K458+345", "线夹", "4601400000000027", "31.5", "2017-11-24 09:23:35"));
        list.add(new Gjlb("京九", "K458+345", "线夹", "4601400000000027", "31.5", "2017-11-24 09:23:35"));
        list.add(new Gjlb("京九", "K458+345", "线夹", "4601400000000027", "31.5", "2017-11-24 09:23:35"));
        list.add(new Gjlb("京九", "K458+345", "线夹", "4601400000000027", "31.5", "2017-11-24 09:23:35"));

        callBack.onSuccess(list);
    }
}
