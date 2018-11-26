package com.example.saajan.dreamapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterActivity extends AppCompatActivity {


    public static DatabaseReference rootRef, mDataRef;
    public static Donor donor=new Donor();

    final static HashMap<String,ArrayList<Donor> > donorList = new HashMap<String,ArrayList<Donor>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_adapter);
        setContentView(R.layout.list_donor);

        rootRef = FirebaseDatabase.getInstance().getReference();
//        utilUpdate(rootRef);
        rootRef.keepSynced(true);//for offline storage and syncing
        showInAdapter(donorList.get(DreamUtil.bloodGroupForList));

    }

    public static void  utilUpdate(DatabaseReference dataRef) {

        //final ArrayList<Donor> st = new ArrayList<>();
        donorList.put("A positive",new ArrayList<Donor>());
        donorList.put("A negative",new ArrayList<Donor>());
        donorList.put("B positive",new ArrayList<Donor>());
        donorList.put("B negative",new ArrayList<Donor>());
        donorList.put("AB positive",new ArrayList<Donor>());
        donorList.put("AB negative",new ArrayList<Donor>());
        donorList.put("O positive",new ArrayList<Donor>());
        donorList.put("O negative",new ArrayList<Donor>());


        //donorList.put(bloodGrp,new ArrayList<Donor>());
        //dataRef.keepSynced(true);
        dataRef.child("Donor").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot mDataSnapshot : dataSnapshot.getChildren()) {
                    Log.e("Update", "=======" + mDataSnapshot.child("name").getValue());
                    Log.e("----", "======="+mDataSnapshot.child("roll").getValue());
                    //st.add(mDataSnapshot.getValue(Donor.class));
                    Donor donor = mDataSnapshot.getValue(Donor.class);
                    String bloodGrp  = donor.getBloodGroup();
                    donorList.get(bloodGrp).add(donor);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void showInAdapter(final ArrayList<Donor> arrayList) {
        MyAdapter myAdapter =
                new MyAdapter(this, R.layout.donor_layout, arrayList);
        ListView listView = (ListView) findViewById(R.id.donor_list_listview);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(AdapterActivity.this, "Item clicked!", Toast.LENGTH_SHORT).show();
                donor = (Donor) parent.getItemAtPosition(position);
                Intent it = new Intent(getApplicationContext(),DonorProfile.class);
                startActivity(it);

            }
        });

    }
}
