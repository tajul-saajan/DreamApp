package com.example.saajan.dreamapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CurrentReqListActivity extends AppCompatActivity {

    final static ArrayList<Request> requestList = new ArrayList<>();
    public static String requestID;

    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

    public static void utilUpdateRequests(DatabaseReference dataRef) {
        dataRef.child("notifications").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot mData : dataSnapshot.getChildren()) {
                    Request request = mData.getValue(Request.class);
                    Log.e("Patient Name ----- : ", request.getPatientName());
                    requestList.add(request);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_donor); //listview for adapter
        utilUpdateRequests(rootRef);
        showInAdapter();

    }

    public void showInAdapter() {
        AdapterRequestList adapter = new
                AdapterRequestList(this, R.layout.request_layout, requestList);
        ListView listView = (ListView) findViewById(R.id.donor_list_listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CurrentReqListActivity.this,
                        "Item clicked!", Toast.LENGTH_SHORT).show();
                Request request = (Request) parent.getItemAtPosition(position);
                requestID = request.getReqID();
                Intent it = new Intent(getApplicationContext(), RequestProfile.class);
                startActivity(it);
            }
        });
    }
}
