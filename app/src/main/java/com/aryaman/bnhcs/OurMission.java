package com.aryaman.bnhcs;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class OurMission extends AppCompatActivity {
    ImageView missionlogo, withoutbar;
    TextView misstext;
    Animation big_anim;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.our_mission);

        missionlogo = (ImageView) findViewById(R.id.missionlogo);
        misstext = (TextView) findViewById(R.id.misstext);
        withoutbar = (ImageView) findViewById(R.id.withoutbar);
        big_anim = AnimationUtils.loadAnimation(OurMission.this, R.anim.big_anim);

        missionlogo.animate().alpha(1f).setDuration(2500).setStartDelay(500);
        misstext.animate().alpha(1f).setDuration(2500).setStartDelay(500);

    }
}
