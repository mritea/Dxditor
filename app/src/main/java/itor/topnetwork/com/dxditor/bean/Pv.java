package itor.topnetwork.com.dxditor.bean;

/**
 * @Description:
 * @Created by D.Han on 2018/3/7 17:20 in Peking.
 */

import java.io.Serializable;
import java.util.Arrays;

public class Pv implements Serializable {
    private float x;
    private float y;
    private float originX;
    private float originY;
    private float diffX;
    private float diffY;
    private char[] label;

    public Pv() {
        this.set(0.0F, 0.0F);
    }

    public Pv(float x, float y) {
        this.set(x, y);
    }

    public Pv(Pv pv) {
        this.set(pv.x, pv.y);
        this.label = pv.label;
    }

    public void update(float scale) {
        this.x = this.originX + this.diffX * scale;
        this.y = this.originY + this.diffY * scale;
    }

    public void finish() {
        this.set(this.originX + this.diffX, this.originY + this.diffY);
    }

    public Pv set(float x, float y) {
        this.x = x;
        this.y = y;
        this.originX = x;
        this.originY = y;
        this.diffX = 0.0F;
        this.diffY = 0.0F;
        return this;
    }

    public Pv setTarget(float targetX, float targetY) {
        this.set(this.x, this.y);
        this.diffX = targetX - this.originX;
        this.diffY = targetY - this.originY;
        return this;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    /** @deprecated */
    @Deprecated
    public char[] getLabel() {
        return this.label;
    }

    public Pv setLabel(String label) {
        this.label = label.toCharArray();
        return this;
    }

    public char[] getLabelAsChars() {
        return this.label;
    }

    /** @deprecated */
    @Deprecated
    public Pv setLabel(char[] label) {
        this.label = label;
        return this;
    }

    public String toString() {
        return "PointValue [x=" + this.x + ", y=" + this.y + "]";
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(o != null && this.getClass() == o.getClass()) {
            Pv that = (Pv)o;
            return Float.compare(that.diffX, this.diffX) != 0?false:(Float.compare(that.diffY, this.diffY) != 0?false:(Float.compare(that.originX, this.originX) != 0?false:(Float.compare(that.originY, this.originY) != 0?false:(Float.compare(that.x, this.x) != 0?false:(Float.compare(that.y, this.y) != 0?false:Arrays.equals(this.label, that.label))))));
        } else {
            return false;
        }
    }

    public int hashCode() {
        int result = this.x != 0.0F?Float.floatToIntBits(this.x):0;
        result = 31 * result + (this.y != 0.0F?Float.floatToIntBits(this.y):0);
        result = 31 * result + (this.originX != 0.0F?Float.floatToIntBits(this.originX):0);
        result = 31 * result + (this.originY != 0.0F?Float.floatToIntBits(this.originY):0);
        result = 31 * result + (this.diffX != 0.0F?Float.floatToIntBits(this.diffX):0);
        result = 31 * result + (this.diffY != 0.0F?Float.floatToIntBits(this.diffY):0);
        result = 31 * result + (this.label != null?Arrays.hashCode(this.label):0);
        return result;
    }
}
