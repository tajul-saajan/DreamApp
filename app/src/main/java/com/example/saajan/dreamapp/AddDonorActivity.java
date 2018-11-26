package com.example.saajan.dreamapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddDonorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donor);

        Button submit = (Button) findViewById(R.id.submit_donor);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInfo();
            }
        });
    }

    protected void getInfo() {
        final String name = ((EditText) findViewById(R.id.name_donor)).getText().toString().trim();
        final String roll = ((EditText) findViewById(R.id.roll_add_donor)).getText().toString().trim();
        final String contactNo = ((EditText) findViewById(R.id.contact_etxt2)).getText().toString().trim();

        Spinner sp = (Spinner) findViewById(R.id.blood_group_sp2);
        sp.setOnItemSelectedListener(this);
        final String bloodGroup = sp.getSelectedItem().toString().trim();

        Spinner sp2 = (Spinner) findViewById(R.id.dept_sp);
        sp2.setOnItemSelectedListener(this);
        final String dept = sp2.getSelectedItem().toString().trim();

        if (!name.isEmpty() && roll.length() == 7 && contactNo.length() == 11
                && bloodGroup.length() <= 11 && dept.length() < 15) {

            final Donor donor = new Donor(name, roll, dept, bloodGroup, contactNo);


            final DatabaseReference mDatabase;
            mDatabase = FirebaseDatabase.getInstance().getReference().child("Donor");

            //if donor already exists
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild(roll)) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Donor Already Exists",
                                Toast.LENGTH_LONG);

                        toast.show();
                    } else {
                        mDatabase.child(donor.getRoll()).setValue(donor);
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Donor Added",
                                Toast.LENGTH_LONG);

                        toast.show();
                        goBack();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Please Enter Values Correctly",
                    Toast.LENGTH_SHORT);

            toast.show();
        }


    }

    private void goBack() {

        super.onBackPressed();//go to previous activity
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
