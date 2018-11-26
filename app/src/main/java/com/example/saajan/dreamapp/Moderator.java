package com.example.saajan.dreamapp;

import java.util.ArrayList;

public class Moderator {

    public static final String DEFAULT_PASS = "12345";

    String password,roll;
    ArrayList<Donor.Donation> helpHistory;

    public Moderator(){;}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public ArrayList<Donor.Donation> getHelpHistory() {
        return helpHistory;
    }

    public void setHelpHistory(ArrayList<Donor.Donation> helpHistory) {
        this.helpHistory = helpHistory;
    }

    public Moderator(String password, String roll) {
        this.password = password;
        this.roll = roll;

    }
}
