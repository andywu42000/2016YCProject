package com.example.jiarou.sharelove;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.R.layout.select_dialog_item;
import static android.R.layout.simple_selectable_list_item;
import static android.R.layout.simple_spinner_item;

public class SearchActivity extends AppCompatActivity {
    private Spinner spinner;
    Spinner city;
    Spinner vendor_type;
    /*spinner 連動*/
    private String[] type = new String[] {"基隆市", "新北市","臺北市","宜蘭縣","新竹縣","桃園縣"
            ,"苗栗縣","臺中市","彰化縣","南投縣","嘉義縣","雲林縣","臺南市","高雄市","澎湖縣","金門縣"
            ,"屏東縣","台東縣","花蓮縣"};
    private String[] address = new String[]{"(200)仁愛區","(201)信義區","(202)中正區","(204)安樂區","(206)七堵區","(203)中山區","(205)暖暖區"};
    private String[][] type2 = new String[][]{{"(200)仁愛區","(201)信義區","(202)中正區","(204)安樂區","(206)七堵區","(203)中山區","(205)暖暖區"},{"(207)萬里區","(220)板橋區","(222)深坑區","(224)瑞芳區","(226)平溪區","(228)貢寮區","(232)坪林區","(234)永和區","(236)土城區","(238)樹林區","(241)三重區","(243)泰山區","(247)蘆洲區","(249)八里區","(252)三芝區","(208)金山區","(221)汐止區","(223)石碇區","(227)雙溪區","(231)新店區","(233)烏來區","(235)中和區","(237)三峽區","(239)鶯歌區","(242)新莊區","(244)林口區","(248)五股區","(251)淡水區","(253)石門區"},
            {"(100)中正區","(103)大同區","(104)中山區","(106)大安區","(108)萬華區","(110)信義區","(112)北投區","(114)內湖區","(116)文山區","(111)士林區","(105)松山區","(115)南港區"} };

     Spinner sp;//第一個下拉選單
     Spinner sp2;//第二個下拉選單
    private Context context;




    ArrayAdapter<String> adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final Firebase myFirebaseRef = new Firebase("https://vendor-5acbc.firebaseio.com/Vendors");


        vendor_type=(Spinner) findViewById(R.id.spinner2);
         /*spinner 連動*/
        context = this;

        //程式剛啟始時載入第一個下拉選單
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, type);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp = (Spinner) findViewById(R.id.spinner);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(selectListener);

        //因為下拉選單第一個為地址，所以先載入地址群組進第二個下拉選單
        adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, address);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2 = (Spinner) findViewById(R.id.spinner3);
        sp2.setAdapter(adapter2);

       /*build a 下拉式選單 firebase
        final List<String> areas = new ArrayList<String>();
        Spinner areaSpinner = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(SearchActivity.this, android.R.layout.simple_spinner_item, areas);
        areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        areaSpinner.setAdapter(areasAdapter);

        /* try show whatt I select
        areaSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView a, View view, int position, long id) {
                Toast.makeText(SearchActivity.this, "your select" + a.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });
*/

/**firebase
        ChildEventListener childEventListener = myFirebaseRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                areas.add( (String) dataSnapshot.child("Location/ZIP").getValue());
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
        */


    }
    private AdapterView.OnItemSelectedListener selectListener = new AdapterView.OnItemSelectedListener(){
        public void onItemSelected(AdapterView<?> parent, View v, int position,long id){
            //讀取第一個下拉選單是選擇第幾個
            int pos = sp.getSelectedItemPosition();
            //重新產生新的Adapter，用的是二維陣列type2[pos]
            adapter2 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, type2[pos]);
            //載入第二個下拉選單Spinner
            sp2.setAdapter(adapter2);
        }

        public void onNothingSelected(AdapterView<?> arg0){

        }

    };




    public void order(View v){

        String []  vendor_types=getResources().getStringArray(R.array.vendor_types);
    }




}
