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
    private BridgeBean bridgeBean;

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

        //Form表单格式的参数传递
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
        //callBack.onSuccess(bridgeWarnings);
    }

    @Override
    public void getTrendData(final ValueCallBack<String> callBack,final int position) {
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
                System.out.println("bridgeTrends:" + res);

                try {
                    JSONObject js = new JSONObject(res);
                    if (js.getBoolean("success")) {
                        bridgeTrends = gson.fromJson(js.getString("data"), new TypeToken<ArrayList<BridgeTrend>>() {
                        }.getType());
                        BridgeBean bridgeBean = new BridgeBean();
                        //bridgeBean.initValue =;
                        List<String> time = new ArrayList<>();
                        for (int i = 0; i < bridgeTrends.size(); i++) {
                            time.add(bridgeTrends.get(i).getStorageTime());
                        }
                        bridgeBean.xData = time;

                        List<Float> lineDatas = new ArrayList<>();
                        for (int i = 0; i < bridgeTrends.size(); i++) {
                            lineDatas.add(Float.parseFloat( bridgeTrends.get(i).getValue()));
                        }
                        bridgeBean.seriesData = lineDatas;

                        bridgeBean.initValue=Integer.parseInt(bridgeWarnings.get(position).getInitValue());

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
