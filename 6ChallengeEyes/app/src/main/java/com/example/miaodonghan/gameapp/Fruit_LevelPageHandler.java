package com.example.miaodonghan.gameapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


public class Fruit_LevelPageHandler extends Activity {
    Button pause;
    TextView score;
    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "Record" ;
    public static final  String Round = "round";

    String currentCategory ="";
    String currentLevel ="";
    int currentRound=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advan_one);




        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String currentCategory = sharedPreferences.getString("category", "fruit");
        String currentLevel =sharedPreferences.getString("level","entry");
        int currentRound = sharedPreferences.getInt("round", 1);
        int currentScore = sharedPreferences.getInt("score",0);
        score = (TextView)findViewById(R.id.score);
        score.setText("Score: " + currentScore);


        GridView gridview = (GridView) findViewById(R.id.advanceOne);
        switch(currentLevel) {
            case "entry": gridview.setNumColumns(3);  break;
            case "intermediate": gridview.setNumColumns(6);  break;
            case "advanced": gridview.setNumColumns(8);  break;
        }
        gridview.setAdapter(new ImageAdapter(this,currentCategory,currentLevel,currentRound));



        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                int currentRandom =sharedPreferences.getInt("random", 1);
                int currentRoundd = sharedPreferences.getInt("round", 1);
                int currentScoree = sharedPreferences.getInt("score", 0);
                Log.d(">>>>>>>", currentRandom+">>>>>>");
                Log.d(">>>>>>>round", currentRoundd + ">>>>>>");
                if (position == currentRandom) {
                    Toast.makeText(Fruit_LevelPageHandler.this, "Congratulations!",
                            Toast.LENGTH_SHORT).show();
                    if(currentRoundd == 4){
                        Intent toHomePage = new Intent(Fruit_LevelPageHandler.this, CategoryHandler.class);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt(Round, 1);
                        editor.commit();

                        startActivity(toHomePage);
                    }else{
                        Intent thisPage = new Intent(Fruit_LevelPageHandler.this, Fruit_LevelPageHandler.class);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt(Round, ++currentRoundd);
                        editor.putInt("score", ++currentScoree);
                        editor.commit();

                        startActivity(thisPage);
                    }


                } else {
                    Toast.makeText(Fruit_LevelPageHandler.this, "Try again!",
                           // Toast.makeText(Fruit_LevelPageHandler.this, "Try again!" + position,
                                    Toast.LENGTH_SHORT).show();
                }

            }
        });



        pause = (Button)findViewById(R.id.pause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Fruit_LevelPageHandler.this);
                alertDialogBuilder.setTitle("Leave or not");
                alertDialogBuilder.setMessage("Do you want to continue or exit?");
                alertDialogBuilder.setPositiveButton("Resume", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        // do nothing, just stay on original page

                    }


                });
                // set negative button: No message
                alertDialogBuilder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {
                        Intent toHomePage = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(toHomePage);
                    }


                });
                alertDialogBuilder.show();

            }


        });





    }
}
