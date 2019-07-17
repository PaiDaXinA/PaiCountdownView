package com.example.paicountdownviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PaiCountdownView extends View {
    private Paint mPaint;
    private Context context;
    private RadialGradient radialGradient;
    int num = 0;
    private float sum=0;
    private int y=0;
    private int initialColor=R.color.blue_item;
    private int finishColor=R.color.red;

    public PaiCountdownView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public PaiCountdownView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public PaiCountdownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setInitialColor(int initialColor) {
        this.initialColor = initialColor;
    }

    public void setFinishColor(int finishColor) {
        this.finishColor = finishColor;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
        invalidate();
    }

    public void setY(int sum) {
        this.y = sum;
        invalidate();
    }

    private void init() {
        mPaint = new Paint();
        //抗锯齿
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        //分三种，STROKE之绘制轮廓，不绘制内容，FILL只绘制内容，FILL_AND_STROKE，内容和轮廓都绘制
        mPaint.setStyle(Paint.Style.FILL);
      //  paint.setAntiAlias(true);// 设置是否抗锯齿
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);// 帮助消除锯齿
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //设置线宽，线宽默认是1
        mPaint.setStrokeWidth(7);
        //在屏幕中间画圆，半径为屏幕的1/3
        //canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/3,mPaint);

        //取得整个屏幕中心为圆心点
        mPaint.setStrokeWidth(5);
        canvas.drawPoint(getWidth() / 2, y +getWidth() / 4, mPaint);

        //android坐标系默认是在左上角的，现在我们需要将坐标轴移动到有圆心位置，这样有利于我们绘制线
        mPaint.setStrokeWidth(7);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(getResources().getColor(initialColor));
        //坐标原点平移到圆心的位置
        canvas.translate(getWidth() / 2, y +getWidth() / 4);
        for (int i = 0; i < 360; i++) {
            //刻度线长度为20，一圈是360度，并且秒钟转一圈为60秒，所以一秒就对应360度/60秒
            if (i % 10 == 0) {
                canvas.drawLine(0, getWidth()/4-40,0 , getWidth() / 4, mPaint);
            }
//            }else if(i%6==0){
//                canvas.drawLine(getWidth()/3-14,0,getWidth()/3,0,mPaint);
//            }
            //每绘制一次就旋转一度，总共绘制了360条线
            canvas.rotate(1);
        }

        //canvas.restore();
        mPaint.setStrokeWidth(7);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(getResources().getColor(finishColor));
        //坐标原点平移到圆心的位置
        // canvas.translate(getWidth()/2,getHeight()/2);
        for (int j = 0; j < sum; j++) {
            //刻度线长度为20，一圈是360度，并且秒钟转一圈为60秒，所以一秒就对应360度/60秒
            if (j % 10 == 0) {
                num++;
                canvas.drawLine(0, getWidth()/4-40,0 , getWidth() / 4, mPaint);
            }
//            }else if(i%6==0){
//                canvas.drawLine(getWidth()/3-14,0,getWidth()/3,0,mPaint);
//            }
            //每绘制一次就旋转一度，总共绘制了360条线
            canvas.rotate(-1);
        }
        //Toast.makeText(context, "num" + sum, Toast.LENGTH_SHORT).show();
    }
}