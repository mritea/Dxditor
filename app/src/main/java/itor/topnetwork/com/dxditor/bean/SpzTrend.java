package itor.topnetwork.com.dxditor.bean;

/**
 * @Description:
 * @Created by D.Han on 2018/3/27 17:02 in Peking.
 */

public class SpzTrend {
    private Float typeData;
    private String storageTime;

    public SpzTrend(Float typeData, String storageTime) {
        this.typeData = typeData;
        this.storageTime = storageTime;
    }

    public Float getTypeData() {
        return typeData;
    }

    public void setTypeData(Float typeData) {
        this.typeData = typeData;
    }

    public String getStorageTime() {
        return storageTime;
    }

    public void setStorageTime(String storageTime) {
        this.storageTime = storageTime;
    }
}
