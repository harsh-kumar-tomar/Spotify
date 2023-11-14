package com.example.javaappversion19.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.javaappversion19.Adapters.MusicLibraryAdapter;
import com.example.javaappversion19.Models.Struct_Music_Library;
import com.example.javaappversion19.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MusicLibrary extends AppCompatActivity {
    RecyclerView musicLibraryRecylerView ;
    DatabaseReference databaseReference;
    ArrayList<Struct_Music_Library> dataList;
    ImageView backButton;
    MusicLibraryAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_library);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.status_bar_color));
        backButton = findViewById(R.id.backButtonLibrary);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

            }
        });


        dataList=new ArrayList<>();

        fetchData();

        musicLibraryRecylerView = findViewById(R.id.MusicLibrary);

        adapter = new MusicLibraryAdapter(MusicLibrary.this , dataList);

        musicLibraryRecylerView.setLayoutManager(new LinearLayoutManager(MusicLibrary.this));
        musicLibraryRecylerView.setAdapter(adapter);



    }
    @Override
    protected void onDestroy() {
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        super.onDestroy();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

    }

    @Override
    protected void onStop() {

        super.onStop();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

    }

    @Override
    protected void onPause() {

        super.onPause();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

    }

    private void fetchData() {

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Music List");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot musicListSnapshot : snapshot.getChildren()) {
//                    for (DataSnapshot musicSnapshot : musicListSnapshot.getChildren()) {
                        // Access the "Name" and "url" children
                        String name = String.valueOf(musicListSnapshot.child("Name").getValue());
                        String url = String.valueOf(musicListSnapshot.child("url").getValue());
                        String imgurl = String.valueOf(musicListSnapshot.child("imgurl").getValue());
                        String singer = String.valueOf(musicListSnapshot.child("singer").getValue());

//                        Log.e("","value"+name+url);

                        // Create a Struct_Music_Library object and add it to the list
                        dataList.add(new Struct_Music_Library(name, url , imgurl , singer));
//                    }
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}