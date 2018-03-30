package itor.topnetwork.com.dxditor.bean;

/**
 * 设备信息
 * Created by D.Han on 2017/11/15.
 */

public class SbxxBean {
    private int typeCode;
    private String typeName;
    private String typeCount;
    public SbxxBean(int typeCode, String typeName, String typeCount) {
        this.typeCode = typeCode;
        this.typeName = typeName;
        this.typeCount = typeCount;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeCount() {
        return typeCount;
    }

    public void setTypeCount(String typeCount) {
        this.typeCount = typeCount;
    }
}
