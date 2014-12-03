package me.chunyu.demo.TouchEvent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import me.chunyu.demo.Event;

/**
 * @author MasaWong
 * @date 14-9-26.
 */
public class TouchView extends View {
    public TouchView(Context context) {
        super(context);
    }

    public TouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e("ViewDispatch", Event.getActionName(event));
        if (TouchEventActivity.sState == 4) {
            return true;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("ViewTouch", Event.getActionName(event));
        if (TouchEventActivity.sState == 5) {
            return true;
        }
        return super.onTouchEvent(event);
    }
}
