package com.mutairibassam.emergencydevice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
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

public class ParamedicNavActivity extends AppCompatActivity {


    private DatabaseReference mDatabase;
    private ListView mmListview;


    private ArrayList<String> mRequests = new ArrayList<>();
    private ArrayList<String> mKeys = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paramedic_nav);


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navLister);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("requests");
        mmListview = (ListView) findViewById(R.id.requestsListview);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mRequests);
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
                Intent i = new Intent(ParamedicNavActivity.this, ParamedicPatientProfileActivity.class);
                startActivity(i);
            }
        });

    }


        private BottomNavigationView.OnNavigationItemSelectedListener navLister =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        Fragment selectedFragment = null;

                        switch (menuItem.getItemId()) {
                            case R.id.nav_home:
                                selectedFragment = new ParamedicFragmentHomeActivity();
                                break;


                            case R.id.nav_dashboard:
                                selectedFragment = new ParamedicFragemtnDashboardActivity();
                                break;
                        }

                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                selectedFragment).commit();

                        return true;
                    }
                };

}

