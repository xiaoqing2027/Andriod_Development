package com.example.miaodonghan.lab7_rating;

/**
 * Created by Maggie on 11/second/15.
 */
public class Book {

    private int id;
    private String title;
    private String author;
    private String rating;
    private int imageId;




    public Book(){

    }
    public Book(String title, String author,String rating, int imageId){
        super();
        this.title = title;
        this.author = author;
        this.rating = rating;
        this.imageId =imageId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setRating(String rating) { this.rating = rating; }

    public String getRating() {return rating;}

    public void setImageId(int imageId){ this.imageId = imageId;}

    public int getImageId(){ return imageId;}

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", author=" + author
                + ", rating=" + rating + ", imageId=" + imageId +"]";
    }
}
