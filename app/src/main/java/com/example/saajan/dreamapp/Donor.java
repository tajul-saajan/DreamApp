package com.example.saajan.dreamapp;

import java.time.LocalDate;
import java.util.ArrayList;

public class Donor {
    protected String name,roll,department,bloodGroup,contactNo;
    protected ArrayList<Donation> donationHistory;
    protected long lastDonated;

    public Donor(String name, String roll, String department, String bloodGroup, String contactNo) {
        this.name = name;
        this.roll = roll;
        this.department = department;
        this.bloodGroup = bloodGroup;
        this.contactNo = contactNo;
        this.lastDonated=-1;
        this.donationHistory= new ArrayList<Donation>();
    }

    public Donor(){
        ;
    }



    protected class Donation {
        String reqID,reqDate;
        LocalDate donationDate;

        public Donation(String reqID, String reqDate,LocalDate donationDate) {
            this.reqID = reqID;
            this.reqDate = reqDate;
            this.donationDate=donationDate;
        }
    }

    public long getLastDonated() {
        //TODO get last donated days
        return lastDonated;
    }



    public String getName() {
        return name;
    }

    public String getRoll() {
        return roll;
    }

    public String getDepartment() {
        return department;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getContactNo() {
        return contactNo;
    }

    public ArrayList<Donation> getDonationHistory() {
        return donationHistory;
    }
}
