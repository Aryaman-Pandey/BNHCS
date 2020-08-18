package com.aryaman.bnhcs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {
    public int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button student = (Button)findViewById(R.id.student);
        Button teacher = (Button)findViewById(R.id.teacher);
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                intent1.putExtra("counter", 0);
                startActivity(intent1);
                finish();
            }
        });
        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText seccode = new EditText(v.getContext());
                AlertDialog.Builder security = new AlertDialog.Builder(v.getContext());
                security.setTitle("Teacher Authentication !!!");
                security.setMessage("Enter the Security Code provided by Administration");
                security.setView(seccode);
                security.setPositiveButton("Verify", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String code = seccode.getText().toString();
                        if(code.equals("1815"))
                        {
                            Intent intent2 = new Intent(MainActivity.this, LoginActivity.class);
                            intent2.putExtra("counter",1);
                            startActivity(intent2);
                            finish();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Error! Wrong Security Code", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                security.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                security.create().show();
            }
        });
    }
}