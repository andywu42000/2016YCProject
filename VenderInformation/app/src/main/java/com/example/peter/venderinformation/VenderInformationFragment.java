package com.example.peter.venderinformation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VenderInformationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VenderInformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VenderInformationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int REQUEST_CODE_ONSHARE_INT = 100;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView title;
    ImageView vendorPic;
    TextView address;
    TextView time;
    TextView tel;
    TextView story;
    ImageView thumb;
    ImageView fbShare;

    CallbackManager callbackManager;
    ShareDialog shareDialog;
;
    public OnFragmentInteractionListener mListener;

    public VenderInformationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VenderInformation.
     */
    // TODO: Rename and change types and number of parameters
    public static VenderInformationFragment newInstance(String param1, String param2) {
        VenderInformationFragment fragment = new VenderInformationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        title = (TextView)getView().findViewById(R.id.titleTextView);
        vendorPic = (ImageView)getView().findViewById(R.id.vendorImageView);
        time = (TextView)getView().findViewById(R.id.timeTextView);
        address= (TextView)getView().findViewById(R.id.addressTextView);
        tel = (TextView)getView().findViewById(R.id.telTextView);
        story = (TextView)getView().findViewById(R.id.storyTextView);
        thumb = (ImageView)getView().findViewById(R.id.thumbImageView);
        fbShare = (ImageView)getView().findViewById(R.id.fbShareImageView);

        callbackManager  = CallbackManager.Factory.create();

        fbShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {

                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException error) {

                    }
                });

                if(shareDialog.canShow(ShareLinkContent.class)){
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle(title.toString())
                            .setContentDescription(time.toString() + "/n" + address.toString())
                            .setContentUrl(Uri.parse("http://harvest365.org/posts/3309"))
                            .build();

                    shareDialog.show(linkContent);
                }
            }
        });

        return inflater.inflate(R.layout.fragment_vender_information, container, false);

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_ONSHARE_INT){
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

/**
 * This interface must be implemented by activities that contain this
 * fragment to allow an interaction in this fragment to be communicated
 * to the activity and potentially other fragments contained in that
 * activity.
 * <p/>
 * See the Android Training lesson <a href=
 * "http://developer.android.com/training/basics/fragments/communicating.html"
 * >Communicating with Other Fragments</a> for more information.
 */
public interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
}
}
