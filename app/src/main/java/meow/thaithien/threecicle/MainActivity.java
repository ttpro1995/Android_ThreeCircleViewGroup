package meow.thaithien.threecicle;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    ImageButton red;
    ImageButton blue;
    ImageButton green;
    FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ThreeCycle threeCycle = new ThreeCycle(
                getResources().getDrawable(R.drawable.ic_dino1),
                getResources().getDrawable(R.drawable.ic_dino2),
                getResources().getDrawable(R.drawable.ic_dino3),
                getResources().getDrawable(R.drawable.ic_animals1),
                getResources().getDrawable(R.drawable.ic_animals2),
                getResources().getDrawable(R.drawable.ic_animals3),
                getResources().getDrawable(R.drawable.ic_verhi1),
                getResources().getDrawable(R.drawable.ic_verhi2),
                getResources().getDrawable(R.drawable.ic_verhi3)
        );
        /*ThreeCycle threeCycle = new ThreeCycle(
                getResources().getDrawable(R.drawable.red_big),
                getResources().getDrawable(R.drawable.red_med),
                getResources().getDrawable(R.drawable.red_small),
                getResources().getDrawable(R.drawable.blue_big),
                getResources().getDrawable(R.drawable.blue_med),
                getResources().getDrawable(R.drawable.blue_small),
                getResources().getDrawable(R.drawable.green_big),
                getResources().getDrawable(R.drawable.green_med),
                getResources().getDrawable(R.drawable.green_small)
        );*/

        FrameThreeCycleViewGroup threeCycleViewGroup = new FrameThreeCycleViewGroup(this,threeCycle);
        threeCycleViewGroup.setTopOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"top",Toast.LENGTH_SHORT).show();
            }
        });
        threeCycleViewGroup.setMidOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "mid", Toast.LENGTH_SHORT).show();
            }
        });
        threeCycleViewGroup.setBotOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"bot",Toast.LENGTH_SHORT).show();
            }
        });
        threeCycleViewGroup.setImage();



        container = (FrameLayout) findViewById(R.id.container);
        container.addView(threeCycleViewGroup);

      /*  ButterKnife.bind(this);
        red= (ImageButton) findViewById(R.id.red);
         blue = (ImageButton) findViewById(R.id.blue);
         green = (ImageButton) findViewById(R.id.green);
        container = (FrameLayout) findViewById(R.id.container);
*/
        /*red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) red.getLayoutParams();
                container.removeView(red);
                red = new ImageButton(MainActivity.this);
                red.setLayoutParams(layoutParams);

                red.setBackground(null);
                container.addView(red);
                red.setImageDrawable(getResources().getDrawable(R.drawable.red150));
            }
        });
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) blue.getLayoutParams();
                container.removeView(blue);
                blue = new ImageButton(MainActivity.this);
                blue.setLayoutParams(layoutParams);
                blue.setBackground(null);
                container.addView(blue);
                blue.setImageDrawable(getResources().getDrawable(R.drawable.blue150));
            }
        });
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) green.getLayoutParams();
                container.removeView(green);
                green = new ImageButton(MainActivity.this);
                green.setLayoutParams(layoutParams);

                green.setBackground(null);
                container.addView(green);
                green.setImageDrawable(getResources().getDrawable(R.drawable.green150));
            }
        });*/

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
