package com.mutairibassam.emergencydevice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PatientNavActivity extends AppCompatActivity {

    private TextView txtValueName;
    private TextView txtValueNationalID;
    private TextView txtValueBloodType;
    private TextView txtValueHeight;
    private TextView txtValueWeight;
    private TextView txtValueDOB;
    private TextView txtValueMobile;
    private TextView txtValueMedication;
    private TextView txtValueMedicalCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_nav);

        txtValueName = (TextView) findViewById(R.id.txtName);
        txtValueNationalID = (TextView) findViewById(R.id.txtNationalID);
        txtValueBloodType = (TextView) findViewById(R.id.txtBloodType);
        txtValueHeight = (TextView) findViewById(R.id.txtHeight);
        txtValueWeight = (TextView) findViewById(R.id.txtWeight);
        txtValueDOB = (TextView) findViewById(R.id.txtDOB);
        txtValueMobile = (TextView) findViewById(R.id.txtMobile);
        txtValueMedication = (TextView) findViewById(R.id.txtMedication);
        txtValueMedicalCon = (TextView) findViewById(R.id.txtMedicalCond);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navLister);

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("users").child("patient").child("user01");

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

    }
        private BottomNavigationView.OnNavigationItemSelectedListener navLister =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                        //set an empty fragment to be added the selected fragment later
                        Fragment selectedFragment = null;

                        //Home fragment will be shown here
                        switch (menuItem.getItemId()) {
                            case R.id.nav_profile:
                                selectedFragment = new PatientProfile();
                                break;

                            //Dashboard fragment will be shown here
                            case R.id.nav_dashboard:
                                selectedFragment = new PatientFragmentDashboardActivity();
                                break;

                            //Add paramedic fragment will be shown here
                            case R.id.nav_add:
                                selectedFragment = new PatientFragmentAdduserActivity();
                                break;
                        }

                        //fragment manager to manage fragment either to be (added, replaced, and removed))
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                selectedFragment).commit();

                        return true;

                    }
                };

}
