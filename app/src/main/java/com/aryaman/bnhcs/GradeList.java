package com.aryaman.bnhcs;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GradeList extends AppCompatActivity {
    TextView nursery, lkg, ukg, first, second, third, fourth, fifth, sixth, seven, eight;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grade_list);

        nursery = (TextView) findViewById(R.id.nursery);
        lkg = (TextView) findViewById(R.id.lkg);
        ukg = (TextView) findViewById(R.id.ukg);
        first = (TextView) findViewById(R.id.first);
        second = (TextView) findViewById(R.id.second);
        third = (TextView) findViewById(R.id.third);
        fourth = (TextView) findViewById(R.id.fourth);
        fifth = (TextView) findViewById(R.id.fifth);
        sixth = (TextView) findViewById(R.id.sixth);
        seven = (TextView) findViewById(R.id.seven);
        eight = (TextView) findViewById(R.id.eight);

        lkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager packageManager = getApplicationContext().getPackageManager();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                try {
                    String url = "https://chat.whatsapp.com/JaJaWthAWFQA7ol6LWqcek";
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

        ukg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager packageManager = getApplicationContext().getPackageManager();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                try {
                    String url = "https://chat.whatsapp.com/HmfUt2k3mPp0W0GNxdEnci";
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

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager packageManager = getApplicationContext().getPackageManager();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                try {
                    String url = "https://chat.whatsapp.com/KtU1oNWb7eD8GkI9pLsWy2";
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

        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager packageManager = getApplicationContext().getPackageManager();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                try {
                    String url = "https://chat.whatsapp.com/FtlerVssgFRDEu37dnrUlN";
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

    }
}
