package com.example.chiayi.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by chiayi on 16/7/24.
 */
public class CustomAdapter extends BaseAdapter {

    final ArrayList<String> couponName;
    final ArrayList<Long> couponPrice;
    Context context;
    int [] imageId;

    public CustomAdapter(Context c, final ArrayList<String> couponName, final ArrayList<Long> couponPrice,int[] imageId){

        context = c;
        this.imageId=imageId;
        this.couponPrice=couponPrice;
        this.couponName=couponName;


    }


    @Override
    public int getCount() {
        return couponName.size();
    }

    @Override
    public Object getItem(int position) {
        return couponName.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View grid;
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            grid = inflater.inflate(R.layout.custom_gridview, null);
            TextView textView = (TextView) grid.findViewById(R.id.textView1);
            TextView textView2 = (TextView) grid.findViewById(R.id.textView2);
            ImageView imageView = (ImageView)grid.findViewById(R.id.imageView1);

            String name = couponName.get(position);
            textView.setText(name);


            String price = String.valueOf(couponPrice.get(position));
            textView2.setText(price);



        imageView.setImageResource(imageId[position]);


        grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You Clicked " + couponName.get(position), Toast.LENGTH_LONG).show();
            }
        });

        return grid;


    }
}
