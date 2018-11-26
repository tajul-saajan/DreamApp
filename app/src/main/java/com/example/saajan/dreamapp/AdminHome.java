package com.example.saajan.dreamapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminHome extends AppCompatActivity {

    DatabaseReference mReference = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        Button aLB = (Button) findViewById(R.id.admin_list_bt);
        Button aAB = (Button) findViewById(R.id.add_admin_bt);
        Button aVB = (Button) findViewById(R.id.add_volunteer_bt);
        Button reqList = (Button) findViewById(R.id.request_list_bt);

        // ----------update lists -----

        AdapterActivity.utilUpdate(mReference);

        //--------------------


        String token = getSharedPreferences(FrontActivity.PREFS_NAME,MODE_PRIVATE).getString("deviceToken",null);



        mReference.child("deviceTokens").child(token).setValue(token);

        //restrict volunteer
        SharedPreferences preferences  = getSharedPreferences(FrontActivity.PREFS_NAME,MODE_PRIVATE);
        String userType = preferences.getString("usertype",null);

        if(userType.equals("Volunteer")) {

            aLB.setVisibility(View.GONE);

            aAB.setVisibility(View.GONE);

            aVB.setVisibility(View.GONE);
        }

        /* -------admin privileges-------- */

        //Admin list Button Clicked
        Button aListBt = (Button) findViewById(R.id.admin_list_bt);
        aListBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),VolunteerListActivity.class);
                DreamUtil.userType = "Admin";
                startActivity(it);
            }
        });
        //add admin Button Clicked
        Button addAdminBt = (Button) findViewById(R.id.add_admin_bt);
        addAdminBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DreamUtil.userType = "Admin";
                Intent it = new Intent(getApplicationContext(),AddAdminActivity.class);
                startActivity(it);
            }
        });
        //add adVol Button Clicked
        Button addVolBt = (Button) findViewById(R.id.add_volunteer_bt);
        addVolBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DreamUtil.userType = "Volunteer";
                Intent it = new Intent(getApplicationContext(),AddAdminActivity.class);
                startActivity(it);
            }
        });



        /* ------------------------ */


        //Donor list Button Clicked
        Button dListBt = (Button) findViewById(R.id.donor_list_bt);
        dListBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),BloodGroupList.class);
                startActivity(it);
            }
        });

        //volunteer list Button Clicked
        Button volListBt = (Button) findViewById(R.id.volunteer_list_bt);
        volListBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),VolunteerListActivity.class);
                DreamUtil.userType = "Volunteer";
                startActivity(it);
            }
        });

        //all donation history Button Clicked
        Button allDonationHistoryBt = (Button) findViewById(R.id.donation_history_all_bt);
        allDonationHistoryBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),RequestListActivity.class);
                startActivity(it);
            }
        });

        //request list button clicked
        reqList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CurrentReqListActivity.class);
                startActivity(intent);
            }
        });

        //sign out Button Clicked
        Button signOutBt = (Button) findViewById(R.id.sign_out_bt_admin);
        signOutBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),FrontActivity.class);
                getSharedPreferences(FrontActivity.PREFS_NAME,MODE_PRIVATE).edit().putString("usertype",null).commit();
                startActivity(it);

            }
        });

        //add donor button clicked
        Button addDonorBt = (Button) findViewById(R.id.add_donor_bt);
        addDonorBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddDonorActivity.class);
                startActivity(intent);
            }
        });
    }

    private static long sayBackPress;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        if (sayBackPress + 2000 > System.currentTimeMillis()){
//            super.onBackPressed();
            this.finishAffinity();
        }
        else{
            Toast.makeText(AdminHome.this,
                    "Press once again to exit!", Toast.LENGTH_SHORT).show();
            sayBackPress = System.currentTimeMillis();
        }
    }
}
