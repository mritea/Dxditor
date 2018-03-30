package itor.topnetwork.com.dxditor.model.bridge;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import itor.topnetwork.com.dxditor.bean.BridgeTrend;
import itor.topnetwork.com.dxditor.bean.BridgeWarning;
import itor.topnetwork.com.dxditor.hybrid.bean.bridge.BridgeBean;
import itor.topnetwork.com.dxditor.utils.Constants;
import itor.topnetwork.com.dxditor.utils.ValueCallBack;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Description:
 * @Created by D.Han on 2018/3/23 13:52 in Peking.
 */

public class BridgeModel implements IBridgeModel {

    private ArrayList<BridgeWarning> bridgeWarnings;
    private ArrayList<BridgeTrend> bridgeTrends;
    private final OkHttpClient okHttpClient;
    private final Gson gson;

    public BridgeModel() {
        okHttpClient = new OkHttpClient();
        bridgeWarnings = new ArrayList<BridgeWarning>();
        gson = new Gson();
    }

    public ArrayList<BridgeWarning> getWarningList() {
        return bridgeWarnings;
    }

    @Override
    public void getWarningData(final ValueCallBack<ArrayList<BridgeWarning>> callBack) {
        if (Constants.testData) {
            ArrayList<BridgeWarning> temp= new ArrayList<BridgeWarning>();
            temp.add(new BridgeWarning("京津线39号桥梁(中间)梁体应力", null, "3.5N", "2.3N", null, "1", null, null));
            temp.add(new BridgeWarning("京张铁路18号桥梁(中间)梁体裂缝宽度", null, "1.2cm", "1.1cm", null, "2", null, null));
            temp.add(new BridgeWarning("京沪线75号桥梁梁体横向位移", null, "0.6cm", "0.9cm", null, "3", null, null));
            bridgeWarnings=temp;
            callBack.onSuccess(bridgeWarnings);
        } else {
            FormBody formBody = new FormBody
                    .Builder()
                    .add("bridgeCode", Constants.BRIDGECODE)//设置参数名称和参数值
                    .build();
            Request request = new Request
                    .Builder()
                    .post(formBody)
                    .url(Constants.getAppBridgeNewOneAlarm)
                    .build();

            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String res = response.body().string();
                    // System.out.println("getWarningData:" + res);

                    try {
                        JSONObject js = new JSONObject(res);
                        if (js.getBoolean("success")) {
                            bridgeWarnings = gson.fromJson(js.getString("data"), new TypeToken<ArrayList<BridgeWarning>>() {
                            }.getType());
                            callBack.onSuccess(bridgeWarnings);
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
    public void getTrendData(final ValueCallBack<String> callBack, final int position) {
        if (Constants.testData) {
            BridgeBean bridgeBean = new BridgeBean();

            ArrayList<String> x = new ArrayList<String>();
            for (int i = 0; i < 8; i++) {
                x.add("03-2" + i + " 08:00");
            }
            bridgeBean.xData = x;
            ArrayList<Float> f = new ArrayList<Float>();
            if (position == 0) {
                for (int i = 0; i < 8; i++) {
                    f.add(Float.parseFloat("2." + i));
                }
            } else if (position == 1) {
                for (int i = 0; i < 8; i++) {
                    f.add(Float.parseFloat("1.0" + i));
                }
                ;
            } else if (position == 2) {
                for (int i = 1; i < 9; i++) {
                    f.add(Float.parseFloat("0.8" + i));
                }
            }
            bridgeBean.seriesData = f;
            if (position == 0) {
                bridgeBean.initValue = 2.3f;
            } else if (position == 1) {
                bridgeBean.initValue = 1.1f;
            } else if (position == 2) {
                bridgeBean.initValue = 0.8f;
            }

            callBack.onSuccess(gson.toJson(bridgeBean));


        } else {
            bridgeTrends = new ArrayList<BridgeTrend>();
            //Form表单格式的参数传递
            FormBody formBody = new FormBody
                    .Builder()
                    .add("bridgeCode", bridgeWarnings.get(position).getBridgeCode())
                    .add("monitotObjectCode", bridgeWarnings.get(position).getMonitorObjectCode())
                    .add("monitorItemCode", bridgeWarnings.get(position).getMonitorItemCode())
                    .add("storageTime", Constants.MONTH)
                    .build();
            Request request = new Request
                    .Builder()
                    .post(formBody)
                    .url(Constants.getAppBridgeMonthDisplacementInfo)
                    .build();

            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String res = response.body().string();
                    //System.out.println("bridgeTrends:" + res);

                    try {
                        JSONObject js = new JSONObject(res);
                        if (js.getBoolean("success")) {
                            bridgeTrends = gson.fromJson(js.getString("data"), new TypeToken<ArrayList<BridgeTrend>>() {
                            }.getType());
                            BridgeBean bridgeBean = new BridgeBean();
                            List<String> time = new ArrayList<>();
                            for (int i = 0; i < bridgeTrends.size(); i++) {
                                time.add(bridgeTrends.get(i).getStorageTime());
                            }
                            bridgeBean.xData = time;

                            List<Float> lineDatas = new ArrayList<>();
                            for (int i = 0; i < bridgeTrends.size(); i++) {
                                lineDatas.add(Float.parseFloat(bridgeTrends.get(i).getValue()));
                            }
                            bridgeBean.seriesData = lineDatas;

                            bridgeBean.initValue = Float.parseFloat(bridgeWarnings.get(position).getInitValue());

                            callBack.onSuccess(gson.toJson(bridgeBean));
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

}
