package me.chunyu.demo.DragEvent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * @author MasaWong
 * @date 14-9-26.
 */
public class DragViewGroup extends FrameLayout {

    public DragViewGroup(Context context) {
        super(context);
    }

    public DragViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragViewGroup(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                View view = getChildAt(0);

                int height = view.getHeight();
                int width = view.getWidth();

                int currentX = (int) event.getX();
                int currentY = (int) event.getY();

                view.layout(currentX - width / 2, currentY - height / 2,
                        currentX + width / 2, currentY + width / 2);
                break;
        }
        return true;
    }
}
