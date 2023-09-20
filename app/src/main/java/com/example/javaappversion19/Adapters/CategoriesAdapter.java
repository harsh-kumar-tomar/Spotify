package com.example.javaappversion19.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaappversion19.Models.Struct_Categories;
import com.example.javaappversion19.R;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    ArrayList<Struct_Categories> dataItems;
    Context context;

    public CategoriesAdapter(Context context , ArrayList<Struct_Categories> dataItems) {
        this.dataItems = dataItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.song_card, parent  , false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imageView.setImageResource(dataItems.get(position).getImageID());
        holder.textView.setText(dataItems.get(position).getCategoryName());

    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textview);


        }
    }
}
