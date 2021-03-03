package com.aryaman.bnhcs;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

                if (firebaseUser != null) {

                    final String userId = firebaseUser.getUid();
                    DocumentReference documentReference = firebaseFirestore.collection("student").document("st"+userId);
                    documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(task.isSuccessful()){
                                DocumentSnapshot documentSnapshot = task.getResult();
                                assert documentSnapshot != null;
                                if(documentSnapshot.exists())
                                {
                                    startActivity(new Intent(SplashActivity.this, StudentDashboard.class));
                                    finish();
                                }
                                else{
                                    DocumentReference documentReference1 = firebaseFirestore.collection("teacher").document("tc"+userId);
                                    documentReference1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                            DocumentSnapshot documentSnapshot1 = task.getResult();
                                            assert documentSnapshot1 != null;
                                            if(documentSnapshot1.exists()){
                                                startActivity(new Intent(SplashActivity.this, TeacherDashboard.class));
                                                finish();
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    });

                }
                else
                {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },2500);

    }
}
