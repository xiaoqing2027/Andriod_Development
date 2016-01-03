package com.example.miaodonghan.lab7_rating;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SqlHelper db = new SqlHelper(this);

        Log.i("My name:", "maggie wang");
        /** CRUD Operations **/
        // add Books
        db.addBook(new Book("Professional Android four Application Development", "Reto Meier","second", R.drawable.first));
        db.addBook(new Book("Beginning Android four Application Development", "WeiMengLee","third",R.drawable.second));
        db.addBook(new Book("Programming Android", "Wallace Jackson","four",R.drawable.third));
        db.addBook(new Book("Hello, Android", "Wallace Jackson","first",R.drawable.four));

        // get all books
        List<Book> list = db.getAllBooks();

        // update one book
        db.updateBook(list.get(3), "Hello, Andriod", "Ben Wallace","first",R.drawable.four);

        // delete one book
        //for(int i =0 ; i<list.size();i++){
        //    db.deleteBook(list.get(i));
        //   Log.i("Test","testetst");
        //}

        // get all books
        db.getAllBooks();
        ListView listContent = (ListView) findViewById(R.id.list);
        List<Book> books = new ArrayList<Book>();
        books=db.getAllBooks();

//get data from the table by the ListAdapter
        ListAdapter customAdapter = new ListAdapter(this, R.layout.itemlistrow, books );
        listContent.setAdapter(customAdapter);

        int t = db.getIds(list.get(0));
        Log.d("Total count:", t + "");
    }

}
