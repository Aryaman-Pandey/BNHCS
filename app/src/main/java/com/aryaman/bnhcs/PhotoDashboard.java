package com.aryaman.bnhcs;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SnapshotMetadata;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.util.ArrayList;
import java.util.Objects;

public class PhotoDashboard extends AppCompatActivity {
    StorageReference mStorageRef;
    RecyclerView recycle;
    FirebaseFirestore db;
    MyAdapter myAdapter;
    ArrayList<DownModel> downModelArrayList = new ArrayList<>();
    ProgressBar pbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_dashboard);

        setUpRV();
        setUpFB();
        dataFromFirebase();
    }

    private void dataFromFirebase(){
        if(downModelArrayList.size()>0)
            downModelArrayList.clear();

        pbar = (ProgressBar) findViewById(R.id.pBar);
        mStorageRef = FirebaseStorage.getInstance().getReference("Images/");
        pbar.setVisibility(View.VISIBLE);
        mStorageRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {

                for(StorageReference prefix: listResult.getPrefixes()){

                    DownModel downModel = new DownModel(prefix.getName());
                    downModelArrayList.add(downModel);

                }

                myAdapter = new MyAdapter(PhotoDashboard.this, downModelArrayList);
                recycle.setAdapter(myAdapter);
                pbar.setVisibility(View.GONE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pbar.setVisibility(View.GONE);
                Toast.makeText(PhotoDashboard.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setUpFB(){
        mStorageRef = FirebaseStorage.getInstance().getReference();
    }

    private void setUpRV(){
        recycle = (RecyclerView) findViewById(R.id.recycle);
        recycle.setHasFixedSize(true);
        recycle.setLayoutManager(new LinearLayoutManager(this));
    }
}
