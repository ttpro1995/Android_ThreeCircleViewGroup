package meow.thaithien.threecicle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
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
   private ImageButton top;
    private ImageButton mid;
    private ImageButton bot;
    private FrameLayout container;

    private ThreeCycle threeCycle;

    private OnClickListener top_onclick;
    private OnClickListener mid_onclick;
    private OnClickListener bot_onclick;

    public ThreeCycleViewGroup(Context context, ThreeCycle threeCycle) {
        super(context);
        this.context = context;
        //use LayoutInflater
        LayoutInflater li = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        li.inflate(R.layout.circlegroupview, this, true);//bind CustomViewGroup with List_item layout
        this.threeCycle = threeCycle;

        //bind
        top = (ImageButton) findViewById(R.id.top_circle);
        mid = (ImageButton) findViewById(R.id.mid_circle);
        bot = (ImageButton) findViewById(R.id.bot_circle);
        container = (FrameLayout) findViewById(R.id.container);

    }

    public void setImage(){
        Drawable top_d = threeCycle.getTop();
        Drawable mid_d = threeCycle.getMid();
        Drawable bot_d = threeCycle.getBot();
        ArrayList<Integer> status = threeCycle.getStatus();

        FrameLayout.LayoutParams top_param =  (FrameLayout.LayoutParams)top.getLayoutParams();
        FrameLayout.LayoutParams mid_param =  (FrameLayout.LayoutParams)mid.getLayoutParams();
        FrameLayout.LayoutParams bot_param =  (FrameLayout.LayoutParams)bot.getLayoutParams();
        container.removeView(top);
        container.removeView(mid);
        container.removeView(bot);

        top = new ImageButton(context);
        top.setLayoutParams(top_param);
        top.setId(R.id.top_circle);

        mid = new ImageButton(context);
        mid.setLayoutParams(mid_param);
        mid.setId(R.id.mid_circle);

        bot = new ImageButton(context);
        bot.setLayoutParams(bot_param);
        bot.setId(R.id.bot_circle);

        top.setImageDrawable(top_d);
        mid.setImageDrawable(mid_d);
        bot.setImageDrawable(bot_d);

        top.setOnClickListener(this);
        mid.setOnClickListener(this);
        bot.setOnClickListener(this);

        top.setBackground(null);
        mid.setBackground(null);
        bot.setBackground(null);

        for (int i=2;i>=0;i--){
            if (status.get(i)==1)
                container.addView(top);
            if (status.get(i)==2)
                container.addView(mid);
            if (status.get(i)==3)
                container.addView(bot);
        }
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
}
