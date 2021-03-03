package com.aryaman.bnhcs;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AimObjective extends AppCompatActivity {
    ImageView aimslogo;
    TextView aimtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aims_and_objective);

        aimslogo = (ImageView) findViewById(R.id.aimslogo);
        aimtext = (TextView) findViewById(R.id.aimtext);

        aimslogo.animate().alpha(1f).setDuration(2500).setStartDelay(500);
        aimtext.animate().alpha(1f).setDuration(2500).setStartDelay(500);
    }
}
