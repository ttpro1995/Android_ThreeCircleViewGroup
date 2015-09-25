package meow.thaithien.threecicle;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 *
 */
public class CircularView extends ImageView implements View.OnTouchListener{

    private float mCircleCenterX;
    private float mCircleCenterY;
    private float mCircleRadius;
    private OnClickListener mClickListener;

    public CircularView(Context context) {
        this(context, null);
    }

    public CircularView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircularView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(this);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        measure();
    }

    private void measure() {
        this.mCircleCenterX = getWidth()/2;
        this.mCircleCenterY = getHeight()/2;
        this.mCircleRadius = getWidth() / 2;
    }

    @Override
    public boolean onTouch(View view, MotionEvent evt) {
        switch(evt.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                if (inCircle(evt.getX(), evt.getY(), mCircleCenterX, mCircleCenterY, mCircleRadius)) {
                    if(this.mClickListener != null)
                        this.mClickListener.onClick(view);
                }
                return true;
            }

        }
        return false;
    }

    private boolean inCircle(float x, float y, float circleCenterX, float circleCenterY, float circleRadius) {
        double dx = Math.pow(Math.abs(x - circleCenterX), 2);
        double dy = Math.pow(Math.abs(y - circleCenterY), 2);
        double distance = Math.sqrt(dx + dy);
        return distance <= circleRadius;
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        this.mClickListener = l;
    }
}
