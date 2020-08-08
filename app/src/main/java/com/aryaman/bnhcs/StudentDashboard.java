package com.aryaman.bnhcs;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

public class StudentDashboard extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    ImageView withoutbar;
    Animation big_anim;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_dashboard);

        withoutbar = (ImageView) findViewById(R.id.withoutbar);

        big_anim = AnimationUtils.loadAnimation(StudentDashboard.this, R.anim.big_anim);

        withoutbar.animate().translationY(50).setDuration(1500).setStartDelay(150);
        withoutbar.startAnimation(big_anim);

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
                startActivity(new Intent(StudentDashboard.this, LoginActivity.class));
                finish();
        }
        return true;
    }
}
