package com.open.customview;

import android.app.assist.AssistStructure;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SEELE on 2017/5/26.
 */

public class CustomViewGroup extends ViewGroup {
    private int CircleCount;
    private int mLeft;
    private int mHorizotationSpace = 10;
    private int Radius = 10;
    private float mSetHeight;
    private int mWidth = getMeasuredWidth();
    private int mHeight = getMeasuredHeight();
    private List<CircleSpot> Spot = new ArrayList<>();
    private Paint mPaint;
    private int currIndex;

    public CustomViewGroup(Context context) {
        super(context);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int mWidth = getMeasuredWidth();
        int mHeight = getMeasuredHeight();
        for (int i = 0; i < childCount;i++){
            View child = getChildAt(i);
            l = childCount * mWidth;
            t =  0;
            r = mWidth + l;
            b = mHeight;
            child.layout(l,t,r,b);
        }
        CircleCount = childCount;

    }
    private void computerCircleSpot(){
        mLeft = (mWidth  - 2*Radius*CircleCount - mHorizotationSpace*(CircleCount - 1))/2;
        mSetHeight = mHeight*4/5;
        for (int i = 0; i < CircleCount;i++){
            Spot.add(new CircleSpot(mLeft,mSetHeight));
            mLeft = mLeft + 2 * Radius + mHorizotationSpace;
        }
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        computerCircleSpot();
        for (int i = 0; i < CircleCount;i++){
            if (currIndex == -1 || i == currIndex){
                currIndex = i;
                mPaint.setColor(Color.RED);
            }else {
                mPaint.setColor(Color.BLUE);
            }
            int x = Spot.get(i).getWidth();
            float y = Spot.get(i).getHeight();
            canvas.drawCircle(getScrollX() + x,y,Radius,mPaint);
        }
    }
}
