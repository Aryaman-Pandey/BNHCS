package com.aryaman.bnhcs;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

public class OurFounder extends AppCompatActivity {
    TextView oftext;
    ImageView ourfounderlogo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.our_founder);

        oftext = (TextView) findViewById(R.id.oftext);
        ourfounderlogo = (ImageView) findViewById(R.id.ourfounderlogo);

        oftext.animate().alpha(1f).setDuration(2500).setStartDelay(500);
        ourfounderlogo.animate().alpha(1f).setDuration(2500).setStartDelay(500);

    }
}
