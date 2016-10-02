package com.example.jiarou.sharelove;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChanglleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChanglleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChanglleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button c1,c2,c3,c4,over_btn;
    String get_check,number;
    Long point;
   Long points;
    String memberKey;


    private OnFragmentInteractionListener mListener;
    private delete mListener01;


    public ChanglleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment ChanglleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChanglleFragment newInstance() {

        Bundle args = new Bundle();

        final  ChanglleFragment fragment = new ChanglleFragment();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        get_check=bundle.getString("check");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       final View view= inflater.inflate(R.layout.fragment_changlle, container, false);
        c1 = (Button) view.findViewById(R.id.c1);
        c2 = (Button) view.findViewById(R.id.c2);
        c3 = (Button) view.findViewById(R.id.c3);
        c4 = (Button) view.findViewById(R.id.c4);
        over_btn= (Button) view.findViewById(R.id.over_btn);
        //第二格
        if(get_check=="1") {
            c1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "完成囉～ 前進第二關吧！", Toast.LENGTH_LONG).show();
                    //mListener.onFragmentInteraction("1");

                }
            });

            c2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onFragmentInteraction("2");


                }
            });


            c3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "請先完成第二關喔！", Toast.LENGTH_LONG).show();
                    //mListener.onFragmentInteraction("3");

                }
            });

            c4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "請先完成第二關喔！", Toast.LENGTH_LONG).show();
                    //mListener.onFragmentInteraction("4");

                }
            });

            over_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "請先完成第二關喔！", Toast.LENGTH_LONG).show();

                }
            });
        }else if(get_check=="2") {
            c1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mListener.onFragmentInteraction("1");
                    Toast.makeText(getActivity(), "已完成，到第三關囉！", Toast.LENGTH_LONG).show();

                }
            });

            c2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mListener.onFragmentInteraction("2");
                    Toast.makeText(getActivity(),"已完成，到第三關囉！", Toast.LENGTH_LONG).show();

                }
            });


            c3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(getActivity(), "請先完成第一關喔！", Toast.LENGTH_LONG).show();
                    mListener.onFragmentInteraction("3");

                }
            });

            c4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "第三關還沒完成喔！", Toast.LENGTH_LONG).show();
                    //mListener.onFragmentInteraction("4");

                }
            });
            over_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "請先完成第三關喔！", Toast.LENGTH_LONG).show();

                }
            });
        }else  if(get_check=="3") {
            c1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "剩下第四關了！", Toast.LENGTH_LONG).show();

                }
            });

            c2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mListener.onFragmentInteraction("2");
                    Toast.makeText(getActivity(), "剩下第四關了！", Toast.LENGTH_LONG).show();

                }
            });


            c3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "剩下第四關了！", Toast.LENGTH_LONG).show();
                    //mListener.onFragmentInteraction("3");

                }
            });

            c4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getActivity(), "請先完成第一關喔！", Toast.LENGTH_LONG).show();
                    mListener.onFragmentInteraction("4");

                }
            });
            over_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "請先完成第四關喔！", Toast.LENGTH_LONG).show();

                }
            });
        }else  if(get_check=="4"){
            c1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "可以領取愛心幣囉～", Toast.LENGTH_LONG).show();

                }
            });

            c2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mListener.onFragmentInteraction("2");
                    Toast.makeText(getActivity(), "可以領取愛心幣囉～", Toast.LENGTH_LONG).show();

                }
            });


            c3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "可以領取愛心幣囉～", Toast.LENGTH_LONG).show();
                    //mListener.onFragmentInteraction("3");

                }
            });

            c4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "可以領取愛心幣囉～", Toast.LENGTH_LONG).show();


                }
            });
            over_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder bdr = new AlertDialog.Builder(getActivity());
                    bdr.setMessage("獲得10個愛心幣");
                    bdr.setTitle("恭喜挑戰成功");
                    bdr.setPositiveButton("領取", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final Intent intent = new Intent();
                            intent.setClass(getActivity(),IndexActivity.class);
                            startActivity(intent);



                            final Firebase memberRef = new Firebase("https://member-139bd.firebaseio.com/");
              /*之後要加這段半別是哪一個使用者*/
                            Query memberQuery = memberRef.orderByChild("Facebook_ID").equalTo(111111111111111l);


                            memberQuery.addChildEventListener(new ChildEventListener() {

                                @Override
                                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                    point = (Long) dataSnapshot.child("Owned_Points").getValue();
                                    memberKey = dataSnapshot.getKey();
                                    memberRef.child(memberKey).child("Owned_Points").setValue(point + 10);


                                }

                                @Override
                                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                                    point = (Long) dataSnapshot.child("Owned_Points").getValue();

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

                        }
                    });
                    bdr.setCancelable(false);
                    bdr.show();



                }
            });

        }
        else {
            c1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onFragmentInteraction("1");

                }
            });

            c2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mListener.onFragmentInteraction("2");
                    Toast.makeText(getActivity(), "請先完成第一關喔！", Toast.LENGTH_LONG).show();
                    //Log.d("area", "area" + get_check);


                }
            });


            c3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "請先完成第一關喔！", Toast.LENGTH_LONG).show();
                    //mListener.onFragmentInteraction("3");

                }
            });

            c4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "請先完成第一關喔！", Toast.LENGTH_LONG).show();
                    //mListener.onFragmentInteraction("4");

                }
            });
            over_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "請先完成第一關喔！", Toast.LENGTH_LONG).show();

                }
            });
        }







        return  view;
    }
/**
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction();
        }
    }
 **/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }else  if(context instanceof  delete){
            mListener01=(delete) context;
        }
        else {
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
        void onFragmentInteraction(String number);
    }

    public interface delete {
        // TODO: Update argument type and name
        void delete();
    }



}
