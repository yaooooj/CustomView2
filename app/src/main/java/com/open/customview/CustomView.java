package com.open.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by SEELE on 2017/5/26.
 */

public class CustomView extends View {
    private Paint mPaint;
    private int mQuantity;
    private int mColor;
    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.CustomView);
        mQuantity = ta.getInteger(R.styleable.CustomView_quantity,0);
        mColor = ta.getColor(R.styleable.CustomView_color,0);
        ta.recycle();
        init();
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mColor);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final int x = getWidth() / 2;
        final int y = getHeight() / 2;
        canvas.drawCircle(x,y,15,mPaint);
    }
}