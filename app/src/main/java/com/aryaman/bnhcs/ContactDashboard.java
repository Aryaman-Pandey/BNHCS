package com.aryaman.bnhcs;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.common.base.Utf8;
import com.google.firebase.auth.FirebaseAuth;

import java.net.URLEncoder;

public class ContactDashboard extends AppCompatActivity {
    ImageView withoutbar, contactlogo;
    ImageView callus, reachus, mailus, messageus;
    TextView cutitle;
    Animation big_anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_dashboard);

        withoutbar = (ImageView) findViewById(R.id.withoutbar);
        contactlogo = (ImageView) findViewById(R.id.contactlogo);
        cutitle = (TextView) findViewById(R.id.cutitle);

        big_anim = AnimationUtils.loadAnimation(ContactDashboard.this, R.anim.big_anim);

        contactlogo.animate().alpha(1f).setDuration(2500).setStartDelay(500);
        cutitle.animate().alpha(1f).setDuration(2500).setStartDelay(500);
        withoutbar.animate().translationY(50).setDuration(2000).setStartDelay(150);
        withoutbar.startAnimation(big_anim);

        callus = (ImageView) findViewById(R.id.callus);
        reachus = (ImageView) findViewById(R.id.reachus);
        mailus = (ImageView) findViewById(R.id.mailus);
        messageus = (ImageView) findViewById(R.id.messageus);

        callus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "917458891111", null)));
            }
        });

        reachus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://maps.app.goo.gl/smGybTh9VRerQTSPA");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        mailus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] mail = {"bnhcshool@gmail.com"};
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, mail);
                intent.setType("text/plain");
                intent.setPackage("com.google.android.gm");
                startActivity(Intent.createChooser(intent, "Send mail"));
            }
        });

        messageus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager packageManager = getApplicationContext().getPackageManager();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                try {
                    String phn = "+917007119161";
                    String url = "https://api.whatsapp.com/send?phone="+ phn + "&text=" + URLEncoder.encode("", "UTF-8");
                    intent.setPackage("com.whatsapp");
                    intent.setData(Uri.parse(url));
                    if(intent.resolveActivity(packageManager)!=null)
                    {
                        startActivity(intent);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        });

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
