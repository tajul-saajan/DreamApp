package com.example.saajan.dreamapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;



public class BloodGroupList extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_group_list);


        Button ap = (Button) findViewById(R.id.a_positive_bt);
        ap.setOnClickListener(this);
        Button an = (Button) findViewById(R.id.a_negaitive_bt);
        an.setOnClickListener(this);
        Button bp = (Button) findViewById(R.id.b_positive_bt);
        bp.setOnClickListener(this);
        Button bn = (Button) findViewById(R.id.b_negaitive_bt);
        bn.setOnClickListener(this);
        Button abp = (Button) findViewById(R.id.ab_positive_bt);
        abp.setOnClickListener(this);
        Button abn = (Button) findViewById(R.id.ab_negaitive_bt);
        abn.setOnClickListener(this);
        Button op = (Button) findViewById(R.id.o_positive_bt);
        op.setOnClickListener(this);
        Button on = (Button) findViewById(R.id.o_negaitive_bt);
        on.setOnClickListener(this);
    }






    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.a_positive_bt :{
                DreamUtil.bloodGroupForList="A positive";

                break;

            }
            case R.id.a_negaitive_bt: {
                DreamUtil.bloodGroupForList="A negative";

                break;

            }
            case R.id.b_positive_bt: {
                DreamUtil.bloodGroupForList="B positive";
                break;
            }
            case R.id.b_negaitive_bt: {
                DreamUtil.bloodGroupForList="B negative";

                break;

            }
            case R.id.ab_positive_bt: {

                DreamUtil.bloodGroupForList="AB positive";

                break;
            }
            case R.id.ab_negaitive_bt: {
                DreamUtil.bloodGroupForList="AB negative";

                break;

            }
            case R.id.o_positive_bt: {
                DreamUtil.bloodGroupForList="O positive";

                break;
            }
            case R.id.o_negaitive_bt: {
                DreamUtil.bloodGroupForList="O negative";

                break;

            }
        }

        Intent intent = new Intent(this,AdapterActivity.class);
        startActivity(intent);

    }

}
