package com.example.javaappversion19.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.javaappversion19.Adapters.MusicLibraryAdapter;
import com.example.javaappversion19.Models.Struct_Music_Library;
import com.example.javaappversion19.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class search_Fragment extends Fragment {

    RecyclerView musicLibraryRecylerView ;
    DatabaseReference databaseReference;
    ArrayList<Struct_Music_Library> dataList;
    ImageView backButton;
    MusicLibraryAdapter adapter ;


    public search_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_search_, container, false);

        musicLibraryRecylerView = view.findViewById(R.id.MusicLibrary);
        dataList = new ArrayList<>();

        fetchData();

        adapter = new MusicLibraryAdapter(getContext() , dataList);

        musicLibraryRecylerView.setLayoutManager(new LinearLayoutManager(getContext()));
        musicLibraryRecylerView.setAdapter(adapter);





        return view;
    }

    private void fetchData() {

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Music List");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot music : snapshot.getChildren()) {

                    String name = String.valueOf(music.child("Name").getValue());
                    String url = String.valueOf(music.child("url").getValue());
                    String imgurl = String.valueOf(music.child("imgurl").getValue());
                    String singer = String.valueOf(music.child("singer").getValue());


                    dataList.add(new Struct_Music_Library(name, url , imgurl , singer));
                    adapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        databaseReference.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//                for (DataSnapshot music : snapshot.getChildren()) {
//
//                    String name = String.valueOf(music.child("Name").getValue());
//                    String url = String.valueOf(music.child("url").getValue());
//                    String imgurl = String.valueOf(music.child("imgurl").getValue());
//                    String singer = String.valueOf(music.child("singer").getValue());
//
//
//                    dataList.add(new Struct_Music_Library(name, url , imgurl , singer));
//                    adapter.notifyDataSetChanged();
//
//                }
//
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//                for (DataSnapshot music : snapshot.getChildren()) {
//
//                    String name = String.valueOf(music.child("Name").getValue());
//                    String url = String.valueOf(music.child("url").getValue());
//                    String imgurl = String.valueOf(music.child("imgurl").getValue());
//                    String singer = String.valueOf(music.child("singer").getValue());
//
//
//                    dataList.add(new Struct_Music_Library(name, url , imgurl , singer));
//                    adapter.notifyDataSetChanged();
//
//                }
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

    }

}