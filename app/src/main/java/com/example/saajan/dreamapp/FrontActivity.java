package com.example.saajan.dreamapp;

import android.annotation.TargetApi;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FrontActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    public static String uName = "", uEmail = "", uPhotoURL = "";

    private static final String TAG = FrontActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;

    public static GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;

    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

    public static final String PREFS_NAME = "DreamPref";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        } catch (RuntimeException e) {
            Log.e(String.valueOf(FrontActivity.class), " " + e);
        }

        setContentView(R.layout.activity_front);

        AdapterActivity.utilUpdate(rootRef);
        rootRef.keepSynced(true);

        SharedPreferences pref  = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        String userType=pref.getString("usertype",null);

        if (userType != null) {
            Intent intent;
            intent = new Intent(getApplicationContext(), AdminHome.class);
            startActivity(intent);
        }

        Button reqBloodBt = (Button) findViewById(R.id.request_blood);
        reqBloodBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        Button signIn_Bt = (Button) findViewById(R.id.sign_in_bt);
        signIn_Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
            }
        });
    }

    /*double press back button exits the app */

    private static long sayBackPress;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        if (sayBackPress + 2000 > System.currentTimeMillis()) {
//            super.onBackPressed();
            this.finishAffinity();
        } else {
            Toast.makeText(FrontActivity.this, "Press once again to exit!", Toast.LENGTH_SHORT).show();
            sayBackPress = System.currentTimeMillis();
        }
    }

    /*------------------------*/

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            uName = acct.getDisplayName();
            uEmail = acct.getEmail();
            uPhotoURL = acct.getPhotoUrl().toString();

            Intent intent = new Intent(FrontActivity.this, RequestBloodActivity.class);
            FrontActivity.this.startActivity(intent);


//            Log.e(TAG, "display name: " + acct.getDisplayName());
//
//            String personName = acct.getDisplayName();
//            String personPhotoUrl = acct.getPhotoUrl().toString();
//            String email = acct.getEmail();
//
//            Log.e(TAG, "Name: " + personName + ", email: " + email
//                    + ", Image: " + personPhotoUrl);


        } else {
            // Signed out, show unauthenticated UI.

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
