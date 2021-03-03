package com.aryaman.bnhcs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.w3c.dom.Text;

public class FeesDashboard extends AppCompatActivity {
    ImageView withoutbar, feeslogo;
    TextView feetitle,p8;
    Animation big_anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fees_dashboard);

        withoutbar = (ImageView) findViewById(R.id.withoutbar);
        feeslogo = (ImageView) findViewById(R.id.feeslogo);
        feetitle = (TextView) findViewById(R.id.feetitle);
        p8 = (TextView) findViewById(R.id.p8);


        feeslogo.animate().alpha(1f).setDuration(2500).setStartDelay(500);
        feetitle.animate().alpha(1f).setDuration(2500).setStartDelay(500);

        p8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "917458891111", null)));
            }
        });


        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
