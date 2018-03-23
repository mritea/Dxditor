package itor.topnetwork.com.dxditor.model.bridge;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import itor.topnetwork.com.dxditor.bean.BridgeTrend;
import itor.topnetwork.com.dxditor.bean.BridgeWarning;
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

        //Form表单格式的参数传递
        FormBody formBody = new FormBody
                .Builder()
                .add("bridgeCode", "ZX0001")//设置参数名称和参数值
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
                String res=response.body().string();
                System.out.println("getWarningData:" + res);

                try {
                    JSONObject js = new JSONObject(res);
                    if(js.getBoolean("success")) {
                        bridgeWarnings=gson.fromJson(js.getString("data"),new TypeToken<ArrayList<BridgeWarning>>(){}.getType());
                        callBack.onSuccess(bridgeWarnings);
                    }else{
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
    public void getTrendData( ValueCallBack<ArrayList<BridgeTrend>> callBack) {
        bridgeTrends = new ArrayList<BridgeTrend>();
        //Form表单格式的参数传递
        FormBody formBody = new FormBody
                .Builder()
                .add("bridgeCode", "ZX0001")
                .add("monitotObjectCode", "000011")
                .add("monitorItemCode", "19")
                .add("storageTime", "2018-03")
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
                System.out.println("bridgeTrends:" + response.body().string());

            }
        });

    }

}
