package meow.thaithien.threecicle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Thien on 8/12/2015.
 */
public class ThreeCycleViewGroup extends FrameLayout implements View.OnClickListener{

    private String LOG_TAG = ThreeCycleViewGroup.class.getSimpleName();

    private Context context;
   private CircularView top;
    private CircularView mid;
    private CircularView bot;
   // private FrameLayout container;
    private RelativeLayout container;
    private ThreeCycle threeCycle;

    private OnClickListener top_onclick;
    private OnClickListener mid_onclick;
    private OnClickListener bot_onclick;

    private OnTouchListener touchListener;


    public ThreeCycleViewGroup(Context context, ThreeCycle threeCycle) {
        super(context);
        this.context = context;
        //use LayoutInflater
        LayoutInflater li = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       //li.inflate(R.layout.frame_threecircle, this, true);//bind CustomViewGroup with List_item layout
        li.inflate(R.layout.relativecirclegroupview, this, true);//bind CustomViewGroup with List_item layout
        this.threeCycle = threeCycle;

        //bind
        top = (CircularView) findViewById(R.id.top_circle);
        mid = (CircularView) findViewById(R.id.mid_circle);
        bot = (CircularView) findViewById(R.id.bot_circle);
        //container = (FrameLayout) findViewById(R.id.container);
        container = (RelativeLayout) findViewById(R.id.container);
    }

    public void setImage(){
        Drawable top_d = threeCycle.getTop();
        Drawable mid_d = threeCycle.getMid();
        Drawable bot_d = threeCycle.getBot();
        ArrayList<Integer> status = threeCycle.getStatus();

        //framelayout
        /*FrameLayout.LayoutParams top_param =  (FrameLayout.LayoutParams)top.getLayoutParams();
        FrameLayout.LayoutParams mid_param =  (FrameLayout.LayoutParams)mid.getLayoutParams();
        FrameLayout.LayoutParams bot_param =  (FrameLayout.LayoutParams)bot.getLayoutParams();*/

        //relative layout
        RelativeLayout.LayoutParams top_param =  (RelativeLayout.LayoutParams)top.getLayoutParams();
        RelativeLayout.LayoutParams mid_param =  (RelativeLayout.LayoutParams)mid.getLayoutParams();
        RelativeLayout.LayoutParams bot_param =  (RelativeLayout.LayoutParams)bot.getLayoutParams();

        container.removeView(top);
        container.removeView(mid);
        container.removeView(bot);

        top = new CircularView(context);
        top.setLayoutParams(top_param);
        top.setId(R.id.top_circle);

        mid = new CircularView(context);
        mid.setLayoutParams(mid_param);
        mid.setId(R.id.mid_circle);

        bot = new CircularView(context);
        bot.setLayoutParams(bot_param);
        bot.setId(R.id.bot_circle);

        top.setImageDrawable(top_d);
        mid.setImageDrawable(mid_d);
        bot.setImageDrawable(bot_d);

       top.setOnClickListener(this);
        mid.setOnClickListener(this);
        bot.setOnClickListener(this);

        top.setBackground(getResources().getDrawable(R.drawable.round));
        mid.setBackground(getResources().getDrawable(R.drawable.round));
        bot.setBackground(getResources().getDrawable(R.drawable.round));
        /*top.setBackground(top_d);
        mid.setBackground(mid_d);
        bot.setBackground(bot_d);*/
        for (int i = 2; i >= 0; i--) {
            if (status.get(i)==1)
                container.addView(top);
            if (status.get(i)==2)
                container.addView(mid);
            if (status.get(i)==3)
                container.addView(bot);
        }
        //initOnTouchEvent(status.get(0),status.get(1),status.get(2));
    }

    public void setTopOnClickListener(OnClickListener listener){
        this.top_onclick = listener;
    }
    public void setMidOnClickListener(OnClickListener listener){
        this.mid_onclick = listener;
    }
    public void setBotOnClickListener(OnClickListener listener){
        this.bot_onclick = listener;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case  R.id.top_circle:
                threeCycle.selectTop();
                Log.v(LOG_TAG, "select top");
                setImage();
                this.top_onclick.onClick(top);
                break;

            case R.id.mid_circle:
                threeCycle.selectMid();
                Log.v(LOG_TAG, "select mid");
                setImage();
               this.mid_onclick.onClick(mid);
                break;

            case R.id.bot_circle:
                threeCycle.selectBot();
                Log.v(LOG_TAG, "select bot");
                setImage();
               this.bot_onclick.onClick(bot);
                break;
        }
    }
