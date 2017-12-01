package itor.topnetwork.com.dxditor.activitys;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.CoordinateConverter;
import com.amap.api.maps.CoordinateConverter.CoordType;
import com.amap.api.maps.MapView;
import com.amap.api.maps.Projection;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import itor.topnetwork.com.dxditor.R;
import itor.topnetwork.com.dxditor.bean.Device;
import itor.topnetwork.com.dxditor.presenter.BasePresenter;
import itor.topnetwork.com.dxditor.utils.MyUtils;

/**
 * 高德地图
 * Created by D.Han on 2017/11/27.
 */

public class MapPageActivity extends BaseActivity implements AMap.OnMarkerClickListener {
    private MapView mapView;
    private AMap aMap;
    private MarkerOptions markerOption;
    private Marker marker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        /*aMap.moveCamera(CameraUpdateFactory.newLatLngBoundsRect(new LatLngBounds(new LatLng(34,120),new LatLng(31,118)),0,0,0,0));
        UiSettings aMapsetter  =aMap.getUiSettings();
        aMapsetter.setCompassEnabled(true);*/
       /* setMapCustomStyleFile(this);
        //该方法在AMap类中提供
        aMap.setMapCustomEnable(true);//true 开启; false 关闭*/

        initGPSData();

    }

    private void initGPSData() {
        try {
            InputStream is = MapPageActivity.this.getAssets().open("devicetestdata.txt");
            String jsonString = MyUtils.inputStream2String(is);
            JSONObject js = new JSONObject(jsonString);
            String re = js.getString("devices");
            Gson gson =new Gson();
            List<Device> devices = gson.fromJson(re, new TypeToken<List<Device>>() {
            }.getType());
            for(int i=0;i<devices.size();i++){
                LatLng latlng=convert(MapPageActivity.this,devices.get(i).getLocationy()+"",devices.get(i).getLocationx()+"");
                addMarkersToMap(latlng,devices.get(i));
            }
            aMap.setOnMarkerClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * 对marker标注点点击响应事件
     */
    @Override
    public boolean onMarkerClick(final Marker marker) {

        if (aMap != null) {
            jumpPoint(marker);
        }
        Toast.makeText(MapPageActivity.this, marker.getSnippet(), Toast.LENGTH_LONG).show();
        return true;
    }

    /**
     * marker点击时跳动一下
     */
    public void jumpPoint(final Marker marker) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection proj = aMap.getProjection();
        final LatLng markerLatlng = marker.getPosition();
        Point markerPoint = proj.toScreenLocation(markerLatlng);
        markerPoint.offset(0, -100);
        final LatLng startLatLng = proj.fromScreenLocation(markerPoint);
        final long duration = 1500;

        final Interpolator interpolator = new BounceInterpolator();
        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed
                        / duration);
                double lng = t * markerLatlng.longitude + (1 - t)
                        * startLatLng.longitude;
                double lat = t * markerLatlng.latitude + (1 - t)
                        * startLatLng.latitude;
                marker.setPosition(new LatLng(lat, lng));
                if (t < 1.0) {
                    handler.postDelayed(this, 16);
                }
            }
        });
    }
    /**
     * 在地图上添加marker
     */
    private void addMarkersToMap(LatLng latlng,Device device) {
        markerOption = new MarkerOptions().icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .position(latlng)
                /*.title("标题")*/
                .snippet("设备编码:"+device.getCode()+"\n业务名称:"+device.getBusinessName()+"\n线路名称:"+device.getLineName()+"\n公里标:"+device.getDk())
                .draggable(true);

        marker = aMap.addMarker(markerOption);
       // marker.showInfoWindow();

    }

    /**
     * 高德坐标转换
     *
     * @param context
     * @param latStr  纬度
     * @param lngStr  经度
     * @return
     */
    private LatLng convert(Context context, String latStr, String lngStr) {
        if (!"".equals(latStr) && latStr != null && !"".equals(lngStr) && lngStr != null) {
            double lat = 0.0;
            double lon = 0.0;
            lat = Double.parseDouble(latStr);
            lon = Double.parseDouble(lngStr);

            CoordinateConverter converter = new CoordinateConverter(context);
            // CoordType.GPS 待转换坐标类型
            converter.from(CoordType.GPS);
            // sourceLatLng待转换坐标点
            converter.coord(new LatLng(lat, lon));
            // 执行转换操作
            LatLng desLatLng = converter.convert();
            return desLatLng;
        } else {
            Toast.makeText(MapPageActivity.this, "GPS数据错误", Toast.LENGTH_LONG).show();
            return null;
        }
    }

    /**
     * 读取
     * 自定义高德地图文件
     *
     * @param context
     */
    private void setMapCustomStyleFile(Context context) {
        String styleName = "style_gd.data";
        FileOutputStream outputStream = null;
        InputStream inputStream = null;
        String filePath = null;
        try {
            inputStream = context.getAssets().open(styleName);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);

            filePath = context.getFilesDir().getAbsolutePath();
            File file = new File(filePath + "/" + styleName);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            outputStream.write(b);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();

                if (outputStream != null)
                    outputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        aMap.setCustomMapStylePath(filePath + "/" + styleName);

        aMap.showMapText(false);

    }

    @Override
    BasePresenter initPresent() {
        return null;
    }

    @Override
    int getLayout() {
        return R.layout.mapview_activity;
    }

    @Override
    void initView() {
        mapView = (MapView) findViewById(R.id.map);

    }

    @Override
    void onPrepare() {

    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        LatLng llC = new LatLng(35, 115);
        LatLng llD = new LatLng(30, 120);
        LatLngBounds bounds = new LatLngBounds.Builder().include(llC).include(llD).build();
        aMap.moveCamera(CameraUpdateFactory.newLatLngBoundsRect(bounds,10,10,10,10));
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

}
