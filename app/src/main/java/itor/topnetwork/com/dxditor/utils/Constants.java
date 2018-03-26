package itor.topnetwork.com.dxditor.utils;

/**
 * @Description:
 * @Created by D.Han on 2018/3/22 11:37 in Peking.
 */

public class Constants {
    public static boolean testDtat=true;

    public static String IP="http://10.1.4.132:8080";
//桥梁趋势
    public static String getAppBridgeMonthDisplacementInfo=IP+"/iotr/iotrApp/iotr/iotrApp/iotrAppDataBridge!getAppBridgeMonthDisplacementInfo.action";
//桥梁告警
    public static String getAppBridgeNewOneAlarm=IP+"/iotr/iotrApp/iotrAppDataBridge!getAppBridgeNewOneAlarm.action";
//坠坨现场测试
    public static String getAppWeightsTrendInfo=IP+"/iotr/iotrApp/iotrAppDataWeights!getAppWeightsTrendInfo.action";
}
