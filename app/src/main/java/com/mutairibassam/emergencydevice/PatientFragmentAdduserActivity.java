package com.mutairibassam.emergencydevice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientFragmentAdduserActivity extends Fragment implements View.OnClickListener {


    private EditText name, nationalid, relation, mobile, medication;
    private Button btnSubmit;

    private FirebaseDatabase database;
    private DatabaseReference ref;
    private DatabaseReference mDatabaseUsers;
    private FirebaseUser mCurrentUser;
    private PatientAddUserFirebase mPatientAddUserFirebase;
    private FirebaseAuth mAuth;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.activity_fragment_add, container, false);

        name = (EditText) myView.findViewById(R.id.Namefill);
        nationalid = (EditText) myView.findViewById(R.id.NationalIDfill);
        relation = (EditText) myView.findViewById(R.id.badgefill);
        mobile = (EditText) myView.findViewById(R.id.mobilefill);
        medication = (EditText) myView.findViewById(R.id.jobtitlefill);
        btnSubmit = (Button) myView.findViewById(R.id.btnSubmit2);

        btnSubmit.setOnClickListener(this);

        database = FirebaseDatabase.getInstance();
        ref = FirebaseDatabase.getInstance().getReference().child("users").child("patient");
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();//
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("users").child(mCurrentUser.getUid());


        mPatientAddUserFirebase = new PatientAddUserFirebase();

        return myView;
    }

    private void getValues() {

        mPatientAddUserFirebase.setName(name.getText().toString().trim());
        mPatientAddUserFirebase.setNationalid(nationalid.getText().toString().trim());
        mPatientAddUserFirebase.setBadge(relation.getText().toString().trim());
        mPatientAddUserFirebase.setMobile(mobile.getText().toString().trim());
        mPatientAddUserFirebase.setJobtitle(medication.getText().toString().trim());

    }


    @Override
    public void onClick(View v) {


        @SuppressWarnings("VisibleForTests") final DatabaseReference newPost = ref.push();
        mDatabaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                getValues();
                newPost.child("Name").setValue(mPatientAddUserFirebase.getName());
                newPost.child("National ID").setValue(mPatientAddUserFirebase.getName());
                newPost.child("Relation").setValue(mPatientAddUserFirebase.getName());
                newPost.child("Mobile").setValue(mPatientAddUserFirebase.getName());
                newPost.child("Medication").setValue(mPatientAddUserFirebase.getBadge())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getActivity(), "Patient has been added successfully", Toast.LENGTH_LONG).show();

                                }

                            }
                        });


                // To clean the edit text after submitting
                name.setText("");
                nationalid.setText("");
                relation.setText("");
                mobile.setText("");
                medication.setText("");

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}