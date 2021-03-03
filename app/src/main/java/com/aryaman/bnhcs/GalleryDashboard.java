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

public class GalleryDashboard extends AppCompatActivity {
    ImageView withoutbar, gallerylogo, photo, video;
    Animation big_anim;
    TextView gtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_layout);

        withoutbar = (ImageView) findViewById(R.id.withoutbar);
        gallerylogo = (ImageView) findViewById(R.id.gallerylogo);
        gtext = (TextView) findViewById(R.id.gtext);
        photo = (ImageView) findViewById(R.id.photo);
        video = (ImageView) findViewById(R.id.video);

        big_anim = AnimationUtils.loadAnimation(GalleryDashboard.this, R.anim.big_anim);

        gallerylogo.animate().alpha(1f).setDuration(250).setStartDelay(500);
        gtext.animate().alpha(1f).setDuration(2500).setStartDelay(500);
        withoutbar.animate().translationY(50).setDuration(2000).setStartDelay(150);
        withoutbar.startAnimation(big_anim);

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GalleryDashboard.this, PhotoDashboard.class));
            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.youtube.com/channel/UCW4yscWOUXJh8aWLPs4rZ-g");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }
}
