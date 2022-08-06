package com.mutairibassam.emergencydevice;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OpertionProfile extends AppCompatActivity {

    private TextView txtValueName;
    private TextView txtValueNationalID;
    private TextView txtValueBloodType;
    private TextView txtValueDOB;
    private TextView txtValueMobile;
    private TextView txtValueMedication;
    private TextView txtValueMedicalCon;
    private TextView txtValueLocation;

    private TextView txtValueHospital;
    private TextView txtValueParamedic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation_profile);

        txtValueName = (TextView) findViewById(R.id.txtName);
        txtValueNationalID = (TextView) findViewById(R.id.txtNationalID);
        txtValueBloodType = (TextView) findViewById(R.id.txtBloodType);
        txtValueDOB = (TextView) findViewById(R.id.txtDOB);
        txtValueMobile = (TextView) findViewById(R.id.txtMobile);
        txtValueMedication = (TextView) findViewById(R.id.txtMedication);
        txtValueMedicalCon = (TextView) findViewById(R.id.txtMedicalCond);
        txtValueLocation = (TextView) findViewById(R.id.txtLocation);

        txtValueHospital = (TextView) findViewById(R.id.txtHospital);
        txtValueParamedic = (TextView) findViewById(R.id.txtParamedic);

        final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("users").child("operation").child("user01");
        DatabaseReference dbLoc = FirebaseDatabase.getInstance().getReference().child("requests").child("-LbJXu_JZnbfXKsNBMpi");


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
                String hos = dataSnapshot.child("hospital").getValue().toString();
                String para = dataSnapshot.child("paramedic").getValue().toString();

                txtValueName.setText(name);
                txtValueNationalID.setText(nationalId);
                txtValueBloodType.setText(blood);
                txtValueDOB.setText(date);
                txtValueMobile.setText(phone);
                txtValueMedication.setText(med);
                txtValueMedicalCon.setText(medCon);
                txtValueHospital.setText(hos);
                txtValueParamedic.setText(para);

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
