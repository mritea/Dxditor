package itor.topnetwork.com.dxditor.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * @Description:
 * @Created by D.Han on 2018/4/10 13:47 in Peking.
 */

public class CircleView extends View {

    private Paint circlePaint, whitePaint, ringPaint, percentPaint, textPaint, textLittlePaint;
    float percent = 0;
    String count = "0";
    String tai = "台";
    String color = "#F5B400";
    String textColor = "#FFFFFF";
    String grayColor = "#D9D9D9";

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    private int getMySize(int defult, int measureSpec) {
        int mySize = defult;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
                mySize = defult;
                break;
            case MeasureSpec.AT_MOST:
                mySize = size / 5 * 4;
                break;
            case MeasureSpec.EXACTLY:
                mySize = size;
                break;
        }
        return mySize;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int weith = getMySize(100, widthMeasureSpec);
        int height = getMySize(100, heightMeasureSpec);
        if (weith < height) {
            height = weith;
        } else {
            weith = height;
        }
        setMeasuredDimension(weith, height);
    }

    public void init() {
        initRingclePaint();
        initWhitePaint();
        initCirclePaint();
        initPercentPaint();
        initTextPaint();
        initTextLittlePaint();
    }

    private void initTextPaint() {
        textPaint = new Paint();
        textPaint.setTextSize(sp2px(20));
        textPaint.setColor(Color.parseColor(textColor));
    }

    private void initTextLittlePaint() {
        textLittlePaint = new Paint();
        textLittlePaint.setTextSize(sp2px(15));
        textLittlePaint.setColor(Color.parseColor(textColor));
    }


    private void initWhitePaint() {
        whitePaint = new Paint();
        whitePaint.setColor(Color.parseColor(textColor));
    }


    private void initCirclePaint() {
        circlePaint = new Paint();
        circlePaint.setColor(Color.parseColor(color));
    }

    private void initRingclePaint() {
        ringPaint = new Paint();
        ringPaint.setColor(Color.parseColor(grayColor));
    }

    private void initPercentPaint() {
        percentPaint = new Paint();
        percentPaint.setColor(Color.parseColor(color));
        percentPaint.setStrokeWidth(10);
        // percentPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init();
        drawRingCircle(canvas);
        drawPercent(canvas);
        drawWhiteCircle(canvas);
        drawCircle(canvas);
        drawText(canvas);
    }

    private void drawText(Canvas canvas) {
        canvas.save();
        canvas.drawText(count, (getWidth() - textPaint.measureText(count)) / 2 - dp2px(3), (getHeight() + getHeight(count)) / 2, textPaint);

        canvas.drawText(tai, (getWidth() + textLittlePaint.measureText(count)) / 2, (getHeight() + getHeight(count)) / 2, textLittlePaint);
        canvas.restore();
    }

    private void drawPercent(Canvas canvas) {
        canvas.save();
        RectF rectF = new RectF(0, 0, getWidth(), getHeight());
        canvas.drawArc(rectF, 270, (percent / 100) * 360, true, percentPaint);
        canvas.restore();
    }

    private void drawWhiteCircle(Canvas canvas) {
        canvas.save();
        float r = (float) (getWidth() / 2.5);
        int centerX = getWidth() / 2;
        //圆心的纵坐标为当前的View的顶部起始位置+半径
        int centerY = getWidth() / 2;
        canvas.drawCircle(centerX, centerY, r, whitePaint);
        canvas.restore();
    }

    private void drawRingCircle(Canvas canvas) {
        canvas.save();
        float r = (float) (getWidth() / 2);
        int centerX = getWidth() / 2;
        //圆心的纵坐标为当前的View的顶部起始位置+半径
        int centerY = getWidth() / 2;
        canvas.drawCircle(centerX, centerY, r, ringPaint);
        canvas.restore();
    }

    private void drawCircle(Canvas canvas) {
        canvas.save();
        float r = (float) (getWidth() / 3);
        int centerX = getWidth() / 2;
        int centerY = getWidth() / 2;
        canvas.drawCircle(centerX, centerY, r, circlePaint);
        canvas.restore();
    }

    public int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    private int sp2px(float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                getResources().getDisplayMetrics());
    }

    private float getHeight(String str) {
        Rect rect = new Rect();
        textPaint.getTextBounds(str, 0, str.length(), rect);
        return rect.height();
    }

    /**
     * 设置温度
     *
     * @param percent 百分比
     * @param count   数量
     * @param color   颜色
     */
    public void setData(float percent, String count, String color) {
        this.percent = percent;
        this.count = count;
        this.color = color;
        invalidate();
    }
}
