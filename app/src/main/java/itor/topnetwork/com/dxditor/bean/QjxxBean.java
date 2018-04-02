package itor.topnetwork.com.dxditor.bean;

import java.util.ArrayList;

/**
 * @Description: 全局信息实体类
 * @Created by D.Han on 2018/3/30 16:33 in Peking.
 */

public class QjxxBean {
    private ArrayList<Qjxx> iotrappdeviceList;
    private ArrayList<GjCountBean> lineAlarmConutList;

    public ArrayList<Qjxx> getIotrappdeviceList() {
        return iotrappdeviceList;
    }

    public void setIotrappdeviceList(ArrayList<Qjxx> iotrappdeviceList) {
        this.iotrappdeviceList = iotrappdeviceList;
    }

    public ArrayList<GjCountBean> getLineAlarmConutList() {
        return lineAlarmConutList;
    }

    public void setLineAlarmConutList(ArrayList<GjCountBean> lineAlarmConutList) {
        this.lineAlarmConutList = lineAlarmConutList;
    }

    public class Qjxx {
        private String typeCode;
        private String lineName;
        private Integer count;
        private String deviceAlarm;

        public String getTypeCode() {
            return typeCode;
        }

        public void setTypeCode(String typeCode) {
            this.typeCode = typeCode;
        }

        public String getLineName() {
            return lineName;
        }

        public void setLineName(String lineName) {
            this.lineName = lineName;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public String getDeviceAlarm() {
            return deviceAlarm;
        }

        public void setDeviceAlarm(String deviceAlarm) {
            this.deviceAlarm = deviceAlarm;
        }
    }

    public class GjCountBean {
        private String lineName;
        private Integer alarmCount;

        public String getLineName() {
            return lineName;
        }

        public void setLineName(String lineName) {
            this.lineName = lineName;
        }

        public Integer getAlarmCount() {
            return alarmCount;
        }

        public void setAlarmCount(Integer alarmCount) {
            this.alarmCount = alarmCount;
        }
    }
}
