package itor.topnetwork.com.dxditor.bean;

/**
 * @Description:
 * @Created by D.Han on 2018/3/23 13:58 in Peking.
 */

public class BridgeWarning {
    private String monitorName;
    private String bridgeName;
    private String currentValue;
    private String initValue;
    private String itemName;
    private String bridgeCode;
    private String monitorObjectCode;
    private String monitorItemCode;

    public BridgeWarning(String monitorName, String bridgeName, String currentValue, String initValue, String itemName, String bridgeCode, String monitorObjectCode, String monitorItemCode) {
        this.monitorName = monitorName;
        this.bridgeName = bridgeName;
        this.currentValue = currentValue;
        this.initValue = initValue;
        this.itemName = itemName;
        this.bridgeCode = bridgeCode;
        this.monitorObjectCode = monitorObjectCode;
        this.monitorItemCode = monitorItemCode;
    }

    public String getMonitorName() {
        return monitorName;
    }

    public void setMonitorName(String monitorName) {
        this.monitorName = monitorName;
    }

    public String getBridgeName() {
        return bridgeName;
    }

    public void setBridgeName(String bridgeName) {
        this.bridgeName = bridgeName;
    }

    public String getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(String currentValue) {
        this.currentValue = currentValue;
    }

    public String getInitValue() {
        return initValue;
    }

    public void setInitValue(String initValue) {
        this.initValue = initValue;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getBridgeCode() {
        return bridgeCode;
    }

    public void setBridgeCode(String bridgeCode) {
        this.bridgeCode = bridgeCode;
    }

    public String getMonitorObjectCode() {
        return monitorObjectCode;
    }

    public void setMonitorObjectCode(String monitorObjectCode) {
        this.monitorObjectCode = monitorObjectCode;
    }

    public String getMonitorItemCode() {
        return monitorItemCode;
    }

    public void setMonitorItemCode(String monitorItemCode) {
        this.monitorItemCode = monitorItemCode;
    }
}
