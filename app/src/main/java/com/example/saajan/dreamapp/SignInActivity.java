package com.example.saajan.dreamapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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


public class SignInActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Button bt = (Button) findViewById(R.id.log_in_bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLoginInfo();
            }
        });


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void getLoginInfo() {
        EditText uID = (EditText) findViewById(R.id.roll_login);
        final String userRoll = uID.getText().toString().trim();

        EditText uPass = (EditText) findViewById(R.id.passw_login);
        final String password = uPass.getText().toString().trim();

        Spinner uType = (Spinner) findViewById(R.id.user_type_sp);
        uType.setOnItemSelectedListener(this);
        final String userType = (String) uType.getSelectedItem().toString().trim();


        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(userType);


        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(userRoll)) {
                    String kk = dataSnapshot.child(userRoll).child("password").getValue().toString();

                    if (kk.equals(password)) {
                        Toast.makeText(SignInActivity.this, "Logged in as " + userType, Toast.LENGTH_SHORT).show();
                        DreamUtil.userType = userType;
                        DreamUtil.isLoggedIn=true;

                        getSharedPreferences(FrontActivity.PREFS_NAME,MODE_PRIVATE).edit().
                                putString("usertype",userType).commit();

                    } else
                        Toast.makeText(SignInActivity.this, "Incorrect Password,", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(SignInActivity.this, "Incorrect User Name", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        if (userType.equals("Admin")) {
            Intent intent = new Intent(this, AdminHome.class);
            startActivity(intent);
        } else if (userType.equals("Volunteer")) {
            Intent intent = new Intent(this, AdminHome.class);
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Please Select an User Type",
                    Toast.LENGTH_SHORT);

            toast.show();
            uID.setText("");
            uPass.setText("");
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
