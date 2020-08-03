package com.aryaman.bnhcs;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.O)
    FirebaseAuth firebaseAuth;
    Button logbtn;
    Button createbtn;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        final EditText username = (EditText)findViewById(R.id.username);
        username.setAutofillHints(View.AUTOFILL_HINT_EMAIL_ADDRESS);

        final EditText password = (EditText)findViewById(R.id.password);
        password.setAutofillHints(View.AUTOFILL_HINT_PASSWORD);

        logbtn = (Button)findViewById(R.id.logbtn);

        firebaseAuth = FirebaseAuth.getInstance();

        createbtn = (Button)findViewById(R.id.createbtn);

        Intent intent = getIntent();
        int counter = intent.getIntExtra("counter", 0);
        if(counter == 0)
        {
            createbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent3 = new Intent(LoginActivity.this,CreateStudent.class);
                    startActivity(intent3);
                }
            });
        }
        else if(counter == 1)
        {
            createbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent4 = new Intent(LoginActivity.this, CreateTeacher.class);
                    startActivity(intent4);
                }
            });
        }

        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userid = username.getText().toString().trim();
                String passid = password.getText().toString().trim();

                if(TextUtils.isEmpty(userid)){
                    username.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(passid)){
                    password.setError("Password is Required");
                    return;
                }

                //authentication

                firebaseAuth.signInWithEmailAndPassword(userid, passid).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Log In Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Error ! " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}
