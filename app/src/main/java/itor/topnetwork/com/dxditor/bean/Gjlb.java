package itor.topnetwork.com.dxditor.bean;

/**
 * 告警列表
 * Created by D.Han on 2017/11/24.
 */

public class Gjlb {
    private String xl;
    private String glb;
    private String yw;
    private String sb;
    private String type;
    private String sj;

    public Gjlb(String xl, String glb, String yw, String sb, String type, String sj) {
        this.xl = xl;
        this.glb = glb;
        this.yw = yw;
        this.sb = sb;
        this.type = type;
        this.sj = sj;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getXl() {
        return xl;
    }

    public void setXl(String xl) {
        this.xl = xl;
    }

    public String getGlb() {
        return glb;
    }

    public void setGlb(String glb) {
        this.glb = glb;
    }

    public String getYw() {
        return yw;
    }

    public void setYw(String yw) {
        this.yw = yw;
    }

    public String getSb() {
        return sb;
    }

    public void setSb(String sb) {
        this.sb = sb;
    }


    public String getSj() {
        return sj;
    }

    public void setSj(String sj) {
        this.sj = sj;
    }
}
