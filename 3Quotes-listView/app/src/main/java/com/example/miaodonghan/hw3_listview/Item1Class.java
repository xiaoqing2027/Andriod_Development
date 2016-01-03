package com.example.miaodonghan.hw3_listview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by miaodonghan on 10/10/15.
 */
public class Item1Class extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item1);

        String selectedItemText = getIntent().getStringExtra(MainActivity.selected_ID_text);
        String selectedItemDate = getIntent().getStringExtra(MainActivity.selected_ID_date);

        TextView selectedTextView = (TextView)findViewById(R.id.item1_textView);
        TextView selectedDate = (TextView)findViewById(R.id.date);

        selectedTextView.setText( selectedItemText);
        selectedDate.setText("---"+selectedItemDate);
    }


}
