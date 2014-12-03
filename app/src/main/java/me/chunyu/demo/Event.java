package me.chunyu.demo;

import android.view.MotionEvent;

/**
 * @author MasaWong
 * @date 14-9-26.
 */
public class Event {
    public static String getActionName(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return "ACTION_DOWN";

            case MotionEvent.ACTION_MOVE:
                return "ACTION_MOVE";

            case MotionEvent.ACTION_UP:
                return "ACTION_UP";

            case MotionEvent.ACTION_CANCEL:
                return "ACTION_CANCEL";

            case MotionEvent.ACTION_OUTSIDE:
                return "ACTION_OUTSIDE";

            default:
                return "";
        }
    }
}
