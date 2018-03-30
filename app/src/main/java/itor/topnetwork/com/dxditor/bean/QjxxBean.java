package itor.topnetwork.com.dxditor.bean;

/**
 * @Description: 全局信息实体类
 * @Created by D.Han on 2018/3/30 16:33 in Peking.
 */

public class QjxxBean {
    private String typeCode;
    private String lineName;
    private Integer deviceCount;
    private Integer lineId;
    private Integer deviceAlarmCount;
    private Integer bridgeCount;
    private Integer soundBarrierCount;
    private Integer clampCount;
    private Integer weightsCount;
    private Integer alarmCount;

    public QjxxBean(String typeCode, String lineName, Integer deviceCount, Integer lineId, Integer deviceAlarmCount, Integer bridgeCount, Integer soundBarrierCount, Integer clampCount, Integer weightsCount, Integer alarmCount) {
        this.typeCode = typeCode;
        this.lineName = lineName;
        this.deviceCount = deviceCount;
        this.lineId = lineId;
        this.deviceAlarmCount = deviceAlarmCount;
        this.bridgeCount = bridgeCount;
        this.soundBarrierCount = soundBarrierCount;
        this.clampCount = clampCount;
        this.weightsCount = weightsCount;
        this.alarmCount = alarmCount;
    }

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

    public Integer getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(Integer deviceCount) {
        this.deviceCount = deviceCount;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public Integer getDeviceAlarmCount() {
        return deviceAlarmCount;
    }

    public void setDeviceAlarmCount(Integer deviceAlarmCount) {
        this.deviceAlarmCount = deviceAlarmCount;
    }

    public Integer getBridgeCount() {
        return bridgeCount;
    }

    public void setBridgeCount(Integer bridgeCount) {
        this.bridgeCount = bridgeCount;
    }

    public Integer getSoundBarrierCount() {
        return soundBarrierCount;
    }

    public void setSoundBarrierCount(Integer soundBarrierCount) {
        this.soundBarrierCount = soundBarrierCount;
    }

    public Integer getClampCount() {
        return clampCount;
    }

    public void setClampCount(Integer clampCount) {
        this.clampCount = clampCount;
    }

    public Integer getWeightsCount() {
        return weightsCount;
    }

    public void setWeightsCount(Integer weightsCount) {
        this.weightsCount = weightsCount;
    }

    public Integer getAlarmCount() {
        return alarmCount;
    }

    public void setAlarmCount(Integer alarmCount) {
        this.alarmCount = alarmCount;
    }
}
