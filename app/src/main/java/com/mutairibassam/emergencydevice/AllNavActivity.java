package com.mutairibassam.emergencydevice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AllNavActivity extends AppCompatActivity {

    /*
    This class will not be presented in the real application
    because the main purpose only for clear navigation

     */

    Button btnOperation, btnAdmin, btnParamedic, btnPatient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_nav);

        btnOperation = (Button) findViewById(R.id.xmlbtnOperation);
        btnAdmin = (Button) findViewById(R.id.xmlbtnAdmin);
        btnParamedic = (Button) findViewById(R.id.xmlbtnParamedic);
        btnPatient = (Button) findViewById(R.id.xmlbtnPatient);



        //intent to operation activity
        btnOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllNavActivity.this, OperationNavActivity.class));
            }
        });

        //intent to admin activity
        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllNavActivity.this, AdminNavActivity.class));
            }
        });

        //intent to paramedic activity
        btnParamedic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllNavActivity.this, ParamedicNavActivity.class));
            }
        });

        //intent to patient activity
        btnPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllNavActivity.this, PatientNavActivity.class));
            }
        });

    }

}
