package com.open.customview;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by SEELE on 2017/5/30.
 */

public class PaintView extends View implements Runnable {
    private Paint mPaint;
    private Paint mPaintFill;
    private int mWidth;
    private int mHeight;
    private int Radius;
    private Boolean changFlag = true;
    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        init();
    }

    public PaintView(Context context) {
        super(context);
    }

    public PaintView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(10);

        mPaintFill = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintFill.setStyle(Paint.Style.FILL);
        mPaintFill.setColor(Color.GRAY);
        mPaintFill.setMaskFilter(new BlurMaskFilter(50,BlurMaskFilter.Blur.SOLID));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mWidth/2,mHeight/2,Radius,mPaint);
        canvas.drawCircle(mWidth/2,mHeight/2,Radius,mPaintFill);
    }

    @Override
    public void run() {
        while (true){
            try {
                if (changFlag){
                    if (Radius < mHeight/2){
                        Radius += 10;
                        postInvalidate();
                    }
                    else {
                        changFlag = false;
                    }
                }
                else{
                    if(Radius >= 10){
                        Radius -= 10;
                        postInvalidate();
                    }
                    else {
                        changFlag = true;
                    }
                }
                Thread.sleep(40);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
