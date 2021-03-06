package com.aryaman.bnhcs;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

public class TeacherDashboard extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    ImageView withoutbar, bnimage;
    ImageView contactbtn, fees, about, gallery, addnotice;
    Animation big_anim;
    ImageButton profilebutton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_dashboard);

        withoutbar = (ImageView) findViewById(R.id.withoutbar);
        bnimage = (ImageView) findViewById(R.id.bnimage);
        profilebutton = (ImageButton) findViewById(R.id.profilebutton);

        big_anim = AnimationUtils.loadAnimation(TeacherDashboard.this, R.anim.big_anim);

        bnimage.animate().alpha(1f).setDuration(2500).setStartDelay(500);
        withoutbar.animate().translationY(50).setDuration(2000).setStartDelay(150);
        withoutbar.startAnimation(big_anim);

        contactbtn = (ImageView) findViewById(R.id.contactbtn);
        fees = (ImageView) findViewById(R.id.fees);
        about = (ImageView) findViewById(R.id.about);
        gallery = (ImageView) findViewById(R.id.gallery);
        addnotice =  (ImageView) findViewById(R.id.addnotice);

        profilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherDashboard.this, ProfileTeacher.class));
            }
        });

        contactbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherDashboard.this, ContactDashboard.class));
            }
        });

        fees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherDashboard.this, FeesDashboard.class));
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherDashboard.this, SchoolDashboard.class));
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherDashboard.this, GalleryDashboard.class));
            }
        });

        addnotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherDashboard.this, AddNotice.class));
            }
        });

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        firebaseAuth = FirebaseAuth.getInstance();

        if(item.getItemId()==R.id.log_out)
        {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(TeacherDashboard.this, LoginActivity.class));
            finish();
        }
        return true;
    }
}
