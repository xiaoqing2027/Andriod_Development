package com.example.miaodonghan.gameapp;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;


public class ImageAdapter extends BaseAdapter {


    DataProvider data;
    private Context mContext;
    //// references to our images
    private ArrayList<Integer> dataList;
    String level;


    public ImageAdapter(Context c, String category, String level, int round) {
        mContext = c;
        data = new DataProvider(c);
        dataList =  data.getRandomData(category, level, round);
        this.level = level;
    }

    public int getCount() {
        return dataList.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);

            //how to set it

            WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            int height = size.y;
            int horizonal_n = 0;
            switch(level) {
                case "entry":horizonal_n =3;  break;
                case "intermediate": horizonal_n =6;  break;
                case "advanced": horizonal_n =8;  break;
            }
            int unit_size =(width - 8*2) / horizonal_n;

            imageView.setLayoutParams(new GridView.LayoutParams(unit_size, unit_size));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(dataList.get(position));
        return imageView;


    }

    // put all cases of same level(advanced) in an arraylist

}