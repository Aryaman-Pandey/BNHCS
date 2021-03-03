package com.aryaman.bnhcs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoticeBoard extends AppCompatActivity {
    RecyclerView recyclenotice;
    ProgressBar noticepbar;
    NoticeRecycler adapter ;
    NoticeInfo noticeInfo = new NoticeInfo();
    final ArrayList<NoticeInfo> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_board);


        recyclenotice = (RecyclerView) findViewById(R.id.recyclenotice);
        noticepbar = (ProgressBar) findViewById(R.id.noticepBar);

        Intent intent = getIntent();
        final String nheading = intent.getStringExtra("mheading");


        final String nnote = intent.getStringExtra("mnote");

        recyclenotice.setHasFixedSize(true);
        recyclenotice.setLayoutManager(new LinearLayoutManager(NoticeBoard.this));
        noticepbar.setVisibility(View.VISIBLE);
        noticeInfo = new NoticeInfo(nheading+"", nnote+"");
        noticeInfo.setHeading(nheading+"");
        noticeInfo.setNote(nnote+"");
        this.list.add(noticeInfo);
        adapter = new NoticeRecycler(NoticeBoard.this, list);
        recyclenotice.setAdapter(adapter);
        noticepbar.setVisibility(View.GONE);

    }
}
