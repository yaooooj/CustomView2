package com.open.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by SEELE on 2017/5/30.
 */

public class WaveView extends View {
    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private float waveY;
    private float ctrX,ctrY;
    private Path mPath;
    private boolean isInc;
    public WaveView(Context context) {
        super(context);
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(0xFFA2D6AE);

        mPath = new Path();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;

        waveY = 1/8F*mHeight;
        ctrY = - 1/128F*mHeight;

    }


    @Override
    protected void onDraw(Canvas canvas) {

        waveOneThirdHeight();
        waveControl();
        canvas.drawPath(mPath,mPaint);
        mPath.reset();
        invalidate();
    }
    private void waveMove(){
        mPath.moveTo(-1/4F*mWidth,waveY);
        mPath.quadTo(ctrX,ctrY,mWidth + 1/4F*mWidth,waveY);
        mPath.lineTo(mWidth + 1/4F*mWidth,mHeight);
        mPath.lineTo(-1/4F*mWidth,mHeight);
        mPath.close();

        if (waveY  <=  mHeight) {
            ctrY += 2;
            waveY += 2;
        }
    }
    private void waveOneThirdHeight(){
        mPath.moveTo(-1/4F*mWidth,1/3F*mHeight);
        mPath.quadTo(ctrX,ctrY,mWidth + 1/4F*mWidth,1/3F*mHeight);
        mPath.lineTo(mWidth + 1/4F*mWidth,1/3F*mHeight +5);
        mPath.lineTo(-1/4F*mWidth,1/3F*mHeight +5);
        mPath.close();
    }
    private void waveControl(){
        if (ctrX >= mWidth + 1 / 4F * mWidth) {
            isInc = false;
        }
        /*
         * 当控制点的x坐标小于或等于起点x坐标时更改标识值
         */
        else if (ctrX <= -1 / 4F * mWidth) {
            isInc = true;
        }

        ctrX = isInc ? ctrX + 15 : ctrX - 15;
    }
}
