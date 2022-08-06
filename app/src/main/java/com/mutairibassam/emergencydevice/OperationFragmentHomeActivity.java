package com.mutairibassam.emergencydevice;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OperationFragmentHomeActivity extends Fragment {


    private DatabaseReference mDatabase;
    private ListView mmListview;


    private ArrayList<String> mRequests = new ArrayList<>();
    private ArrayList<String> mKeys = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.activity_fragment_home, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("requests");
        mmListview = (ListView) myView.findViewById(R.id.requestsListview);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mRequests);
        mmListview.setAdapter(arrayAdapter);


        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull final DataSnapshot dataSnapshot, String s) {

                String location = dataSnapshot.child("Location").getValue(String.class);
                final String requesterid = dataSnapshot.child("requesterID").getValue(String.class);
                mRequests.add("Req id#  " + requesterid + "\n" + "location:  " + location);


                String key = dataSnapshot.getKey();
                mKeys.add(key);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                // to monitor the index and link it with the value
                String location = dataSnapshot.child("Location").getValue(String.class);
                String key = dataSnapshot.getKey();

                int index = mKeys.indexOf(key);
                mRequests.set(index, location);

                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //redirect to paramedic profile activity
        mmListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), OpertionProfile.class);
                startActivity(i);
            }
        });

        return myView;
    }
}