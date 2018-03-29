package itor.topnetwork.com.dxditor.bean;

/**
 * @Description:
 * @Created by D.Han on 2018/3/23 14:37 in Peking.
 */

public class BridgeTrend {
private String value;
private String storageTime;

    public BridgeTrend(String value, String storageTime) {
        this.value = value;
        this.storageTime = storageTime;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStorageTime() {
        return storageTime;
    }

    public void setStorageTime(String storageTime) {
        this.storageTime = storageTime;
    }
}
