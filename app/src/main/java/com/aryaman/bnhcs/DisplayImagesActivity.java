package com.aryaman.bnhcs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.StreamDownloadTask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class DisplayImagesActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    StorageReference storageReference;
    // Creating RecyclerView.
    RecyclerView recyclerView;
    // Creating RecyclerView.Adapter.
    RecyclerViewAdapter adapter ;
    // Creating Progress dialog
    ProgressDialog progressDialog;
    ImageUploadInfo imageUploadInfo = new ImageUploadInfo();
    ImageUploadInfo post;
    final ArrayList<String> str = new ArrayList<>();

    // Creating List of ImageUploadInfo class.
    final ArrayList<ImageUploadInfo> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_images);

        final Intent intent = getIntent();
        final String foldername = intent.getStringExtra("foldername");
        Toast.makeText(DisplayImagesActivity.this, foldername, Toast.LENGTH_LONG).show();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(DisplayImagesActivity.this));
        progressDialog = new ProgressDialog(DisplayImagesActivity.this);
        progressDialog.setMessage("Loading Images From Firebase.");
        progressDialog.show();
        databaseReference = FirebaseDatabase.getInstance().getReference("Images/"+foldername+"/");

        storageReference = FirebaseStorage.getInstance().getReference("Images/"+foldername+"/");


        storageReference.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(final ListResult listResult) {

                for (final StorageReference storageReference1 : listResult.getItems()) {

                    Toast.makeText(DisplayImagesActivity.this, storageReference1.getPath(), Toast.LENGTH_SHORT).show();
                    String abc = "https://firebasestorage.googleapis.com/v0/b/bnhcs-52055.appspot.com/o/Images%2F"+foldername+"%2F"+storageReference1.getName()+"?alt=media";
                    imageUploadInfo = new ImageUploadInfo(storageReference1.getName(), abc);
                    gotit(imageUploadInfo);
                }

                adapter = new RecyclerViewAdapter(DisplayImagesActivity.this, list, str);

                recyclerView.setAdapter(adapter);
                // Hiding the progress dialog.
                progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(DisplayImagesActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        //bnhcs-52055.appspot.com/Images/Impulse

}
public void gotit(ImageUploadInfo imageUploadInfo){
    this.str.add(imageUploadInfo.getImageURL().toString());
    this.list.add(imageUploadInfo);
}

}
