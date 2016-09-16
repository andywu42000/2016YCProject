package com.example.jiarou.sharelove;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * Created by chiayi on 16/8/30.
 */
public class LottoRuleDialogFragment extends DialogFragment {


//    interface LListener{
//        void OwnedCouponClick();
//        void GoBackClick();
//    }
//
//    private LListener llistener;
//


    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {

        return  layoutInflater.inflate(R.layout.lotto_rule_fragment,container,false);


    }


    public void onViewCreated(View view,Bundle savedInstanceState){

        super.onViewCreated(view,savedInstanceState);
        view.findViewById(R.id.exit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();

            }
        });

    }


    public Dialog onCreateDialog(Bundle savedInstanceState){
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }



}
