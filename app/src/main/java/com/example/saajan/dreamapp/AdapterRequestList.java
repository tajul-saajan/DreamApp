package com.example.saajan.dreamapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

public class AdapterRequestList extends ArrayAdapter<Request> {

    int layoutResourceID;
    private ArrayList<Request> requests;
    private Context context;

    public AdapterRequestList(@NonNull Context context, int layoutResourceID,
                              ArrayList<Request> requests) {
        super(context,layoutResourceID,requests);
        this.context=context;
        this.requests=requests;
        this.layoutResourceID=layoutResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = LayoutInflater.from(getContext()).inflate(layoutResourceID, parent, false);
        }

        final Request currentRequest = getItem(position);

        TextView bloodGrpTv = (TextView) row.findViewById(R.id.blood_group_requestList);
        bloodGrpTv.setText(currentRequest.getBloodGroup());
//
        TextView patientName = (TextView) row.findViewById(R.id.patient_name_request_lists);
        patientName.setText(currentRequest.getPatientName());
//
//
        TextView name = (TextView) row.findViewById(R.id.requested_by_reqList);
        name.setText("Requested By : "+ currentRequest.getName());

        TextView contact  = (TextView) row.findViewById(R.id.contact_reqList);
        contact.setText("Contact No : "+currentRequest.getContacts());

        Button call = (Button) row.findViewById(R.id.call_requestee);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+currentRequest.getContacts()));
                startActivity(getContext(),callIntent,null);
            }
        });

        return row;
        //return super.getView(position, convertView, parent);
    }

}
