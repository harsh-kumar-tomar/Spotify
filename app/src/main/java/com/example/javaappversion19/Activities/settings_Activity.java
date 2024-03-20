package com.example.javaappversion19.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.javaappversion19.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class settings_Activity extends AppCompatActivity {

    TextView user_name , user_password , user_gender , user_email ;
    ImageView user_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        init();

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());


        assert acct != null;
        Glide.with(this)
                .load(acct.getPhotoUrl())
                .into(user_image) ;

        user_email.setText(acct.getEmail());
        user_name.setText(acct.getId());




    }

    public void init()
    {
        user_name = findViewById(R.id.user_name);
        user_email = findViewById(R.id.user_email);
        user_gender = findViewById(R.id.user_gender);
        user_password = findViewById(R.id.user_password);
        user_image = findViewById(R.id.user_image);
    }

}