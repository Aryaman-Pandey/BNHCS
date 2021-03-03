package com.aryaman.bnhcs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoticeRecycler extends RecyclerView.Adapter<NoticeRecycler.NoticeViewHolder> {

    Context context;
    ArrayList<NoticeInfo> MainNoticeInfo;

    public NoticeRecycler(Context context, ArrayList<NoticeInfo> mainNoticeInfo) {
        this.context = context;
        this.MainNoticeInfo = mainNoticeInfo;
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_element, parent, false);
        NoticeRecycler.NoticeViewHolder viewHolder = new NoticeRecycler.NoticeViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, int position) {
        NoticeInfo UploadInfo = MainNoticeInfo.get(position);
        holder.heading.setText(UploadInfo.getHeading());
        holder.note.setText(UploadInfo.getNote());
    }

    @Override
    public int getItemCount() {


        return (null != MainNoticeInfo ? MainNoticeInfo.size() : 0);
    }

    static class NoticeViewHolder extends RecyclerView.ViewHolder {

        public TextView heading;
        public TextView note;

        public NoticeViewHolder(View itemView) {
            super(itemView);

            heading = (TextView) itemView.findViewById(R.id.header);

            note = (TextView) itemView.findViewById(R.id.thenotice);
        }
    }
}
