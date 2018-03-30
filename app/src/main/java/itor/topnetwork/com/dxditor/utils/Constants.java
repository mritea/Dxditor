package itor.topnetwork.com.dxditor.utils;

/**
 * @Description:Url
 * @Created by D.Han on 2018/3/22 11:37 in Peking.
 */

public class Constants {
    public static boolean testData = true;

  public static String IP = "http://36.110.98.226:5280/iotr_product";

    // public static String IP = "http://10.1.4.132:8080/iotr";
    //首页设备信息
    public static String getAppDeviceStausCount = IP + "/iotrApp/iotr/iotrApp/iotrAppDataDevice!getAppDeviceStausCount.action";
    //获取设备告警信息占比
    public static String getAppDeviceAlarmProportion = IP + "/iotrApp/iotr/iotrApp/iotrAppDataDevice!getAppDeviceAlarmProportion.action";
    //获取铁路线中不同设备数量,铁路线告警数
    public static String getAppDeviceCount = IP + "/iotrApp/iotr/iotrApp/iotrAppDataDevice!getAppDeviceCount.action";
    //桥梁趋势
    public static String getAppBridgeMonthDisplacementInfo = IP + "/iotrApp/iotr/iotrApp/iotrAppDataBridge!getAppBridgeMonthDisplacementInfo.action";
    //桥梁告警
    public static String getAppBridgeNewOneAlarm = IP + "/iotrApp/iotrAppDataBridge!getAppBridgeNewOneAlarm.action";
    //坠坨现场测试
    public static String getAppWeightsTrendInfo = IP + "/iotrApp/iotrAppDataWeights!getAppWeightsTrendInfo.action";
    //声屏障监测指标
    public static String getAppSoundBarrierNewOneAlarm = IP + "/iotrApp/iotrAppDataSoundBarrier!getAppSoundBarrierNewOneAlarm.action";
    //声屏障趋势图
    public static String getAppSoundBarrierInfo = IP + "/iotrApp/iotrAppDataSoundBarrier!getAppSoundBarrierInfo.action";

    public static String BRIDGECODE = "ZX0001";

    public static String MONTH = "2018-03";


}
