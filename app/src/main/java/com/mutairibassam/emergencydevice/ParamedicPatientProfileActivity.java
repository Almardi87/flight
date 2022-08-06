package com.mutairibassam.emergencydevice;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ParamedicPatientProfileActivity extends AppCompatActivity {

    private TextView txtValueName;
    private TextView txtValueNationalID;
    private TextView txtValueBloodType;
    private TextView txtValueHeight;
    private TextView txtValueWeight;
    private TextView txtValueDOB;
    private TextView txtValueMobile;
    private TextView txtValueMedication;
    private TextView txtValueMedicalCon;
    private TextView txtValueLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paramedic_patient_profile);

        txtValueName = (TextView) findViewById(R.id.txtName);
        txtValueNationalID = (TextView) findViewById(R.id.txtNationalID);
        txtValueBloodType = (TextView) findViewById(R.id.txtBloodType);
        txtValueHeight = (TextView) findViewById(R.id.txtHeight);
        txtValueWeight = (TextView) findViewById(R.id.txtWeight);
        txtValueDOB = (TextView) findViewById(R.id.txtDOB);
        txtValueMobile = (TextView) findViewById(R.id.txtMobile);
        txtValueMedication = (TextView) findViewById(R.id.txtMedication);
        txtValueMedicalCon = (TextView) findViewById(R.id.txtMedicalCond);
        txtValueLocation = (TextView) findViewById(R.id.txtLocation);

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("users").child("admin").child("user01");
        DatabaseReference dbLoc = FirebaseDatabase.getInstance().getReference().child("requests").child("-LbJXu_JZnbfXKsNBMpi");

        dbRef.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {

                String name = dataSnapshot.child("name").getValue().toString();
                String nationalId = dataSnapshot.child("nationalid").getValue().toString();
                String blood = dataSnapshot.child("bloodtype").getValue().toString();
                String date = dataSnapshot.child("dob").getValue().toString();
                String phone = dataSnapshot.child("mobile").getValue().toString();
                String med = dataSnapshot.child("medication").getValue().toString();
                String medCon = dataSnapshot.child("medicalCon").getValue().toString();
                String hei = dataSnapshot.child("height").getValue().toString();
                String wei = dataSnapshot.child("weight").getValue().toString();

                txtValueName.setText(name);
                txtValueNationalID.setText(nationalId);
                txtValueBloodType.setText(blood);
                txtValueDOB.setText(date);
                txtValueMobile.setText(phone);
                txtValueMedication.setText(med);
                txtValueMedicalCon.setText(medCon);
                txtValueHeight.setText(hei);
                txtValueWeight.setText(wei);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        dbLoc.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String locationValue = dataSnapshot.child("Location").getValue().toString();
                txtValueLocation.setText("Patient Location");

                txtValueLocation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String url = "http://maps.google.com/?q=" + locationValue;
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                        System.out.println(url);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
