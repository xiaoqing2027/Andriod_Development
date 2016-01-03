package com.example.miaodonghan.hw3_listview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

    int global_position=0;
    List<Map<String, Object>> l1 = new ArrayList<Map<String, Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listView = (ListView) findViewById(R.id.listView);

        //show substring
        List<String> s = new ArrayList<String>();
        List<Integer> imageID = new ArrayList<Integer>();
        for(int i=0;i<getData().size();i++){
            if(getData().get(i).get("text").toString().length()>32) {
                s.add(getData().get(i).get("text").toString().substring(0, 32) + "...");
            }else{
                s.add(getData().get(i).get("text").toString());
            }
            imageID.add(Integer.parseInt(getData().get(i).get("img").toString()));
        }

        for(int i=0;i<getData().size();i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", imageID.get(i));
            map.put("text", s.get(i));
            l1.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, l1,
                R.layout.item, new String[]{"img", "text", "n"}, new int[]{R.id.img, R.id.t, R.id.n});

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {

                onClickItem(view,position);

            }


        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View view, int position, long id) {

                //Toast.makeText(MainActivity.this,listView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                global_position = position;
                longClickItems(view, global_position);
                return true;
            }
        });
    }




    public final static String  selected_ID_text = "text";
    public final static String  selected_ID_date = "date";
    //create onClick Event
    public void onClickItem(View v,int position) {

        Intent intent = new Intent(MainActivity.this, Item1Class.class);
        Map<String,Object> o = (HashMap<String,Object>)getData().get(position);
        intent.putExtra(selected_ID_text, o.get("text").toString());
        intent.putExtra(selected_ID_date, o.get("date").toString());
        startActivity(intent);
    }


    //create OnLongClick Event
    public void longClickItems(View v, int position){
        final int p = position;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("are you sure you really want to delete item" + position + "?");

        alertDialogBuilder.setNegativeButton(R.string.negative,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       //stay on original screen, nothing changes
                    }
                });
        alertDialogBuilder.setPositiveButton(R.string.positive,

                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
                        list1 = l1;
                        list1.remove(p);

                        final ListView listView1 = (ListView) findViewById(R.id.listView);
                        SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, list1,
                                R.layout.item, new String[]{"img", "text", "n"}, new int[]{R.id.img, R.id.t, R.id.n});

                        listView1.setAdapter(adapter);


                    }
                });


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    // data part
    private List<Map<String, Object>> getData() {

        List<ListItemData> t = new ArrayList<ListItemData>();
        t.add(new ListItemData("Innovation distinguished between a leader and a follower.", R.drawable.g,"2010.8"));
        t.add(new ListItemData("People don\'t know what they want until you show it to them.", R.drawable.yahoo,"2013.8"));
        t.add(new ListItemData("The only way to be truly satisfied is to do what you believe is great work.", R.drawable.f,"2012.2"));
        t.add(new ListItemData("That\'s been one of my mantras -- focus and simplicity.", R.drawable.apple,"2009.6"));
        t.add(new ListItemData("Simple can be harder than complex: You have to work hard to get your thinking " +
                "clean to make it simple.", R.drawable.amozon,"2012.8"));
        t.add(new ListItemData("But it\'s worth it in the end because once you get there, you can move mountains.", R.drawable.ms,"2011.11"));
        t.add(new ListItemData("Great things in business are never done by one person, they\'re done by a team of people.", R.drawable.girl2,"2012.12"));
        t.add(new ListItemData("You can\'t connect the dots looking forward; you can only connect them looking backward.", R.drawable.cat,"2013.8"));
        t.add(new ListItemData("Your work is going to fill a large part of your life.", R.drawable.dog,"2012.8"));
        t.add(new ListItemData("I\'m the only person I know that\'s lost a quarter of a billion dollars in one year.", R.drawable.ding,"2009.9"));
        t.add(new ListItemData("It\'s more fun to be a pirate than join the Navy.", R.drawable.t,"2014.1"));
        t.add(new ListItemData("Everything is possible.", R.drawable.nike,"2012.1"));


        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        for (ListItemData li : t) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", li.getImageId());
            map.put("text", li.getT());
            map.put("date", li.getDate());

            list.add(map);
        }
        return list;



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

/*
        String[] t ={
                "I want to put a ding in the universe!",
                "People don\'t know what they want until you show it to them.",
                "The only way to be truly satisfied is to do what you believe is great work.",
                "That\'s been one of my mantras -- focus and simplicity.",
                "Simple can be harder than complex: You have to work hard to get your thinking clean to make it simple.",
                "But it\'s worth it in the end because once you get there, you can move mountains.",
                "Great things in business are never done by one person, they\'re done by a team of people.",
                "You can\'t connect the dots looking forward; you can only connect them looking backward.",
                "Your work is going to fill a large part of your life, and the only way to be truly satisfied is" +
                        " to do what you believe is great work.",
                "I\'m the only person I know that\'s lost a quarter of a billion dollars in one year.It\'s very character-building.",
                "It\'s more fun to be a pirate than join the Navy.",
                "Everything is possible."

        };
        int[] imageId ={
            R.drawable.g,
            R.drawable.g,
            R.drawable.g,
            R.drawable.g
        };

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("img",imageId[0]);
        map.put("text", t[0]);
        list.add(map);

        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("img",imageId[1]);
        map1.put("text", t[1]);
        list.add(map1);

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("img",imageId[2]);
        map2.put("text", t[2]);
        list.add(map2);

        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("img",imageId[3]);
        map3.put("text", t[3]);
        list.add(map3);

        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("img",imageId[4]);
        map4.put("text", t[4]);
        list.add(map4);


        Map<String, Object> map5 = new HashMap<String, Object>();
        map5.put("img",imageId[5]);
        map5.put("text", t[5]);
        list.add(map5);

        map = new HashMap<String, Object>();
        map.put("img",imageId[6]);
        map.put("text", t[6]);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img",imageId[7]);
        map.put("text", t[7]);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img",imageId[8]);
        map.put("text", t[8]);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img",imageId[9]);
        map.put("text", t[9]);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img",imageId[10]);
        map.put("text", t[10]);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img",imageId[11]);
        map.put("text", t[11]);
        list.add(map);

*/


     /*
    String[] t={"111","222","333","444","555","666","777","888","999","1010","110"};t
    */