/*
    private void initOnTouchEvent(int big, int med, int small){
        touchListener = new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    float screenX = motionEvent.getX();
                    float screenY = motionEvent.getY();
                    float viewX = screenX - view.getLeft();
                    float viewY = screenY - view.getTop();
                    Log.d(LOG_TAG,"viewX = "+ viewX);
                    Log.d(LOG_TAG,"viewY = "+ viewY);
                    Log.d(LOG_TAG,"view get Left = "+ view.getLeft());
                    Log.d(LOG_TAG, "view get Top = " + view.getTop());

                    float capX = view.getRight() - view.getLeft();
                    float capY = view.getBottom() - view.getTop();
                    Log.d(LOG_TAG,"capX = "+ capX);
                    Log.d(LOG_TAG,"capY = "+ capY);

                    float right_corner_X  = capX - capX/3;
                    float left_corner_X = capX - (capX*2)/3;
                    float bot_corner_Y = capY - capY /3;
                    float top_corner_Y = capY - (capY*2)/3;



                    if (view.getId() == R.id.top_circle) {
                        Log.i(LOG_TAG,"on touch view top circle");
                        if ((right_corner_X < viewX)
                                && (bot_corner_Y < viewY)) {
                            clickMid();
                            return true;
                        }

                        if (bot_corner_Y < viewY) {
                            clickBot();
                            return true;
                        }

                        clickTop();
                        return true;
                    }

                    if (view.getId() == R.id.bot_circle) {
                        Log.i(LOG_TAG,"on touch view bot circle");
                        if ((right_corner_X < viewX)
                               &&(viewY<top_corner_Y)) {
                            clickMid();
                            return true;
                        }
                        if (viewY>top_corner_Y){
                            clickTop();
                            return true;
                        }
                        clickBot();
                        return true;
                    }

                    if (view.getId() == R.id.mid_circle){
                        Log.i(LOG_TAG,"on touch view mid circle");

                        if ((viewY<top_corner_Y)
                                &&(viewX<left_corner_X))
                        {
                            clickTop();
                            return true;
                        }

                        if ((viewY>bot_corner_Y)
                                &&(viewX<left_corner_X))
                        {
                            clickBot();
                            return true;
                        }
                        clickMid();
                        return true;
                    }

                    return true;
                }
                return false;
            }
        };

        switch (big){
            case 1:
                top.setOnTouchListener(touchListener);
                break;
            case 2:
                mid.setOnTouchListener(touchListener);
                break;
            case 3:
                bot.setOnTouchListener(touchListener);
                break;
        }
        switch (med){
            case 1:
                top.setOnTouchListener(touchListener);
                break;
            case 2:
                mid.setOnTouchListener(touchListener);
                break;
            case 3:
                bot.setOnTouchListener(touchListener);
                break;
        }

        switch (small){
            case 1:
                top.setOnClickListener(this);
                break;
            case 2:
                mid.setOnClickListener(this);
                break;
            case 3:
                bot.setOnClickListener(this);
                break;
        }


    }*/

    private void clickTop(){
        Log.i(LOG_TAG, "click Top");
        threeCycle.selectTop();
        Log.v(LOG_TAG, "select top");
        setImage();
        this.top_onclick.onClick(top);
    }
    private void clickBot(){
        Log.i(LOG_TAG,"click Bot");
        threeCycle.selectBot();
        Log.v(LOG_TAG, "select bot");
        setImage();
        this.bot_onclick.onClick(bot);
    }
    private void clickMid(){
        Log.i(LOG_TAG, "click Mid");
        threeCycle.selectMid();
        Log.v(LOG_TAG, "select mid");
        setImage();
        this.mid_onclick.onClick(mid);

    }
}
