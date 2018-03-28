package itor.topnetwork.com.dxditor.utils;

/**
 * @Description:Url
 * @Created by D.Han on 2018/3/22 11:37 in Peking.
 */

public class Constants {
    public static boolean testDtat = true;

    //public static String IP = "http://36.110.98.226:5280/iotr_product";

     public static String IP = "http://10.1.4.132:8080/iotr";
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
