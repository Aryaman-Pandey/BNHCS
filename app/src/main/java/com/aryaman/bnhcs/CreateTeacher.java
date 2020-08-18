package com.aryaman.bnhcs;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static android.view.View.AUTOFILL_HINT_EMAIL_ADDRESS;
import static android.view.View.AUTOFILL_HINT_NAME;
import static android.view.View.AUTOFILL_HINT_PASSWORD;
import static com.google.firebase.auth.FirebaseAuth.getInstance;

public class CreateTeacher extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.O)
    EditText fname;
    @RequiresApi(api = Build.VERSION_CODES.O)
    EditText lname;
    @RequiresApi(api = Build.VERSION_CODES.O)
    EditText email;
    @RequiresApi(api = Build.VERSION_CODES.O)
    EditText pswrd;
    @RequiresApi(api = Build.VERSION_CODES.O)
    Button crtbtn;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore fstore;
    String teacherID;
    ProgressBar progressBar;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_teacher);

        fname = (EditText)findViewById(R.id.fname);
        fname.setAutofillHints(AUTOFILL_HINT_NAME);

        lname = (EditText)findViewById(R.id.lname);
        lname.setAutofillHints(AUTOFILL_HINT_NAME);

        email = (EditText)findViewById(R.id.email);
        email.setAutofillHints(AUTOFILL_HINT_EMAIL_ADDRESS);

        pswrd = (EditText)findViewById(R.id.pswrd);
        pswrd.setAutofillHints(AUTOFILL_HINT_PASSWORD);

        crtbtn = (Button)findViewById(R.id.crtbtn);
        firebaseAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        crtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mfname = fname.getText().toString().trim();
                final String mlname = lname.getText().toString().trim();
                final String memail = email.getText().toString().trim();
                final boolean TeacherVerify = true;
                String mpswrd = pswrd.getText().toString().trim();

                if(TextUtils.isEmpty(mfname)){
                    fname.setError("First Name is Required");
                    return;
                }
                if(TextUtils.isEmpty(mlname)){
                    lname.setError("Last Name is Required");
                    return;
                }
                if(TextUtils.isEmpty(memail)){
                    email.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(mpswrd)){
                    pswrd.setError("Password is Required");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                final Task<AuthResult> user_created = firebaseAuth.createUserWithEmailAndPassword(memail, mpswrd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(CreateTeacher.this, "User Created", Toast.LENGTH_SHORT).show();
                            teacherID = firebaseAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("teacher").document("tc"+teacherID);
                            Map<String, Object> teacher = new HashMap<>();
                            teacher.put("fname", mfname);
                            teacher.put("lname", mlname);
                            teacher.put("email", memail);
                            teacher.put("teacher", TeacherVerify);
                            documentReference.set(teacher).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG", "onSuccess: User Profile is Created for"+teacherID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("msg", "onFailure "+ e.toString());
                                }
                            });
                            FirebaseAuth.getInstance().signOut();
                            Intent intent = new Intent(CreateTeacher.this, LoginActivity.class);
                            intent.putExtra("counter", 1);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(CreateTeacher.this, "Error ! " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

    }

}
