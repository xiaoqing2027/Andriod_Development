package com.example.miaodonghan.quizapp_hw5;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by miaodonghan on 10/31/15.
 */
public class RetrieveTextTask extends AsyncTask<String, Integer, List<String>> {

    @Override
    protected List<String> doInBackground(String... urls) {
        List<String> stringList = new ArrayList<String>();

        try {
            URL textUrl= new URL(urls[0]);
            BufferedReader bufferReader = new BufferedReader(
                    new InputStreamReader(textUrl.openStream()));
            String StringBuffer;

            while ((StringBuffer = bufferReader.readLine()) != null) {
                Log.i("test>>", ">>>>>>>" + StringBuffer);
                stringList.add(StringBuffer);
            }
            bufferReader.close();
            return stringList;
        } catch (Exception e) {
            Log.e("BG_TASK", e.toString());
            return null;
        }
    }
}
