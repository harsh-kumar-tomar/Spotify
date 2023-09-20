package com.example.javaappversion19.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.javaappversion19.Adapters.CategoriesAdapter;
import com.example.javaappversion19.Adapters.PlaylistAdapter;
import com.example.javaappversion19.Models.Struct_Categories;
import com.example.javaappversion19.Models.Struct_Playlist;
import com.example.javaappversion19.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private Switch switchView;
    GridLayoutManager layoutManager;
    RecyclerView CategoriesRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initialize();

        ArrayList<Struct_Categories> dataItems = new ArrayList<>() ;
        dataItems.add(new Struct_Categories(R.drawable.darlinginthefranxx , "Hindi"));
        dataItems.add(new Struct_Categories(R.drawable.journeyofelaina , "English"));
        dataItems.add(new Struct_Categories(R.drawable.deathnote , "Lofi"));
        dataItems.add(new Struct_Categories(R.drawable.demonslayer , "Fun"));
        dataItems.add(new Struct_Categories(R.drawable.darlinginthefranxx , "Hindi"));
        dataItems.add(new Struct_Categories(R.drawable.journeyofelaina , "English"));
        dataItems.add(new Struct_Categories(R.drawable.deathnote , "Lofi"));
        dataItems.add(new Struct_Categories(R.drawable.demonslayer , "Fun"));
        CategoriesAdapter adapter = new CategoriesAdapter(MainActivity.this , dataItems);




        layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);
        CategoriesRecyclerView.setLayoutManager(layoutManager);




        CategoriesRecyclerView.setAdapter(adapter);


        RecyclerView PlaylistRecyclerView = findViewById(R.id.playlistRecyclerView);

        ArrayList<Struct_Playlist> playlistdataItems = new ArrayList<>();
        playlistdataItems.add(new Struct_Playlist(R.drawable.demonslayer , "Liked songs" , "9 songs"));
        playlistdataItems.add(new Struct_Playlist(R.drawable.deathnote , "Lover" , "10 songs"));
        playlistdataItems.add(new Struct_Playlist(R.drawable.journeyofelaina , "Anime songs" , "21 songs"));
        playlistdataItems.add(new Struct_Playlist(R.drawable.darlinginthefranxx , "Romantic songs" , "7 songs"));
        playlistdataItems.add(new Struct_Playlist(R.drawable.demonslayer , "Liked songs" , "9 songs"));
        playlistdataItems.add(new Struct_Playlist(R.drawable.deathnote , "Lover" , "10 songs"));
        playlistdataItems.add(new Struct_Playlist(R.drawable.journeyofelaina , "Anime songs" , "21 songs"));
        playlistdataItems.add(new Struct_Playlist(R.drawable.darlinginthefranxx , "Romantic songs" , "7 songs"));

        PlaylistAdapter playlistAdapter = new PlaylistAdapter(MainActivity.this  , playlistdataItems);


        PlaylistRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        PlaylistRecyclerView.setAdapter(playlistAdapter);




        switchView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    layoutManager = new GridLayoutManager(MainActivity.this, 2, GridLayoutManager.VERTICAL, false);
                    CategoriesRecyclerView.setLayoutManager(layoutManager);


                } else {

                    layoutManager = new GridLayoutManager(MainActivity.this, 2, GridLayoutManager.HORIZONTAL, false);
                    CategoriesRecyclerView.setLayoutManager(layoutManager);
                }
            }
        });








    }

    public void play(View view)
    {
        Intent intent = new Intent(MainActivity.this , Music_Playing_Activity.class);
        startActivity(intent);
    }


    private void initialize() {
        switchView = findViewById(R.id.switchButton);
        CategoriesRecyclerView = findViewById(R.id.CategoriesRecylerView);


    }
}