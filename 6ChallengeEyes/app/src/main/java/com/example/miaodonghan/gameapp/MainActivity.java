package com.example.miaodonghan.gameapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button fruit;
    Button letter;
    Button clear;
    public static final String MyPREFERENCES = "Record" ;
    public static final String Category = "category";
    public static final  String Round = "round";
    public static final String Score ="score";

    public static final String PREFERENCES_record = "record";
    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        fruit = (Button)findViewById(R.id.fruit);
        fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //int currentScoree = sharedPreferences.getInt("score", 0);
                editor.putString(Category, "fruit");
                editor.putInt(Round, 1);
                //editor.putInt(Score, 0);
                editor.commit();

                Intent levels = new Intent(MainActivity.this,CategoryHandler.class);
                startActivity(levels);
            }
        });

        letter = (Button)findViewById(R.id.letter);
        letter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(Category,"letter");
                editor.putInt(Round, 1);
                //editor.putInt(Score, 0);
                editor.commit();

                Intent levels = new Intent(MainActivity.this,CategoryHandler.class);
                startActivity(levels);
            }
        });

        clear = (Button)findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putInt(Score, 0);
                editor.commit();
                Toast.makeText(MainActivity.this, "You clear your score!",

                        Toast.LENGTH_SHORT).show();
            }
        });


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
