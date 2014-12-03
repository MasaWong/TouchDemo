package me.chunyu.demo.SortEvent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * @author MasaWong
 * @date 14-9-26.
 */
public class SortViewGroup extends LinearLayout {

    private int mChildHeight;

    private int mSelectedIndex;
    private int mCorveredIndex;

    public SortViewGroup(Context context) {
        super(context);
    }

    public SortViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        setChildrenDrawingOrderEnabled(true);
    }

    /**
     * override super method to set drawing order, make dragging item on the top
     * the param i means iteration not index, the returns int means NO.int child view, be careful
     */
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
        mChildHeight = getChildAt(0).getHeight();
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int currentY = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mSelectedIndex = getIndexBy(currentY);
                mCorveredIndex = mSelectedIndex;
                break;

            case MotionEvent.ACTION_MOVE:
                if (mSelectedIndex == -1) {
                    break;
                }
                View child = getChildAt(mSelectedIndex);
                child.layout(0, currentY - child.getHeight() / 2,
                        getWidth(), currentY + child.getHeight() / 2);

                int corveredIndex = getIndexBy(currentY);
                if (corveredIndex != -1 && corveredIndex != mCorveredIndex) {
                    animateChildSorting(corveredIndex);
                    mCorveredIndex = corveredIndex;
                }
                break;

            case MotionEvent.ACTION_UP:
                doChildSorting();
                mCorveredIndex = -1;
                mSelectedIndex = -1;
                break;
        }
        return true;
    }

    private void animateChildSorting(int corveredIndex) {
        int newTop = getCoordinateBy(mCorveredIndex);
        int oldTop = getCoordinateBy(corveredIndex);

        View child = getChildAt(getRealIndex(corveredIndex));

        TranslateAnimation animation = new TranslateAnimation(
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, oldTop - child.getTop(),
                Animation.ABSOLUTE, newTop - child.getTop());
        animation.setDuration(400L);
        animation.setFillAfter(true);

        child.clearAnimation();
        child.startAnimation(animation);
    }

    @SuppressLint("WrongCall")
    private void doChildSorting() {
        ArrayList<View> recycleBin = new ArrayList<View>();
        for (int i = 0; i < getChildCount(); ++i) {
            View view = getChildAt(i);
            view.clearAnimation();
            recycleBin.add(view);
        }
        removeAllViews();

        View child = recycleBin.get(mSelectedIndex);
        recycleBin.remove(mSelectedIndex);
        recycleBin.add(mCorveredIndex, child);

        for (View view : recycleBin) {
            addView(view);
        }

        // layout child views
        onLayout(true, getLeft(), getTop(), getRight(), getBottom());
    }

    private int getRealIndex(int i) {
        if (mCorveredIndex < mSelectedIndex && i > mCorveredIndex && i < mSelectedIndex) {
            ++i;
        } else if (mCorveredIndex > mSelectedIndex && i < mCorveredIndex && i > mSelectedIndex) {
            --i;
        }
        return i;
    }

    private int getIndexBy(int coordinate) {
        int index = coordinate / mChildHeight;
        if (index >= getChildCount()) {
            return -1;
        }
        return index;
    }

    private int getCoordinateBy(int index) {
        return mChildHeight * index;
    }
}
