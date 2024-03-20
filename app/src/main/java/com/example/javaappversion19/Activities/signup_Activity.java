package com.example.javaappversion19.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.javaappversion19.Fragments.enter_email;
import com.example.javaappversion19.Fragments.enter_gender;
import com.example.javaappversion19.Fragments.enter_name;
import com.example.javaappversion19.Fragments.enter_password;
import com.example.javaappversion19.Fragments.home_Fragment;
import com.example.javaappversion19.R;

public class signup_Activity extends AppCompatActivity implements handleFragmentsInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        FragmentContainerView fragmentContainerView = findViewById(R.id.signup_fragmentContainerView);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.signup_fragmentContainerView , new enter_email())
                .commit();


    }

    @Override
    public void onButtonClicked(int fragmentId) {
        Fragment fragment = null;

        // Determine which fragment to open based on the provided fragmentId
        switch (fragmentId) {
            case 1:
                fragment = new enter_email();
                break;
            case 2:
                fragment = new enter_password();
                break;
            case 3:
                fragment = new enter_gender();
                break;
            case 4 :
                fragment = new enter_name();
                break;

            default:

                fragment = new enter_name();
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        }

        // Replace the current fragment with the determined fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.signup_fragmentContainerView, fragment)
                .setCustomAnimations(R.anim.fade_in , R.anim.fade_out)
                .addToBackStack(null)  // Optional: Add the transaction to the back stack
                .commit();
    }
}
