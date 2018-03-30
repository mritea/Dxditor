package itor.topnetwork.com.dxditor.bean;

/**
 * @Description:声屏障
 * @Created by D.Han on 2018/3/27 15:10 in Peking.
 */

public class SpzWarning {
    private String bridgeCode;
    private String monitorName;
    private String monitorValue;
    private String type;
    private String unit;

    public SpzWarning(String bridgeCode, String monitorName, String monitorValue, String type, String unit) {
        this.bridgeCode = bridgeCode;
        this.monitorName = monitorName;
        this.monitorValue = monitorValue;
        this.type = type;
        this.unit = unit;
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

    public String getMonitorValue() {
        return monitorValue;
    }

    public void setMonitorValue(String monitorValue) {
        this.monitorValue = monitorValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
