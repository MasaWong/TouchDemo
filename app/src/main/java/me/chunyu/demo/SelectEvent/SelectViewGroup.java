package me.chunyu.demo.SelectEvent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

/**
 * @author MasaWong
 * @date 14-9-28.
 */
public class SelectViewGroup extends RelativeLayout {

    private int mSelectedIndex = -1;
    private ScaleAnimation mScaleAnimation;

    public SelectViewGroup(Context context) {
        super(context);
    }

    public SelectViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SelectViewGroup(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mScaleAnimation = new ScaleAnimation(1f, 2f, 1f, 2f);
        mScaleAnimation.setDuration(400L);
        mScaleAnimation.setFillAfter(true);

        setChildrenDrawingOrderEnabled(true);
    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        if (mSelectedIndex == -1) {
            return i;
        } else if (i == childCount - 1) {
            return mSelectedIndex;
        } else if (i >= mSelectedIndex) {
            return i + 1;
        }
        return i;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int x = (int) event.getX();
                int y = (int) event.getY();
                for (int i = 0; i < getChildCount(); ++i) {
                    View child = getChildAt(i);
                    if (x < child.getRight() && x > child.getLeft()
                            && y < child.getBottom() && y > child.getTop()) {
                        if (mSelectedIndex != i) {
                            if (mSelectedIndex != -1) {
                                getChildAt(mSelectedIndex).clearAnimation();
                            }

                            mSelectedIndex = i;

                            child.startAnimation(mScaleAnimation);
                        }
                        break;
                    }
                }
        }
        return true;
    }
}
