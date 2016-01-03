package com.example.miaodonghan.hw3_listview;

/**
 * Created by miaodonghan on 9/24/15.
 */
public class ListItemData {
    String t;
    int imageId;
    String date;
    public ListItemData(String s, int i,String d){
        t  = s;
        imageId = i;
        date = d;
    }

    public void setT(String t) {
        this.t = t;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
    public void setDate(String date) {
        this.date =date;
    }

    public String getT() {
        return t;
}

public int getImageId() {
        return imageId;
        }
    public String getDate() {
        return date;
    }
}
