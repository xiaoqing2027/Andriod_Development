package com.example.miaodonghan.hw2_test;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import java.text.DecimalFormat;


public class MainActivity extends Activity implements OnSeekBarChangeListener{

    private SeekBar seekBar;
    private TextView t7_min;
    private TextView t8_max;
    private TextView t3_F;
    private TextView t5_C;
    private TextView t6_color;
    private ImageView image;
    private Drawable spring;
    private Drawable summer;
    private Drawable winter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);

        t3_F = (TextView)findViewById(R.id.textView3);
        t5_C = (TextView)findViewById(R.id.textView5);
        t6_color = (TextView)findViewById(R.id.textView6);
        image = (ImageView)findViewById(R.id.imageView);

        t6_color.setBackgroundColor(Color.BLUE);
        t6_color.setText("Too Cold");
        image.setBackgroundResource(R.drawable.winter);

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


    public void onStartTrackingTouch(SeekBar seekBar){

    }

    public void onStopTrackingTouch(SeekBar seekBar){

        //seekBar.setSecondaryProgress(seekBar.getProgress());


    }

    public void onProgressChanged(SeekBar seekBar, int p, boolean fromUser){

        double progress =Double.parseDouble(new DecimalFormat("###.##").format((p*1.00)/1000.00*200.00));
        double c = Double.parseDouble(new DecimalFormat("###.##").format(5.00/9.00*(progress-32)));
        String t3 = Double.toString(progress);
        String t5 = Double.toString(c);

        t3_F.setText(t3);
        t5_C.setText(t5);


        if(p<200){
            t6_color.setBackgroundColor(Color.BLUE);
            t6_color.setText("Too Cold");
            image.setBackgroundResource(R.drawable.winter);
        }else if(p<450){
            t6_color.setBackgroundColor(Color.GREEN);
            t6_color.setText("Just right");
            image.setBackgroundResource(R.drawable.spring);
        }else{
            t6_color.setBackgroundColor(Color.RED);
            t6_color.setText("Too Hot");
            image.setBackgroundResource(R.drawable.summer);
        }

    }

}
