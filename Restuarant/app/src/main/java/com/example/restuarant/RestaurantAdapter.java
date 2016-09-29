package com.example.restuarant;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class RestaurantAdapter extends ArrayAdapter {


    List<Restaurant> mData;
    Context mContext;
    int resource;
    ImageView img1,img2;

    public RestaurantAdapter(Context context, int resource, List objects)
    {
        super(context, resource, objects);
        mContext=context;
        this.resource=resource;
        mData=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource,parent,false);
        }

        Restaurant c=mData.get(position);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView address= (TextView) convertView.findViewById(R.id.address);
        TextView type = (TextView) convertView.findViewById(R.id.textView6);
        RatingBar rb = (RatingBar) convertView.findViewById(R.id.rating);
         img1 = (ImageView) convertView.findViewById(R.id.imageView);
         img2 = (ImageView) convertView.findViewById(R.id.imageView2);

        name.setText(c.getName());
        address.setText(c.getAddress());
        type.setText(c.getCategory());
        rb.setRating(c.getRatings());

        return convertView;
    }




}
