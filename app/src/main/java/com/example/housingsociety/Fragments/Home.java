package com.example.housingsociety.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.housingsociety.Adapter.FirebaseAdapter;
import com.example.housingsociety.Model.FirebaseModel;
import com.example.housingsociety.Model.SelectListener;
import com.example.housingsociety.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment implements SelectListener {


    public Home() {
        // Required empty public constructor
    }



    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    List<FirebaseModel> modelList = new ArrayList<>();
    FirebaseAdapter firebaseAdapter;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        progressBar = view.findViewById(R.id.ProgressBar);
        progressBar.setVisibility(View.VISIBLE);
        Toast.makeText(getContext(), "somethingnew", Toast.LENGTH_SHORT).show();
        recyclerView = view.findViewById(R.id.recyclerview);
        databaseReference = FirebaseDatabase.getInstance().getReference("Worker");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    FirebaseModel model = dataSnapshot.getValue(FirebaseModel.class);
                    modelList.add(model);
                }
                firebaseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        progressBar.setVisibility(View.INVISIBLE);


        firebaseAdapter = new FirebaseAdapter(getContext(), modelList, this::onItemClicked);
        recyclerView.setAdapter(firebaseAdapter);


        return view;
    }

    @Override
    public void onItemClicked(FirebaseModel firebaseModel) {




     }
}