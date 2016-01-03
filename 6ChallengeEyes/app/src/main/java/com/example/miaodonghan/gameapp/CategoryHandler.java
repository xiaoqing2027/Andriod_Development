package com.example.miaodonghan.gameapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by miaodonghan on 11/21/15.
 */
public class CategoryHandler extends Activity{

    Button homePage;
    Button entry;
    Button intermediate;
    Button advanced;
    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "Record" ;
    public static final String Level = "level";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        homePage=(Button)findViewById(R.id.homePage);
        entry = (Button)findViewById(R.id.button3);
        intermediate =(Button)findViewById(R.id.button4);
        advanced =(Button)findViewById(R.id.button5);

        advanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Level,"advanced");
                editor.commit();
                String advancedbuttonText = advanced.getText().toString();
                Intent advanced = new Intent(CategoryHandler.this, Fruit_LevelPageHandler.class);
                advanced.putExtra("advancedbuttonText",advancedbuttonText);
                startActivity(advanced);
            }
        });
        intermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Level,"intermediate");
                editor.commit();
                Intent advanced = new Intent(CategoryHandler.this, Fruit_LevelPageHandler.class);
                startActivity(advanced);
            }
        });
        entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Level,"entry");
                editor.commit();
                Intent advanced = new Intent(CategoryHandler.this, Fruit_LevelPageHandler.class);
                startActivity(advanced);
            }
        });

        homePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent advanced = new Intent(CategoryHandler.this, MainActivity.class);
                startActivity(advanced);
            }
        });



    }
}
