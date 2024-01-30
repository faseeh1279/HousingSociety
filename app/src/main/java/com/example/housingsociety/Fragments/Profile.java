package com.example.housingsociety.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.housingsociety.Model.FirebaseSignUpModel;
import com.example.housingsociety.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class Profile extends Fragment {


    public Profile() {
        // Required empty public constructor
    }


    EditText ed_username, ed_phone, ed_email, ed_password;
    ImageView imageView;
    TextView txt_username;
    ProgressBar progressBar;
    DatabaseReference databaseReference;
    Button btn_upload;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FirebaseApp.initializeApp(requireContext());
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ed_username = view.findViewById(R.id.username);
        ed_email = view.findViewById(R.id.email);
        ed_phone = view.findViewById(R.id.phone);
        ed_password = view.findViewById(R.id.password);
        imageView = view.findViewById(R.id.profile_image);
        txt_username = view.findViewById(R.id.title_username);
        progressBar = view.findViewById(R.id.progressbar);
        btn_upload = view.findViewById(R.id.btn_upload);
        progressBar.setVisibility(View.INVISIBLE);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        // Fetching the Data






        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        progressBar.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }, 5000);
            }
        });

        return view;
    }


}