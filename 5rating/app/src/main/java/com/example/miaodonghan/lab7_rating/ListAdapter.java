package com.example.miaodonghan.lab7_rating;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Maggie on 11/10/15.
 */
public class ListAdapter extends ArrayAdapter<Book> {
    private List<Book> items;

    public ListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }
    public ListAdapter(Context context, int resource, List<Book> items) {
        super(context, resource, items);

        this.items = items;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.itemlistrow, null);
        }
        Book p = getItem(position);
        if (p != null) {
            TextView tt = (TextView) v.findViewById(R.id._id);
            TextView tt1 = (TextView) v.findViewById(R.id.title);
            TextView tt3 = (TextView) v.findViewById(R.id.author);
            RatingBar rb = (RatingBar) v.findViewById(R.id.rating);
            ImageView image= (ImageView) v.findViewById(R.id.imageView);

            if (tt != null) {
                tt.setText("" + p.getId());
            }
            if (tt1 != null) {
                tt1.setText(p.getTitle());
            }
            if (tt3 != null) {
                tt3.setText(p.getAuthor());
            }
            if (rb != null) {
                float rating = Float.parseFloat(p.getRating());
                rb.setRating(rating);
            }else{
                float rating = Float.parseFloat("0");
                rb.setRating(rating);

            }
            if(image != null){
                int imageId =p.getImageId();
                image.setImageResource(imageId);
            }
        }
        return v;
    }
}