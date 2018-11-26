package com.example.saajan.dreamapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RequestBloodActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_blood);




        Button submitReq = (Button) findViewById(R.id.submit_req_bt);


        submitReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertReq();
            }
        });
    }

    private void insertReq () {
        EditText pn = (EditText) findViewById(R.id.patient_name_etxt);
        final String patientName = pn.getText().toString().trim();

        EditText dt = (EditText) findViewById(R.id.description_etxt);
        final String description = dt.getText().toString().trim();

        EditText ct = (EditText) findViewById(R.id.contact_etxt);
        final String contactNo = ct.getText().toString().trim();

        EditText bg =(EditText) findViewById(R.id.no_bag_etxt);
        final String noBag = bg.getText().toString().trim();

        Spinner sp = (Spinner) findViewById(R.id.blood_group_sp);
        sp.setOnItemSelectedListener(this);
        final String bloodGroup = sp.getSelectedItem().toString().trim();

        Integer unique_id= (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
        String reqID = unique_id.toString();


        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

        Request request;
        request =new Request(patientName,FrontActivity.uName,FrontActivity.uPhotoURL,
                bloodGroup,description,contactNo,FrontActivity.uEmail,noBag,reqID,date);



        Toast toast = Toast.makeText(getApplicationContext(),
                "Request Added",
                Toast.LENGTH_SHORT);

        toast.show();

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("requests").child(date).child(reqID).setValue(request);
        mDatabase.child("notifications").child(reqID).setValue(request);


        //after completing go to user home page
        Intent intent = new Intent(this,UserHome.class);
        startActivity(intent);
    }

    public void onBackPressed() {
        Intent intent = new Intent(this,UserHome.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
