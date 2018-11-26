package com.example.saajan.dreamapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);

        Button bt = (Button) findViewById(R.id.add_usr_submit_bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInfo();
            }
        });
    }

    private void getInfo() {
        final String roll = ((EditText)findViewById(R.id.roll_add_usr)).getText().toString().trim();
        final String pass = ((EditText)findViewById(R.id.pass_add_usr)).getText().toString().trim();
        final String cPass = ((EditText)findViewById(R.id.con_pass_add_usr)).getText().toString().trim();

        final Moderator moderator=new Moderator(pass,roll);

        if(!pass.equals(cPass)) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Password Doesn't Match\nPlease Enter Again",
                    Toast.LENGTH_SHORT);


            toast.show();

            ((EditText)findViewById(R.id.pass_add_usr)).setText("");
            ((EditText)findViewById(R.id.con_pass_add_usr)).setText("");

        }
        else {

            DatabaseReference mDatabase;
            mDatabase = FirebaseDatabase.getInstance().getReference();

            //String usertype = getSharedPreferences(FrontActivity.PREFS_NAME,MODE_PRIVATE).getString("usertype",null);
            String usertype = DreamUtil.userType;

            if(usertype.equals("Admin")) {
                //admin access
                Toast.makeText(getApplicationContext(),
                        "Admin Added",
                        Toast.LENGTH_SHORT).show();

                mDatabase.child("users").child("Admin").child(roll).setValue(moderator);
            }
            else if(usertype.equals("Volunteer")) {
                //volunteer add
                Toast.makeText(getApplicationContext(),
                        "Volunteer Added",
                        Toast.LENGTH_SHORT).show();

                mDatabase.child("users").child("Volunteer").child(roll).setValue(moderator);
            }

            super.onBackPressed();
        }
    }
}
