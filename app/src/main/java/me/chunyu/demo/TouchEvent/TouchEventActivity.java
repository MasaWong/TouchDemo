package me.chunyu.demo.TouchEvent;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import me.chunyu.demo.Event;
import me.chunyu.demo.R;


public class TouchEventActivity extends ActionBarActivity {

    public static int sState = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            sState = (sState + 1) % 7;
            Log.e("State", String.valueOf(sState));
        }

        Log.e("ActivityDispatch", Event.getActionName(event));
        if (sState == 0) {
            return true;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("ActivityTouch", Event.getActionName(event));
        if (TouchEventActivity.sState == 6) {
            return true;
        }
        return super.onTouchEvent(event);
    }
}
