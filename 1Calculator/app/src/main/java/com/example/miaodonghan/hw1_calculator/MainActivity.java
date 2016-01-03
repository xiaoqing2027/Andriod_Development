package com.example.miaodonghan.hw1_calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.text.DecimalFormat;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


    public void onButtonClick(View v) {

        double meal=0.0, t2=0.0, t3=0.0, t4,t5;

        EditText mealCost = (EditText) findViewById(R.id.editText);
        EditText tax = (EditText) findViewById(R.id.editText2);
        EditText tip = (EditText) findViewById(R.id.editText3);
        EditText total = (EditText) findViewById(R.id.editText4);

        String  mc = mealCost.getText().toString();
        String  ti = tip.getText().toString();
        String  ta = tax.getText().toString();
        if(mc.isEmpty()){
            total.setText("please input meal cost");
        }
        if(ti.isEmpty()){
            total.setText("please input tax percentage");
        }
        if(ta.isEmpty()){
            total.setText("please input tip percentage");
        }

        //if(mc != "" && ti != "" && ta != "") {
            meal = Double.parseDouble(mealCost.getText().toString());
            t2 = Double.parseDouble(tax.getText().toString());
            t3 = Double.parseDouble(tip.getText().toString());
            t4 = meal * (1 + (t2 + t3) * 0.01);
            t5 = Double.parseDouble(new DecimalFormat("##.##").format(t4));
            total.setText("" + Double.toString(t5));
        //}




    }

}
