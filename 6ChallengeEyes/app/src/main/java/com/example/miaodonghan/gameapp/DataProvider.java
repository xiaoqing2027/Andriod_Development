package com.example.miaodonghan.gameapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class DataProvider {

    public DataProvider(Context c) {
        this.c  = c;
    }
    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "Record" ;
    public static final  String Random = "random";
    Context c;
    Random randomGenerator = new Random();

    public ArrayList<Integer> getRandomData(String currentCategory, String currentLevel, int Round){
//        String picA = currentCategory + "_" + currentLevel + "_" + Round + "_a";
//        String picB = currentCategory + "_" + currentLevel + "_" + Round + "_b";
          String picA = currentCategory + "_" + Round + "_a";
          String picB = currentCategory + "_" + Round + "_b";

        ArrayList<Integer> result;

        int length = 0;

        switch(currentLevel) {
            case "entry":
                length = 3*4;
                break;
            case "intermediate":
                length = 6*8;
                break;
            case "advanced":
                length = 8*14;
                break;
        }

        result = new ArrayList<>(length);

        int rand = randomGenerator.nextInt(Integer.MAX_VALUE) % length;

        int resIdA = c.getResources().getIdentifier(picA, "drawable", c.getPackageName());
        int resIdB = c.getResources().getIdentifier(picB, "drawable", c.getPackageName());

        sharedPreferences = c.getSharedPreferences(MyPREFERENCES, c.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("random", rand);
        editor.commit();
        for(int i = 0; i< length; i++){
            if(i == rand) {
                result.add(resIdB);
            }
            else {
                result.add(resIdA);
            }
        }
        return result;
    }


}
