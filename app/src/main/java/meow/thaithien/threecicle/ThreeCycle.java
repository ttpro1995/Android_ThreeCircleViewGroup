package meow.thaithien.threecicle;

import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Thien on 9/23/2015.
 */
public class ThreeCycle {

    private String LOG_TAG = ThreeCycle.class.getSimpleName();
    //1
    private Drawable top_big;
    private Drawable top_med;
    private Drawable top_small;

    //2
    private Drawable mid_big;
    private Drawable mid_med;
    private Drawable mid_small;

    //3
    private Drawable bot_big;
    private Drawable bot_med;
    private Drawable bot_small;



    private ArrayList<Integer> status;

    public ThreeCycle( Drawable top_big, Drawable top_med, Drawable top_small, Drawable mid_big, Drawable mid_med, Drawable mid_small, Drawable bot_big, Drawable bot_med, Drawable bot_small) {


        this.top_big = top_big;
        this.top_med = top_med;
        this.top_small = top_small;
        this.mid_big = mid_big;
        this.mid_med = mid_med;
        this.mid_small = mid_small;
        this.bot_big = bot_big;
        this.bot_med = bot_med;
        this.bot_small = bot_small;
        status = new ArrayList<>();
        status.add(1);
        status.add(2);
        status.add(3);
    }

    public Drawable getTop(){
        if (status.get(0)==1)
            return top_big;
        if (status.get(1)==1)
            return top_med;
        if (status.get(2)==1)
            return top_small;
        return null;
    };
    public Drawable getMid(){
        if (status.get(0)==2)
            return mid_big;
        if (status.get(1)==2)
            return mid_med;
        if (status.get(2)==2)
            return mid_small;
        return null;
    };
    public Drawable getBot(){
        if (status.get(0)==3)
            return bot_big;
        if (status.get(1)==3)
            return bot_med;
        if (status.get(2)==3)
            return bot_small;
        return null;
    };

    public void selectTop(){
        for (int i=0;i<3;i++)
            if (status.get(i)==1) {
                status.remove(i);
                break;
            }
        status.add(0,1);
        Log.v(LOG_TAG,"select top");
    }

    public void selectMid(){
        for (int i=0;i<3;i++)
            if (status.get(i)==2) {
                status.remove(i);
                break;
            }
        status.add(0,2);
        Log.v(LOG_TAG, "select mid");
    }

    public void selectBot(){
        for (int i=0;i<3;i++)
            if (status.get(i)==3) {
                status.remove(i);
                break;
            }
        status.add(0,3);
        Log.v(LOG_TAG, "select bot");
    }

    public ArrayList<Integer> getStatus(){
        return status;
    }
}
