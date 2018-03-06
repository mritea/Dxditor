package itor.topnetwork.com.dxditor.model.xj;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import itor.topnetwork.com.dxditor.bean.XjBean;
import itor.topnetwork.com.dxditor.utils.ValueCallBack;

/**
 * 线夹监测Model
 * Created by kimi on 2018/3/6.
 */

public class XjjcModel implements IxjjcModel{
    private Timer timer; // 定时刷新折线图
    public XjjcModel() {
    }



    @Override
    public void xjjcData(final ValueCallBack<XjBean> callBack) {
        final Random random = new Random();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int nextInt = random.nextInt(150);

                XjBean xjBean = new XjBean();
                xjBean.setTem(nextInt + 1+"");
                callBack.onSuccess(xjBean);
            }
        }, 100, 1000);
    }
}
