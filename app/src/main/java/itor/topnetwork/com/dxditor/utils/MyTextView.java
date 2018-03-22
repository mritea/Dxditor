package itor.topnetwork.com.dxditor.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class MyTextView extends AppCompatTextView {

    private final static String TAG = "222";
    private Paint paint1;
    private Paint paint2;

    private int mWidth;
    private LinearGradient gradient;
    private Matrix matrix;
    //渐变的速度
    private int deltaX;

    public MyTextView(Context context) {
        super(context, null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
        // TODO Auto-generated constructor stub
    }

    private void initView(Context context, AttributeSet attrs) {
        paint1 = new Paint();
        paint1.setColor(getResources().getColor(android.R.color.holo_blue_dark));
        paint1.setStyle(Paint.Style.FILL);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // TODO Auto-generated method stub
        super.onSizeChanged(w, h, oldw, oldh);
        if(mWidth == 0){
            mWidth = getMeasuredWidth();
            paint2 = getPaint();
            //颜色渐变器
            gradient = new LinearGradient(0, 0, mWidth, 0, new int[]{Color.BLUE,Color.GREEN,Color.RED}, null, Shader.TileMode.CLAMP);
            paint2.setShader(gradient);

            matrix = new Matrix();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(matrix !=null){
            deltaX+=mWidth/5;
            if(deltaX>2*mWidth){
                deltaX = -mWidth;
            }
        }
        //关键代码通过矩阵的平移实现
        matrix.setTranslate(deltaX, 0);
        gradient.setLocalMatrix(matrix);
        postInvalidateDelayed(10);
    }
}