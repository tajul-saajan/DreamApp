package com.example.saajan.dreamapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DonorProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donor_profile);

        Intent it = getIntent();
        Donor donor = AdapterActivity.donor;

        ((TextView)findViewById(R.id.name)).setText(donor.getName().toUpperCase());
        ((TextView)findViewById(R.id.roll)).setText(donor.getRoll().toUpperCase());
        ((TextView)findViewById(R.id.dept_donor)).setText(donor.getDepartment().toUpperCase());
        ((TextView)findViewById(R.id.blood_grp_donor_prf)).setText(donor.getBloodGroup());
        ((TextView)findViewById(R.id.name2)).setText(donor.getName());

        //call button clicked
        final String number = donor.getContactNo().toString();
        Button call_bt = (Button) findViewById(R.id.call_donor_bt);
        call_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+number));
                startActivity(callIntent);
            }
        });
    }
}
