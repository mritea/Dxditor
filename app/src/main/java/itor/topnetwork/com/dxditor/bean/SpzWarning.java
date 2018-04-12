package itor.topnetwork.com.dxditor.bean;

/**
 * @Description:声屏障
 * @Created by D.Han on 2018/3/27 15:10 in Peking.
 */

public class SpzWarning {
    private String bridgeCode;
    private String monitorName;
    private String type;
    private String time;
    private String fl;
    private String fx;

    public SpzWarning(String bridgeCode, String monitorName, String time, String fl, String fx) {
        this.bridgeCode = bridgeCode;
        this.monitorName = monitorName;
        this.time = time;
        this.fl = fl;
        this.fx = fx;
    }

    public String getBridgeCode() {
        return bridgeCode;
    }

    public void setBridgeCode(String bridgeCode) {
        this.bridgeCode = bridgeCode;
    }

    public String getMonitorName() {
        return monitorName;
    }

    public void setMonitorName(String monitorName) {
        this.monitorName = monitorName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
