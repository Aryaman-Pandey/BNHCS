package com.aryaman.bnhcs;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class OurSchool extends AppCompatActivity {
    ImageView ourschoollogo;
    TextView ostext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.our_school);

        ourschoollogo = (ImageView) findViewById(R.id.ourschoollogo);
        ostext = (TextView) findViewById(R.id.ostext);

        ourschoollogo.animate().alpha(1f).setDuration(2500).setStartDelay(500);
        ostext.animate().alpha(1f).setDuration(2500).setStartDelay(500);

    }
}
