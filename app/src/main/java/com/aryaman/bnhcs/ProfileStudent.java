package com.aryaman.bnhcs;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;


public class ProfileStudent extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView firstname, lastname, email;
    Button savebutton;
    Spinner gradechooser;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_student);

        firstname = (TextView) findViewById(R.id.firstname);
        lastname = (TextView) findViewById(R.id.lastname);
        email = (TextView) findViewById(R.id.email);

        savebutton = (Button) findViewById(R.id.savebutton);

        gradechooser = (Spinner) findViewById(R.id.gradechooser);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(ProfileStudent.this, R.array.grades, R.layout.support_simple_spinner_dropdown_item);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        gradechooser.setAdapter(adapter);


        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        if (firebaseUser != null) {

            final String userId = firebaseUser.getUid();
            final DocumentReference documentReference = firebaseFirestore.collection("student").document("st" + userId);
            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    String first = documentSnapshot.getString("fname");
                    firstname.setText(first);
                    String last = documentSnapshot.getString("lname");
                    lastname.setText(last);
                    String emailid = documentSnapshot.getString("email");
                    email.setText(emailid);
                    String gradeid = documentSnapshot.getString("grade");
                    int item = adapter.getPosition(gradeid);
                    gradechooser.setSelection(item);

                    gradechooser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(final AdapterView<?> parent, View view, final int position, long id) {

                            savebutton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    final AlertDialog.Builder confirmbox = new AlertDialog.Builder(v.getContext(), R.style.Alert);
                                    confirmbox.setTitle("Confirm");
                                    confirmbox.setMessage("Are You Sure You Want to Save the Information ?");

                                    confirmbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            Map<String, Object> student = new HashMap<>();
                                            student.put("grade", parent.getItemAtPosition(position).toString());
                                            documentReference.update(student);
                                            Toast.makeText(ProfileStudent.this, "Changes Saved", Toast.LENGTH_SHORT).show();

                                        }
                                    });

                                    confirmbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });

                                    confirmbox.create().show();

                                }
                            });


                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }
            });
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
