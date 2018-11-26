package com.example.saajan.dreamapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class RequestListActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    ArrayList<String> listDataHeader;
    HashMap<String, ArrayList<String>> listDataChild;

    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("requests");
    final HashMap<String, ArrayList<Request>> listRequest =new HashMap<>();
    ArrayList<String> listRequestHeader =new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_list);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.request_expander);

        // preparing list data
        prepareListData();

        prepareRequestInfo();

//        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
//
//        // setting list adapter
//        expListView.setAdapter(listAdapter);

        listAdapter = new ExpandableListAdapter(this, listRequestHeader, listRequest);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        expListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Request request = (Request) parent.getItemAtPosition(position);

            }
        });

    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, ArrayList<String>>();

        // Adding child data
        listDataHeader.add("Top 250");
        listDataHeader.add("Now Showing");
        listDataHeader.add("Coming Soon..");

        // Adding child data
        ArrayList<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        ArrayList<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        ArrayList<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }



    private void prepareRequestInfo() {

        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot mDataSnapshot : dataSnapshot.getChildren()) {
                    Log.e("Update", "=======" + mDataSnapshot.getKey());
                    String date = mDataSnapshot.getKey();
                    listRequest.put(date,new ArrayList<Request>());
                    listRequestHeader.add(date);
//                    Log.e("----", "======="+mDataSnapshot.child("roll").getValue());
                    //st.add(mDataSnapshot.getValue(Donor.class));
//                    Donor donor = mDataSnapshot.getValue(Donor.class);
//                    String bloodGrp  = donor.getBloodGroup();
//                    donorList.get(bloodGrp).add(donor);

                    for (DataSnapshot childDataSnapshot : mDataSnapshot.getChildren()) {
                        Log.e("Update", "=======" + childDataSnapshot.getValue());
                        Request request= childDataSnapshot.getValue(Request.class);
                        listRequest.get(date).add(request);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
