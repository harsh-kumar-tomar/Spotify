package com.example.javaappversion19.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.javaappversion19.Activities.settings_Activity;
import com.example.javaappversion19.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class home_Fragment extends Fragment {

    public home_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_, container, false);

        EditText search = view.findViewById(R.id.search);
        ImageView user_image = view.findViewById(R.id.user_image);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getContext());


        assert acct != null;
        Glide.with(this)
                .load(acct.getPhotoUrl())
                .into(user_image) ;

        user_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext() , settings_Activity.class));
            }
        });






        return view;
    }
}