package com.aryaman.bnhcs;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView foldername;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        foldername = itemView.findViewById(R.id.foldername);

    }
}
