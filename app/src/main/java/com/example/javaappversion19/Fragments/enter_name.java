package com.example.javaappversion19.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.javaappversion19.Activities.handleFragmentsInterface;
import com.example.javaappversion19.R;


public class enter_name extends Fragment {

    handleFragmentsInterface helperInterface ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enter_name, container, false);

        AppCompatButton create_acc_btn = view.findViewById(R.id.create_acc_btn);

        create_acc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                helperInterface.onButtonClicked(4);

            }
        });

        return view;
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof handleFragmentsInterface) {
            helperInterface = (handleFragmentsInterface) context;
        }
    }
}
