package com.example.javaappversion19.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaappversion19.Models.Struct_Playlist;
import com.example.javaappversion19.R;

import java.util.ArrayList;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {

    Context context ;
    ArrayList<Struct_Playlist> dataItems ;

    public PlaylistAdapter(Context context, ArrayList<Struct_Playlist> dataItems) {
        this.context = context;
        this.dataItems = dataItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.playlist_card , parent  , false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imageView.setImageResource(dataItems.get(position).getImageID());
        holder.playlistName.setText(dataItems.get(position).getPlaylistName());
        holder.noofSongs.setText(dataItems.get(position).getNoOfSongs());

    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView playlistName , noofSongs;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            playlistName = itemView.findViewById(R.id.playlistName);
            noofSongs = itemView.findViewById(R.id.singer);

        }
    }
}
