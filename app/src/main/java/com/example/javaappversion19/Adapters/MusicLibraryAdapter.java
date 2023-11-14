package com.example.javaappversion19.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.javaappversion19.Activities.Music_Playing_Activity;
import com.example.javaappversion19.Models.Struct_Music_Library;
import com.example.javaappversion19.R;

import java.util.ArrayList;

public class MusicLibraryAdapter extends RecyclerView.Adapter<MusicLibraryAdapter.ViewHolder> {

    Context context;
    ArrayList<Struct_Music_Library> dataList ;

    public MusicLibraryAdapter(Context context, ArrayList<Struct_Music_Library> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MusicLibraryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.playlist_card  , parent , false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MusicLibraryAdapter.ViewHolder holder, int position) {

        holder.SongName.setText(dataList.get(position).getSongName());
        holder.singerName.setText(dataList.get(position).getSinger());
//        holder.img.setImageResource(R.drawable.demonslayer);

        Glide.with(context)
                        .load(dataList.get(position).getImgUrl())
                                .into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , Music_Playing_Activity.class);
                intent.putExtra("url" , dataList.get(position).getUrl());
                intent.putExtra("imgurl" , dataList.get(position).getImgUrl());
                intent.putExtra("songname" , dataList.get(position).getSongName());
                intent.putExtra("singername" , dataList.get(position).getSinger());



                ((Activity) context).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView SongName , singerName;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            SongName = itemView.findViewById(R.id.playlistName);
            singerName = itemView.findViewById(R.id.singer);
            img = itemView.findViewById(R.id.image);

        }
    }


}
