package com.example.saajan.dreamapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<Donor> {

    int layoutResourceID;
    private ArrayList<Donor> donors;
    private Context context;

    public MyAdapter(@NonNull Context context,int layoutResourceID, ArrayList<Donor> donors) {
        super(context, layoutResourceID,donors);
        this.context=context;
        this.donors=donors;
        this.layoutResourceID=layoutResourceID;
    }


    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = LayoutInflater.from(getContext()).inflate(layoutResourceID, parent, false);
        }

        Donor currentDonor = getItem(position);

        TextView nameTv = (TextView) row.findViewById(R.id.donor_name);
        nameTv.setText(currentDonor.getName());

        TextView rollTv = (TextView) row.findViewById(R.id.roll_no_donor);
        rollTv.setText(currentDonor.getRoll());


        TextView bloodGrp = (TextView) row.findViewById(R.id.blood_group_tv_donor);
        bloodGrp.setText(currentDonor.getBloodGroup());


        TextView lastDonated = (TextView) row.findViewById(R.id.last_deonated_donor);
//        currentDonor.setLastDonated();
        if(currentDonor.getLastDonated()!=-1) lastDonated.setText("Last Donated " + currentDonor.getLastDonated() + " day(s) ago");
        else lastDonated.setText("No Donation");

        return row;
        //return super.getView(position, convertView, parent);
    }
}
