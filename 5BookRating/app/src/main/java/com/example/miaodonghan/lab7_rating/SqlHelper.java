package com.example.miaodonghan.lab7_rating;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Maggie on 11/second/15.
 */
public class SqlHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "BookDB";

    // Books table name
    private static final String TABLE_BOOKS = "books";
    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_RATING = "rating";
    private static final String KEY_Image= "imageId";


    String CREATE_BOOK_TABLE = "CREATE TABLE books ( " +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "title TEXT, "+
            "author TEXT, "+
            "rating TEXT,"+
            "imageId TEXT)";

    public SqlHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_BOOK_TABLE = "CREATE TABLE books ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, "+
                "author TEXT, "+
                "rating TEXT," +
                "imageId TEXT)";
        // create books table
        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS books");
        // create fresh books table
        this.onCreate(db);
    }

    /*CRUD operations (create "add", read "get", update, delete) */
    public void addBook(Book book){
        Log.d("addBook", book.toString());
        // first. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // second. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, book.getTitle()); // get title
        values.put(KEY_AUTHOR, book.getAuthor());// get author
        values.put(KEY_RATING, book.getRating());// get rating
        values.put(KEY_Image, book.getImageId());// get imageName
        // third. insert
        db.insert(TABLE_BOOKS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/values
        // four. Close dbase
        db.close();
    }
    // Get All Books
    public List<Book> getAllBooks() {
        List<Book> books = new LinkedList<Book>();
        // first. build the query
        String query = "SELECT * FROM " + TABLE_BOOKS;
        // second. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // third. go over each row, build book and add it to list
        Book book = null;
        if (cursor.moveToFirst()) {
            do {
                book = new Book();
                book.setId(Integer.parseInt(cursor.getString(0)));
                book.setTitle(cursor.getString(1));
                book.setAuthor(cursor.getString(2));
                book.setRating(cursor.getString(3));
                book.setImageId(Integer.parseInt(cursor.getString(4)));
                // Add book to books
                books.add(book);
            } while (cursor.moveToNext());
        }
        Log.d("getAllBooks()", books.toString());

        return books; // return books
    }
    // Updating single book
    public int updateBook(Book book, String newTitle, String newAuthor, String newRating, int newImage) {
        // first. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // second. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
       // values.put("title", book.getTitle()); // get title
        //values.put("author", book.getAuthor()); // get author
        values.put("title", newTitle);
        values.put("author", newAuthor);
        values.put("rating", newRating);
        values.put("imageId", newImage);
        // third. updating row
        int i = db.update(TABLE_BOOKS, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(book.getId()) }); //selection args
        // four. close dbase

        db.close();
        Log.d("UpdateBook", book.toString());
        return i;
    }


    // Deleting single book
    public void deleteBook(Book book) {
        // first. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // second. delete
        db.delete(TABLE_BOOKS,
                KEY_ID+" = ?",
                new String[] { String.valueOf(book.getId()) });
        // third. close
        db.close();
        Log.d("deleteBook", book.toString());
    }

    public int getIds(Book book)
    {
        String selectQuery = "SELECT id FROM books";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c = database.rawQuery(selectQuery, null);
        c.moveToFirst();
        int total = c.getCount();

        Log.d("Log total in getIds():", total +"");
        return total;

    }
}
