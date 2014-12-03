package me.chunyu.demo.TouchEvent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import me.chunyu.demo.Event;

/**
 * @author MasaWong
 * @date 14-9-26.
 */
public class TouchViewGroup extends FrameLayout {
    public TouchViewGroup(Context context) {
        super(context);
    }

    public TouchViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchViewGroup(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e("ViewGroupDispatch", Event.getActionName(event));
        if (TouchEventActivity.sState == 1) {
            return true;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Log.e("ViewGroupIntercept", Event.getActionName(event));
        if (TouchEventActivity.sState == 2) {
            return true;
        }
        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("ViewGroupTouch", Event.getActionName(event));
        if (TouchEventActivity.sState == 3) {
            return true;
        }
        return super.onTouchEvent(event);
    }
}
