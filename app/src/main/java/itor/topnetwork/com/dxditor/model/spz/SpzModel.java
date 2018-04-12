package itor.topnetwork.com.dxditor.model.spz;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import itor.topnetwork.com.dxditor.bean.SpzTrend;
import itor.topnetwork.com.dxditor.bean.SpzWarning;
import itor.topnetwork.com.dxditor.hybrid.bean.spz.SpzEchartsBean;
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
 * @Created by D.Han on 2018/3/27 15:01 in Peking.
 */

public class SpzModel implements ISpzModel {
    private final Gson gson;
    private final OkHttpClient okHttpClient;

    private ArrayList<SpzWarning> spzWarnings;
    private ArrayList<SpzTrend> spzTrends;

    public SpzModel() {
        okHttpClient = new OkHttpClient();
        gson = new Gson();
        spzWarnings = new ArrayList<SpzWarning>();
    }

    public ArrayList<SpzWarning> getWarningList() {
        return spzWarnings;
    }

    @Override
    public void getWarningData(final ValueCallBack<ArrayList<SpzWarning>> callBack) {
        if (Constants.testData) {
            ArrayList<SpzWarning> temp = new ArrayList<SpzWarning>();
            temp.add(new SpzWarning("", "京津线03号桥梁声屏障（2米处）声屏障位移", "2018-04-12 09:09:00", "轻风  1.6-3.5 m/s", "北风"));
            temp.add(new SpzWarning("", "丰沙线11号桥梁声屏障（0.9米处）声屏障位移", "2018-04-11 21:58:00", "强风  10-11.6 m/s", "东北风"));
            temp.add(new SpzWarning("", "京包线19号桥梁声屏障（2.4米处）声屏障位移", "2018-04-10 19:28:00", "微风  0.6-1.5 m/s", "西北风"));
            temp.add(new SpzWarning("", "京包线19号桥梁声屏障（2.4米处）声屏障位移", "2018-04-10 19:28:00", "微风  0.6-1.5 m/s", "西北风"));
            spzWarnings = temp;
            callBack.onSuccess(spzWarnings);
        } else {
        /*//Form表单格式的参数传递
        FormBody formBody = new FormBody
                .Builder()
                .add("bridgeCode", Constants.BRIDGECODE)//设置参数名称和参数值
                .build();*/
            Request request = new Request
                    .Builder()
                    //  .post(formBody)
                    .url(Constants.getAppSoundBarrierNewOneAlarm)
                    .build();

            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String res = response.body().string();
                    System.out.println("getAppSoundBarrierNewOneAlarm:" + res);

                    try {
                        JSONObject js = new JSONObject(res);
                        if (js.getBoolean("success")) {
                            spzWarnings = gson.fromJson(js.getString("data"), new TypeToken<ArrayList<SpzWarning>>() {
                            }.getType());
                            callBack.onSuccess(spzWarnings);
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
    public void getTrendData(final ValueCallBack<String> callBack, int position) {
        spzTrends = new ArrayList<SpzTrend>();
        if (Constants.testData) {
            SpzEchartsBean spzBean = new SpzEchartsBean();
            if (position == 0) {
                for (int i = 0; i < 9; i++) {
                    spzTrends.add(new SpzTrend(Float.parseFloat("10" + i), "03-2" + i + " 08:00"));
                }
            } else if (position == 1) {
                for (int i = 0; i < 9; i++) {
                    spzTrends.add(new SpzTrend(Float.parseFloat("8" + i), "03-2" + i + " 08:00"));
                }
            } else if (position == 2) {
                for (int i = 0; i < 9; i++) {
                    spzTrends.add(new SpzTrend(Float.parseFloat("7" + i), "03-2" + i + " 08:00"));
                }
            }else if (position == 3) {
                for (int i = 0; i < 9; i++) {
                    spzTrends.add(new SpzTrend(Float.parseFloat("8" + i), "03-2" + i + " 08:00"));
                }
            }
            List<String> time = new ArrayList<>();
            for (int i = 0; i < spzTrends.size(); i++) {
                time.add(spzTrends.get(i).getStorageTime());
            }
            spzBean.xData = time;
            List<Float> lineDatas = new ArrayList<>();
            for (int i = 0; i < spzTrends.size(); i++) {
                lineDatas.add(spzTrends.get(i).getTypeData());
            }
            spzBean.seriesData = lineDatas;


            callBack.onSuccess(gson.toJson(spzBean));

        } else {
            //Form表单格式的参数传递
            FormBody formBody = new FormBody
                    .Builder()
                    .add("bridgeCode", spzWarnings.get(position).getBridgeCode())
                    .add("type", spzWarnings.get(position).getType())
                    .add("storageTime", Constants.MONTH)
                    .build();
            Request request = new Request
                    .Builder()
                    .post(formBody)
                    .url(Constants.getAppSoundBarrierInfo)
                    .build();

            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String res = response.body().string();
                    System.out.println("getAppSoundBarrierInfo:" + res);

                    try {
                        JSONObject js = new JSONObject(res);
                        if (js.getBoolean("success")) {
                            spzTrends = gson.fromJson(js.getString("data"), new TypeToken<ArrayList<SpzTrend>>() {
                            }.getType());
                            SpzEchartsBean spzBean = new SpzEchartsBean();
                            //bridgeBean.initValue =;
                            List<String> time = new ArrayList<>();
                            for (int i = 0; i < spzTrends.size(); i++) {
                                time.add(spzTrends.get(i).getStorageTime());
                            }
                            spzBean.xData = time;

                            List<Float> lineDatas = new ArrayList<>();
                            for (int i = 0; i < spzTrends.size(); i++) {
                                lineDatas.add(spzTrends.get(i).getTypeData());
                            }
                            spzBean.seriesData = lineDatas;


                            callBack.onSuccess(gson.toJson(spzBean));
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
