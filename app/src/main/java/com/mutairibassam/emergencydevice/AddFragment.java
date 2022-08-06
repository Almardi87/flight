package com.mutairibassam.emergencydevice;

import android.content.Intent;
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

public class AddFragment extends Fragment implements View.OnClickListener {


    //Declaration part (Button, EditText)
    private EditText name, nationalid, badgenumber, mobile, jobtitle;
    private Button btnSubmit;

    //Declaration part for Firebase
    FirebaseDatabase database;

    //database reference
    DatabaseReference ref;
    DatabaseReference mDatabaseUsers;
    FirebaseUser mCurrentUser;
    users Users;

    //Firebase Authentication
    FirebaseAuth mAuth;

// on Create view in the fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.activity_fragment_add, container, false);

        // link java button to a specific xml (button and EditText)
        name = (EditText) myView.findViewById(R.id.Namefill);
        nationalid = (EditText) myView.findViewById(R.id.NationalIDfill);
        badgenumber = (EditText) myView.findViewById(R.id.badgefill);
        mobile = (EditText) myView.findViewById(R.id.mobilefill);
        jobtitle = (EditText) myView.findViewById(R.id.jobtitlefill);
        btnSubmit = (Button) myView.findViewById(R.id.btnSubmit2);

        btnSubmit.setOnClickListener(this);

        //Specifie the database in the firebase, the root and children
        database = FirebaseDatabase.getInstance();
        ref = FirebaseDatabase.getInstance().getReference().child("users").child("admin");
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("users").child(mCurrentUser.getUid());

        //users class
        Users = new users();

        return myView;
    }

    //return values from the user
    private void getValues() {

        Users.setName(name.getText().toString().trim());
        Users.setNationalid(nationalid.getText().toString().trim());
        Users.setBadge(badgenumber.getText().toString().trim());
        Users.setMobile(mobile.getText().toString().trim());
        Users.setJobtitle(jobtitle.getText().toString().trim());

    }

    // onclick method
    @Override
    public void onClick(View v) {

        // store user's input in the firebase
        @SuppressWarnings("VisibleForTests") final DatabaseReference newPost = ref.push();
        mDatabaseUsers.addValueEventListener(new ValueEventListener() {

            // if there is any data change in the firebase does (onDataChange) method
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                getValues();
                newPost.child("Name").setValue(Users.getName());
                newPost.child("National ID").setValue(Users.getNationalid());
                newPost.child("Badge Number").setValue(Users.getBadge());
                newPost.child("Mobile").setValue(Users.getMobile());
                newPost.child("Job title").setValue(Users.getJobtitle())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getActivity(), "User has been added successfully", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                // To clean the edit text after submitting
                name.setText("");
                nationalid.setText("");
                badgenumber.setText("");
                mobile.setText("");
                jobtitle.setText("");
            }

            // if there is any data cancellation in the firebase does (onCancelled) method
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}













