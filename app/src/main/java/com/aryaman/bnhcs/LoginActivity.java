package com.aryaman.bnhcs;

import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.model.value.ReferenceValue;

import java.util.Collection;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.O)
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    Button logbtn;
    Button createbtn;
    Button fpid;
    FirebaseFirestore ftp;
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
        user = FirebaseAuth.getInstance().getCurrentUser();

        createbtn = (Button)findViewById(R.id.createbtn);

        fpid = (Button)findViewById(R.id.fpid);

        Intent intent = getIntent();
        int counter = intent.getIntExtra("counter", 0);
        if(counter == 0)
        {
            createbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent3 = new Intent(LoginActivity.this,CreateStudent.class);
                    startActivity(intent3);
                    finish();
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
                    finish();
                }
            });
        }

        //loggedin

        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userid = username.getText().toString().trim();
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
                        if(task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Log In Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = getIntent();
                            int counter = intent.getIntExtra("counter", 0);
                            if (counter == 0) {
                                startActivity(new Intent(LoginActivity.this, StudentDashboard.class));
                                finish();
                            }
                            else if(counter == 1)
                            {
                                startActivity(new Intent(LoginActivity.this, TeacherDashboard.class));
                                finish();
                            }

                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Error ! " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

        fpid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText resetmail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password?");
                passwordResetDialog.setMessage("Enter Your Email Address to get Password Reset Link");
                passwordResetDialog.setView(resetmail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String mail = resetmail.getText().toString();
                        firebaseAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(LoginActivity.this, "Reset Link has been sent to your Email", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this, "Error! Could not send Reset Link"+ e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });


                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                passwordResetDialog.create().show();

            }
        });
    }
}
