package itor.topnetwork.com.dxditor.bean;

/**
 * Created by D.Han on 2017/11/20.
 */

public class GjxxBean {
    private String name;
    private int persent;

    public GjxxBean(String name, int persent) {
        this.name = name;
        this.persent = persent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPersent() {
        return persent;
    }

    public void setPersent(int persent) {
        this.persent = persent;
    }
}
