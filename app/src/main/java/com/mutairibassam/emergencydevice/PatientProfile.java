package com.mutairibassam.emergencydevice;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientProfile extends Fragment {

    private TextView txtValueName;
    private TextView txtValueNationalID;
    private TextView txtValueBloodType;
    private TextView txtValueHeight;
    private TextView txtValueWeight;
    private TextView txtValueDOB;
    private TextView txtValueMobile;
    private TextView txtValueMedication;
    private TextView txtValueMedicalCon;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.activity_patient_fragment_profile, container, false);



        txtValueName = (TextView) myView.findViewById(R.id.txtName);
        txtValueNationalID = (TextView) myView.findViewById(R.id.txtNationalID);
        txtValueBloodType = (TextView) myView.findViewById(R.id.txtBloodType);
        txtValueHeight = (TextView) myView.findViewById(R.id.txtHeight);
        txtValueWeight = (TextView) myView.findViewById(R.id.txtWeight);
        txtValueDOB = (TextView) myView.findViewById(R.id.txtDOB);
        txtValueMobile = (TextView) myView.findViewById(R.id.txtMobile);
        txtValueMedication = (TextView) myView.findViewById(R.id.txtMedication);
        txtValueMedicalCon = (TextView) myView.findViewById(R.id.txtMedicalCond);


        final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("users").child("patient").child("user01");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String name = dataSnapshot.child("name").getValue().toString();
                String nationalId = dataSnapshot.child("nationalid").getValue().toString();
                String blood = dataSnapshot.child("bloodtype").getValue().toString();
                String date = dataSnapshot.child("dob").getValue().toString();
                String phone = dataSnapshot.child("mobile").getValue().toString();
                String med = dataSnapshot.child("medication").getValue().toString();
                String medCon = dataSnapshot.child("medicalCon").getValue().toString();
                String hi = dataSnapshot.child("height").getValue().toString();
                String we = dataSnapshot.child("weight").getValue().toString();

                txtValueName.setText(name);
                txtValueNationalID.setText(nationalId);
                txtValueBloodType.setText(blood);
                txtValueDOB.setText(date);
                txtValueMobile.setText(phone);
                txtValueMedication.setText(med);
                txtValueMedicalCon.setText(medCon);
                txtValueHeight.setText(hi);
                txtValueWeight.setText(we);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return myView;
    }
}
