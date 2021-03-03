package com.aryaman.bnhcs;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import static android.widget.Toast.*;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context context;
    ArrayList<ImageUploadInfo> MainImageUploadInfoList;
    ArrayList<String> str;
    public RecyclerViewAdapter(Context context, ArrayList<ImageUploadInfo> TempList, ArrayList<String> temp) {
        this.MainImageUploadInfoList = TempList;
        this.context = context;
        this.str = temp;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);
        RecyclerViewAdapter.ViewHolder viewHolder = new RecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ImageUploadInfo UploadInfo = MainImageUploadInfoList.get(position);
        holder.imageNameTextView.setText(UploadInfo.getImageName());
        //Toast.makeText(context, str.get(position)+"", LENGTH_SHORT).show();
        Glide.with(context).load(UploadInfo.getImageURL()).into(holder.imageView);
    }
    @Override
    public int getItemCount() {
        return MainImageUploadInfoList.size();
    }

static class ViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView imageNameTextView;

    public ViewHolder(View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.imageView);

        imageNameTextView = (TextView) itemView.findViewById(R.id.ImageNameTextView);
    }
    }
}