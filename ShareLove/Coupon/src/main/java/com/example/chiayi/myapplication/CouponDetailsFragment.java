package com.example.chiayi.myapplication;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by chiayi on 16/7/29.
 */
public class CouponDetailsFragment extends Fragment {


    private static final String ARGUMENT_NAME = "CouponName";
    private static final String ARGUMENT_PRICE = "Price";
    private static final String ARGUMENT_IMAGE_URL = "ImageURL";
    private static final String ARGUMENT_INFO = "Info";
    private static final String ARGUMENT_AMOUNT = "Amount";


    public static CouponDetailsFragment newInstance(String CouponName, String Price, String ImageURL,String Info,String Amount){

        final Bundle args = new Bundle();
        args.putString(ARGUMENT_NAME,CouponName);
        args.putString(ARGUMENT_PRICE,Price);
        args.putString(ARGUMENT_IMAGE_URL,ImageURL);
        args.putString(ARGUMENT_INFO,Info);
        args.putString(ARGUMENT_AMOUNT,Amount);
        final CouponDetailsFragment fragment= new CouponDetailsFragment();
        fragment.setArguments(args);

        return fragment;

    }

    public CouponDetailsFragment(){
    //empty constructors
    }

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        final View view = inflater.inflate(R.layout.coupon_details_fragment, container, false);
        final TextView nameTextView = (TextView) view.findViewById(R.id.textView4);
        final TextView priceTextView = (TextView) view.findViewById(R.id.textView6);
        final TextView amountTextView = (TextView) view.findViewById(R.id.textView8);
        final TextView infoTextView = (TextView) view.findViewById(R.id.textView12);
        final ImageView couponImage = (ImageView) view.findViewById(R.id.imageView);


        final Bundle args = getArguments();
        nameTextView.setText(args.getString(ARGUMENT_NAME));
        priceTextView.setText(args.getString(ARGUMENT_PRICE));
        DownloadImageTask downloadImage = new DownloadImageTask(couponImage);
        downloadImage.execute(args.getString(ARGUMENT_IMAGE_URL));
        amountTextView.setText(args.getString(ARGUMENT_AMOUNT));
        infoTextView.setText(args.getString(ARGUMENT_INFO));


        return view;
    }






}
