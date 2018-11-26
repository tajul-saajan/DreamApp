package com.example.saajan.dreamapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DreamUtil extends AppCompatActivity {
    public static String userType = "";
    public static int accessLevel = -1;
    public static String bloodGroupForList = "";
    public static boolean isLoggedIn=false;


    public static final String USER = "user";
    public static final String ADMIN = "admin";
    public static final String VOLUNTEER= "Volunteer";


}
