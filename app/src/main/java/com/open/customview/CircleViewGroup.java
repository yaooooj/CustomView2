package com.open.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SEELE on 2017/6/4.
 */

public class CircleViewGroup extends ViewPager {
    private String TAG = "CircleViewGroup";
    private Paint mPaint;
    private Paint mPaintFull;
    private List<CircleSpot> mCircleSpots;
    private int mWidth;
    private int mHeight;
    private boolean isCircle;
    private int mRadius = 5;
    private float mTwoCircleSpace = 10;
    private float mLeft,mTop;
    private int mCount;
    public CircleViewGroup(Context context) {
        super(context);
    }

    public CircleViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.CircleViewGroup);
        mCount = ta.getInteger(R.styleable.CircleViewGroup_circleCount,0);
        Log.d(TAG,"CircleCount -" + mCount);
        init();

    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);

        mPaintFull = new Paint();
        mPaintFull.setStyle(Paint.Style.FILL);
        mPaintFull.setColor(Color.WHITE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
    }

    private void computerRadius(int count){
        if (count == 0){
            isCircle = false;
        }else {
            mLeft = mWidth / 2 - mRadius - (count - 1) * mTwoCircleSpace;
            mTop = mHeight * 3 / 4F;
            mCircleSpots = new ArrayList<>(count);
            for (int i =0;i < count;i++){
                mCircleSpots.add(new CircleSpot(mLeft,mTop));
                mLeft = mLeft + 2 * mRadius + mTwoCircleSpace;
            }
            isCircle = true;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        computerRadius(mCount);
        for (int i =0;i < mCount;i++){
            canvas.drawCircle(mCircleSpots.get(i).getWidth(),mCircleSpots.get(i).getHeight(),mRadius,mPaint);
        }
    }
}
