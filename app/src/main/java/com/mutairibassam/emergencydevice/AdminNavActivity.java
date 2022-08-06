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
import java.util.ArrayList;

public class AdminNavActivity extends AppCompatActivity {


    private DatabaseReference mDatabase;
    private ListView mmListview;


    private ArrayList<String> mRequests = new ArrayList<>();
    private ArrayList<String> mKeys = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_nav);


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
                Intent i = new Intent(AdminNavActivity.this, ParamedicPatientProfileActivity.class);
                startActivity(i);
            }
        });

    }



    // bottom Navigation fragment
    private BottomNavigationView.OnNavigationItemSelectedListener navLister =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    //set an empty fragment to be added the selected fragment later
                    Fragment selectedFragment = null;

                    //Home fragment will be shown here
                    switch (menuItem.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;

                        //Add paramedic fragment will be shown here
                        case R.id.nav_add:
                            selectedFragment = new AddFragment();
                            break;

                            //Dashboard fragment will be shown here
                        case R.id.nav_dashboard:
                            selectedFragment = new DashboardFragment();
                            break;
                    }

                    //fragment manager to manage fragment either to be (added, replaced, and removed))
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;

                }
            };

}
