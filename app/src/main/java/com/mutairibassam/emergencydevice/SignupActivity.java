package com.mutairibassam.emergencydevice;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mutairibassam.emergencydevice.LoginPage;
import com.mutairibassam.emergencydevice.R;

public class SignupActivity extends AppCompatActivity {

    private EditText txtNationalID;
    private EditText txtPassword, txtPassword2;
    private Button btnBack;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        txtNationalID = (EditText) findViewById(R.id.NationalID);
        txtPassword = (EditText) findViewById(R.id.Password);
        txtPassword2 = (EditText) findViewById(R.id.Password2);
        btnBack = (Button) findViewById(R.id.btnBack2);
        firebaseAuth = FirebaseAuth.getInstance();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginPage.class));
            }
        });


    }



    public void navigation(View v) {

        String name = txtNationalID.getText().toString().trim();
        String email = txtNationalID.getText().toString() + "@gmail.com";
        String password = txtPassword.getText().toString().trim();
        String confirmPass = txtPassword2.getText().toString().trim();

        if (name.isEmpty()) {
            txtNationalID.setError(getString(R.string.input_error_name));
            txtNationalID.requestFocus();
            return;
        }

        if (name.length() != 10) {
            txtNationalID.setError(getString(R.string.input_error_name));
            txtNationalID.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            txtPassword.setError(getString(R.string.input_error_password));
            txtPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            txtPassword.setError(getString(R.string.input_error_password_length));
            txtPassword.requestFocus();
            return;
        }

        if (!password.equals(confirmPass)) {
            txtPassword2.setError(getString(R.string.input_error_password_confirmation));
            txtPassword2.requestFocus();
            return;
        }


        final ProgressDialog progressDialog = ProgressDialog.show(SignupActivity.this, "Please wait...", "Processing...", true);

        (firebaseAuth.createUserWithEmailAndPassword(email, password))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            Toast.makeText(SignupActivity.this, "Registration successful", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(SignupActivity.this, LoginPage.class);
                            startActivity(i);
                        } else {
                            Log.e("ERROR", task.getException().toString());
                            Toast.makeText(SignupActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}