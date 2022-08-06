package com.mutairibassam.emergencydevice;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class ResetActivity extends AppCompatActivity {

    Dialog myDialog;

    private EditText NationalID;
    private Button btnBack;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        myDialog = new Dialog(this);


        btnBack = (Button) findViewById(R.id.Back);
        auth = FirebaseAuth.getInstance();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResetActivity.this, LoginPage.class));
            }
        });
    }

    public void resetMethod(View v)
    {
        NationalID = (EditText) findViewById(R.id.number_edtext);
        final String nationaldid = NationalID.getText().toString();

        auth.sendPasswordResetEmail(nationaldid).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {


                Toast.makeText(ResetActivity.this, "6-digits code has been successfully sent to your mobile phone", Toast.LENGTH_SHORT).show();

                TextView txtclose;
                Button btnSubmit;
                myDialog.setContentView(R.layout.activity_popup_message);
                txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
                btnSubmit = (Button) myDialog.findViewById(R.id.btnSubmit2);
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });

                myDialog.show();

                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        myDialog.dismiss();
                        Toast.makeText(ResetActivity.this, "Password has been sent successfully", Toast.LENGTH_SHORT).show();

                    }

                });

                // To clean the edit text after submitting
                NationalID.setText("");
            }

        });


    }

}

