package itor.topnetwork.com.dxditor.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by D.Han on 2017/11/28.
 */

public class MyUtils {
    public static String inputStream2String(InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1;) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }
}
