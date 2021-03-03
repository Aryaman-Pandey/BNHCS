package com.aryaman.bnhcs;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SchoolDashboard extends AppCompatActivity {
    ImageView withoutbar, aboutschool;
    TextView abttext;
    ImageView ourschool, info, ourfounder, mission, objective, prayers;
    Animation big_anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.school_dashboard);

        withoutbar = (ImageView) findViewById(R.id.withoutbar);
        aboutschool = (ImageView) findViewById(R.id.aboutschool);
        abttext = (TextView) findViewById(R.id.abttext);
        ourschool = (ImageView) findViewById(R.id.ourschool);
        info = (ImageView) findViewById(R.id.info);
        ourfounder = (ImageView) findViewById(R.id.ourfounder);
        mission = (ImageView) findViewById(R.id.mission);
        objective = (ImageView) findViewById(R.id.objective);
        prayers = (ImageView) findViewById(R.id.prayers);

        big_anim = AnimationUtils.loadAnimation(SchoolDashboard.this, R.anim.big_anim);

        aboutschool.animate().alpha(1f).setDuration(2500).setStartDelay(500);
        abttext.animate().alpha(1f).setDuration(2500).setStartDelay(500);
        withoutbar.animate().translationY(50).setDuration(2000).setStartDelay(150);
        withoutbar.startAnimation(big_anim);

        ourschool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SchoolDashboard.this, OurSchool.class));
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SchoolDashboard.this, Information.class));
            }
        });

        ourfounder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SchoolDashboard.this, OurFounder.class));
            }
        });

        mission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SchoolDashboard.this, OurMission.class));
            }
        });

        objective.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SchoolDashboard.this, AimObjective.class));
            }
        });

        prayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SchoolDashboard.this, Prayer.class));
            }
        });

    }
}
