package com.example.jiarou.sharelove;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Peter on 2016/8/9.
 */
public class VendedInfoFragment extends Fragment {
    final static String DB_URL = "https://vendor-5acbc.firebaseio.com/Vendors";
    String imgurURL = "http://i.imgur.com/";

    private static final String ARGUMENT_TITLE = "VendorTitle";
    /*
    private static final String ARGUMENT_VENDERURL = "VendorURL";
    private static final String ARGUMENT_PHONE = "VendorPhone";
    private static final String ARGUMENT_REMARK = "TimeRemark";
    private static final String ARGUMENT_MON = "MonTime";
    private static final String ARGUMENT_TUE = "TueTime";
    private static final String ARGUMENT_WED = "WedTime";
    private static final String ARGUMENT_THU = "ThuTime";
    private static final String ARGUMENT_FRI = "FriTime";
    private static final String ARGUMENT_SAT = "SatTime";
    private static final String ARGUMENT_SUN = "SunTime";
    private static final String ARGUMENT_ADDRESS = "VendorAddress";
    private static final String ARGUMENT_STORY = "VendorStory";
    */

    CallbackManager callbackManager;
    ShareDialog shareDialog;

    Bitmap bitmap;
    String forShareUse;

    @Override
    public void onCreate(Bundle savedInstanceState){
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        super.onCreate(savedInstanceState);

        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);


    }

    public static VendedInfoFragment newInstance(String vendorTitle/*, String vendorURL, String vendorPhone, String timeRemark,
                                                 String monTime, String tueTime, String wedTime, String thuTime, String friTime,
                                                 String satTime, String sunTime, String vendorAddress, String vendorStory*/){
        final Bundle args = new Bundle();
        args.putString(ARGUMENT_TITLE, vendorTitle);
        /*
        args.putString(ARGUMENT_VENDERURL, vendorURL);
        args.putString(ARGUMENT_PHONE, vendorPhone);
        args.putString(ARGUMENT_REMARK, timeRemark);
        args.putString(ARGUMENT_MON, monTime);
        args.putString(ARGUMENT_TUE, tueTime);
        args.putString(ARGUMENT_WED, wedTime);
        args.putString(ARGUMENT_THU, thuTime);
        args.putString(ARGUMENT_FRI, friTime);
        args.putString(ARGUMENT_SAT, satTime);
        args.putString(ARGUMENT_SUN, sunTime);
        args.putString(ARGUMENT_ADDRESS, vendorAddress);
        args.putString(ARGUMENT_STORY, vendorStory);
        */

        final VendedInfoFragment fragment = new VendedInfoFragment();
        fragment.setArguments(args);

        return fragment;

    }

    public VendedInfoFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(com.example.peter.focus.R.layout.vender_info_fragment, container, false);

        final TextView titleTextView = (TextView)view.findViewById(com.example.peter.focus.R.id.titleTextView);

        final ImageView vendorImageView = (ImageView)view.findViewById(com.example.peter.focus.R.id.vendorImageView);

        final TextView phoneTextView = (TextView)view.findViewById(com.example.peter.focus.R.id.phoneTextView);
        final TextView remarkTextView = (TextView)view.findViewById(com.example.peter.focus.R.id.remarkTextView);
        final TextView monTextView = (TextView)view.findViewById(com.example.peter.focus.R.id.monTextView);
        final TextView tueTextView = (TextView)view.findViewById(com.example.peter.focus.R.id.tueTextView);
        final TextView wedTextView = (TextView)view.findViewById(com.example.peter.focus.R.id.wedTextView);
        final TextView thuTextView = (TextView)view.findViewById(com.example.peter.focus.R.id.thuTextView);
        final TextView friTextView = (TextView)view.findViewById(com.example.peter.focus.R.id.friTextView);
        final TextView satTextView = (TextView)view.findViewById(com.example.peter.focus.R.id.satTextView);
        final TextView sunTextView = (TextView)view.findViewById(com.example.peter.focus.R.id.sunTextView);
        final TextView addressTextView = (TextView)view.findViewById(com.example.peter.focus.R.id.addressTextView);
        final TextView storyTextView = (TextView)view.findViewById(com.example.peter.focus.R.id.storyTextView);

        final ImageView fbShare = (ImageView)view.findViewById(com.example.peter.focus.R.id.fbShareImageView);
        fbShare.bringToFront();

        final Bundle args = getArguments();
        titleTextView.setText(args.getString(ARGUMENT_TITLE));
        /*
        DownloadImageTask downloadImage = new DownloadImageTask(vendorImageView);
        downloadImage.execute(args.getString(ARGUMENT_VENDERURL));
        phoneTextView.setText(args.getString(ARGUMENT_PHONE));
        remarkTextView.setText(args.getString(ARGUMENT_REMARK));
        monTextView.setText(args.getString(ARGUMENT_MON));
        tueTextView.setText(args.getString(ARGUMENT_TUE));
        wedTextView.setText(args.getString(ARGUMENT_WED));
        thuTextView.setText(args.getString(ARGUMENT_THU));
        friTextView.setText(args.getString(ARGUMENT_FRI));
        satTextView.setText(args.getString(ARGUMENT_SAT));
        sunTextView.setText(args.getString(ARGUMENT_SUN));
        addressTextView.setText(args.getString(ARGUMENT_ADDRESS));
        storyTextView.setText(args.getString(ARGUMENT_STORY));
        */

        Firebase.setAndroidContext(this.getActivity());
        final Firebase vendor2 = new Firebase(DB_URL);
        String mark = args.getString(ARGUMENT_TITLE);

        Query focusVendor2 = vendor2.orderByChild("Information/Name").equalTo(mark);

        focusVendor2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String picId = (String)dataSnapshot.child("Photos").child("Photo_ID").getValue();
                String pic = imgurURL + picId + ".jpg";
               DownloadImageTask downloadImage = new DownloadImageTask(vendorImageView);
                downloadImage.execute(pic);

                forShareUse = pic;

                String phone = (String) dataSnapshot.child("Information").child("Phone").getValue();
                String remark = (String) dataSnapshot.child("Open_Days").child("Remark").getValue();
                String monOpen = (String) dataSnapshot.child("Open_Days").child("Mon").child("Open_At").getValue();
                String monClose = (String) dataSnapshot.child("Open_Days").child("Mon").child("Close_At").getValue();
                String mon = monOpen + "~" + monClose;
                String tueOpen = (String) dataSnapshot.child("Open_Days").child("Tue").child("Open_At").getValue();
                String tueClose = (String) dataSnapshot.child("Open_Days").child("Tue").child("Close_At").getValue();
                String tue = tueOpen + "~" + tueClose;
                String wedOpen = (String) dataSnapshot.child("Open_Days").child("Wed").child("Open_At").getValue();
                String wedClose = (String) dataSnapshot.child("Open_Days").child("Wed").child("Close_At").getValue();
                String wed = wedOpen + "~" + wedClose;
                String thuOpen = (String) dataSnapshot.child("Open_Days").child("Thu").child("Open_At").getValue();
                String thuClose = (String) dataSnapshot.child("Open_Days").child("Thu").child("Close_At").getValue();
                String thu = thuOpen + "~" + thuClose;
                String friOpen = (String) dataSnapshot.child("Open_Days").child("Fri").child("Open_At").getValue();
                String friClose = (String) dataSnapshot.child("Open_Days").child("Fri").child("Close_At").getValue();
                String fri = friOpen + "~" + friClose;
                String satOpen = (String) dataSnapshot.child("Open_Days").child("Sat").child("Open_At").getValue();
                String satClose = (String) dataSnapshot.child("Open_Days").child("Sat").child("Close_At").getValue();
                String sat = satOpen + "~" + satClose;
                String sunOpen = (String) dataSnapshot.child("Open_Days").child("Sun").child("Open_At").getValue();
                String sunClose = (String) dataSnapshot.child("Open_Days").child("Sun").child("Close_At").getValue();
                String sun = sunOpen + "~" + sunClose;
                String address = (String) dataSnapshot.child("Location").child("Address").getValue();
                String story = (String) dataSnapshot.child("Information").child("Introduction").getValue();

                phoneTextView.setText(phone);
                remarkTextView.setText(remark);
                monTextView.setText(mon);
                tueTextView.setText(tue);
                wedTextView.setText(wed);
                thuTextView.setText(thu);
                friTextView.setText(fri);
                satTextView.setText(sat);
                sunTextView.setText(sun);
                addressTextView.setText(address);
                storyTextView.setText(story);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        fbShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {
                        Toast.makeText(getContext(), "success", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getContext(), "cancel", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(getContext(), "error", Toast.LENGTH_LONG).show();
                    }
                });

                if(ShareDialog.canShow(SharePhotoContent.class)){
                    //Bitmap image = getBitmap(forShareUse);

                    ShareLinkContent content = new ShareLinkContent.Builder()
                            .setContentTitle("Hello Facebook")
                            .setImageUrl(Uri.parse("http://developers.facebook.com/android"))
                            .build();

                    /*
                    SharePhoto photo = new SharePhoto.Builder().setImageUrl(Uri.parse(forShareUse)).build();
                    SharePhotoContent content = new SharePhotoContent
                            .Builder().addPhoto(photo).build();
                            */

                    shareDialog.show(content);

                }else {
                    Toast.makeText(getContext(), "QQ", Toast.LENGTH_LONG).show();
                }

            }
        });

        return view;
    }

    public Bitmap getBitmap(String... params){
        HttpURLConnection connection;
        final Bitmap myBitmap;

        try{
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream input = connection.getInputStream();
            myBitmap = BitmapFactory.decodeStream(input);
            this.bitmap = myBitmap;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
