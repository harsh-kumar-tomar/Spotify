package com.example.javaappversion19.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.javaappversion19.Adapters.CategoriesAdapter;
import com.example.javaappversion19.Adapters.PlaylistAdapter;
import com.example.javaappversion19.Fragments.home_Fragment;
import com.example.javaappversion19.Fragments.library_Fragment;
import com.example.javaappversion19.Fragments.search_Fragment;
import com.example.javaappversion19.Models.Struct_Categories;
import com.example.javaappversion19.Models.Struct_Playlist;
import com.example.javaappversion19.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private Switch switchView;
    GridLayoutManager layoutManager;
    RecyclerView CategoriesRecyclerView;
    ImageView allMusics;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        final  int home_bnv = R.id.home_bnv;
        final int library_bnv = R.id.library_bnv;
        final int search_bnv = R.id.search_bnv;


        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container_view , new home_Fragment())
                .commit();



        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {




                if (item.getItemId() == R.id.home_bnv)
                {

                    getSupportFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(R.anim.fade_in , R.anim.fade_out)
                            .replace(R.id.fragment_container_view , new home_Fragment())
                            .commit();

                }else if (item.getItemId() == R.id.search_bnv)
                    {

                        getSupportFragmentManager()
                                .beginTransaction()
                                .setCustomAnimations(R.anim.fade_in , R.anim.fade_out)
                                .replace(R.id.fragment_container_view , new search_Fragment())
                                .commit();

                    }else if (item.getItemId() == R.id.library_bnv)
                        {

                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .setCustomAnimations(R.anim.fade_in , R.anim.fade_out)
                                    .replace(R.id.fragment_container_view , new library_Fragment())
                                    .commit();

                        }else { Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show(); }

                return true;
            }
        });

//        allMusics = findViewById(R.id.allMusics);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.status_bar_color));


//        allMusics.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this , MusicLibrary.class);
//                startActivity(intent);
//                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
//
//            }
//        });


//        initialize();

//        ArrayList<Struct_Categories> dataItems = new ArrayList<>() ;
//        dataItems.add(new Struct_Categories(R.drawable.darlinginthefranxx , "Hindi"));
//        dataItems.add(new Struct_Categories(R.drawable.journeyofelaina , "English"));
//        dataItems.add(new Struct_Categories(R.drawable.deathnote , "Lofi"));
//        dataItems.add(new Struct_Categories(R.drawable.demonslayer , "Fun"));
//        dataItems.add(new Struct_Categories(R.drawable.darlinginthefranxx , "Hindi"));
//        dataItems.add(new Struct_Categories(R.drawable.journeyofelaina , "English"));
//        dataItems.add(new Struct_Categories(R.drawable.deathnote , "Lofi"));
//        dataItems.add(new Struct_Categories(R.drawable.demonslayer , "Fun"));
//        CategoriesAdapter adapter = new CategoriesAdapter(MainActivity.this , dataItems);
//
//
//
//
//        layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);
//        CategoriesRecyclerView.setLayoutManager(layoutManager);
//
//
//
//
//        CategoriesRecyclerView.setAdapter(adapter);
//
//
//        RecyclerView PlaylistRecyclerView = findViewById(R.id.playlistRecyclerView);
//
//        ArrayList<Struct_Playlist> playlistdataItems = new ArrayList<>();
//        playlistdataItems.add(new Struct_Playlist(R.drawable.demonslayer , "Liked songs" , "9 songs"));
//        playlistdataItems.add(new Struct_Playlist(R.drawable.deathnote , "Lover" , "10 songs"));
//        playlistdataItems.add(new Struct_Playlist(R.drawable.journeyofelaina , "Anime songs" , "21 songs"));
//        playlistdataItems.add(new Struct_Playlist(R.drawable.darlinginthefranxx , "Romantic songs" , "7 songs"));
//        playlistdataItems.add(new Struct_Playlist(R.drawable.demonslayer , "Liked songs" , "9 songs"));
//        playlistdataItems.add(new Struct_Playlist(R.drawable.deathnote , "Lover" , "10 songs"));
//        playlistdataItems.add(new Struct_Playlist(R.drawable.journeyofelaina , "Anime songs" , "21 songs"));
//        playlistdataItems.add(new Struct_Playlist(R.drawable.darlinginthefranxx , "Romantic songs" , "7 songs"));
//
//        PlaylistAdapter playlistAdapter = new PlaylistAdapter(MainActivity.this  , playlistdataItems);
//
//
//        PlaylistRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        PlaylistRecyclerView.setAdapter(playlistAdapter);
//
//
//
//
//        switchView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//
//                    layoutManager = new GridLayoutManager(MainActivity.this, 2, GridLayoutManager.VERTICAL, false);
//                    CategoriesRecyclerView.setLayoutManager(layoutManager);
//
//
//                } else {
//
//                    layoutManager = new GridLayoutManager(MainActivity.this, 2, GridLayoutManager.HORIZONTAL, false);
//                    CategoriesRecyclerView.setLayoutManager(layoutManager);
//                }
//            }
//        });
//
//
//
//
//
//


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