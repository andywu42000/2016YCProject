package com.example.peter.focus;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Peter on 2016/8/9.
 */
public class VenderInfoFragment extends Fragment {

    private static final String ARGUMENT_TITLE = "VendorTitle";
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

    public static VenderInfoFragment newInstance(String vendorTitle, String vendorURL, String vendorPhone, String timeRemark,
                                                 String monTime, String tueTime, String wedTime, String thuTime, String friTime,
                                                 String satTime, String sunTime, String vendorAddress, String vendorStory){
        final Bundle args = new Bundle();
        args.putString(ARGUMENT_TITLE, vendorTitle);
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

        final VenderInfoFragment fragment = new VenderInfoFragment();
        fragment.setArguments(args);

        return fragment;

    }

    public VenderInfoFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.vender_info_fragment, container, false);
        final TextView titleTextView = (TextView)view.findViewById(R.id.titleTextView);
        final ImageView vendorImageView = (ImageView)view.findViewById(R.id.vendorImageView);
        final TextView phoneTextView = (TextView)view.findViewById(R.id.phoneTextView);
        final TextView remarkTextView = (TextView)view.findViewById(R.id.remarkTextView);
        final TextView monTextView = (TextView)view.findViewById(R.id.monTextView);
        final TextView tueTextView = (TextView)view.findViewById(R.id.tueTextView);
        final TextView wedTextView = (TextView)view.findViewById(R.id.wedTextView);
        final TextView thuTextView = (TextView)view.findViewById(R.id.thuTextView);
        final TextView friTextView = (TextView)view.findViewById(R.id.friTextView);
        final TextView satTextView = (TextView)view.findViewById(R.id.satTextView);
        final TextView sunTextView = (TextView)view.findViewById(R.id.sunTextView);
        final TextView addressTextView = (TextView)view.findViewById(R.id.addressTextView);
        final TextView storyTextView = (TextView)view.findViewById(R.id.storyTextView);

        final Bundle args = getArguments();
        titleTextView.setText(args.getString(ARGUMENT_TITLE));
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

        return view;
    }
}
