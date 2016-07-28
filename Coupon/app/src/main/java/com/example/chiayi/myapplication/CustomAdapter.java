package com.example.chiayi.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by chiayi on 16/7/24.
 */
public class CustomAdapter extends BaseAdapter {

    final ArrayList<String> couponName;
    final ArrayList<Long> couponPrice;
    Context context;
    ArrayList<String> couponImage;
    Bitmap bitmap;

    public CustomAdapter(Context c, final ArrayList<String> couponName, final ArrayList<Long> couponPrice,ArrayList<String> couponImage){

        context = c;
        this.couponImage=couponImage;
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


            String couponURL = couponImage.get(position);
            DownloadImageTask downloadImage = new DownloadImageTask(imageView);
            downloadImage.execute(couponURL);




            //bitmap=getBitmapFromURL(couponURL);
            //imageView.setImageBitmap(bitmap);


        grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You Clicked " + couponName.get(position), Toast.LENGTH_LONG).show();
            }
        });


        return grid;


    }

    }





        class DownloadImageTask extends AsyncTask<String,Integer,Bitmap> {

            ImageView imageView;
            Bitmap bitmap;

            public DownloadImageTask(ImageView imageView) {
                this.imageView = imageView;
            }

            @Override
            protected Bitmap doInBackground(String... params) {
                HttpURLConnection connection;
                final Bitmap myBitmap;

                try {
                    URL url = new URL(params[0]);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    //connection.setDoInput(true);
                    //connection.connect();
                    InputStream input = connection.getInputStream();
                    myBitmap = BitmapFactory.decodeStream(input);
                    this.bitmap=myBitmap;

                } catch (java.io.IOException e) {
                    e.printStackTrace();
                    return null;

                }

                return bitmap;
            }

            protected void onPostExecute(Bitmap bitmap) {

               bitmap=this.bitmap;
                this.imageView.setImageBitmap(bitmap);

            }

        }



