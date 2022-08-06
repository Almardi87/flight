package com.mutairibassam.emergencydevice;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {


    private EditText inputNationalID, inputPassword;
    private FirebaseAuth auth;
    private Button btnSign, btnLog, btnRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);


        inputNationalID = (EditText) findViewById(R.id.NationalID);
        inputPassword = (EditText) findViewById(R.id.Password);
        btnSign = (Button) findViewById(R.id.btnSignUp);
        btnLog = (Button) findViewById(R.id.btnLogin);
        btnRes = (Button) findViewById(R.id.btnReset);

        auth = FirebaseAuth.getInstance();


        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this, SignupActivity.class));
            }
        });

        btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this, ResetActivity.class));
            }
        });

        // Login button and validation
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int NationalID = inputNationalID.getText().length();
                final String email = inputNationalID.getText().toString() + "@gmail.com";
                final String password = inputPassword.getText().toString();

                //if condition to validate the input

                if (NationalID != 10) {
                    Toast.makeText(getApplicationContext(), "Please Enter Valid National ID!", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter a password!", Toast.LENGTH_LONG).show();
                    return;
                }


                // check the auth in the firebase
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginPage.this, new OnCompleteListener<AuthResult>() {

                          @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (!task.isSuccessful()) {

                                    Log.e("ERROR AUTHENTICATION", "Password and confirmation do not match" + task.getException().getMessage());

                                    Toast.makeText(LoginPage.this, task.getException().getMessage().toString(), Toast.LENGTH_LONG).show();
                                } else {
                                    Intent intent = new Intent(LoginPage.this, AllNavActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });


                }

        });
    }
}


















