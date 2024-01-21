package com.example.housingsociety.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.housingsociety.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Admin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Admin extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Admin() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Admin.
     */
    // TODO: Rename and change types and number of parameters
    public static Admin newInstance(String param1, String param2) {
        Admin fragment = new Admin();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    EditText ed1, ed2, ed3;
    ImageView img;
    RadioGroup radioGroup, radioButton1, radioButton2;
    private final int GALLERY_REQ_CODE = 1000;
    Button btn_upload;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_admin, container, false);
        ed1 = view.findViewById(R.id.name);
        ed2 = view.findViewById(R.id.email);
        ed3 = view.findViewById(R.id.password);
        img = view.findViewById(R.id.ImageView);
        btn_upload = view.findViewById(R.id.btn_upload);
        radioGroup = view.findViewById(R.id.radioGroup);
        radioButton1 = view.findViewById(R.id.radioButton1);
        radioButton2 = view.findViewById(R.id.radioButton2);
        // RadioGroup
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                String gender = "";
//                // checkedId is the RadioButton ID that is selected
//                switch (checkedId) {
//                    case R.id.radioButton1:
//                        gender = "Male";
//                        // Handle option 1 selected
//                        break;
//                    case R.id.radioButton2:
//                        // Handle option 2 selected
//                        gender = "Female";
//                        break;
//                    // Add more cases if needed
//                }
//            }
//        });

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference();
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, GALLERY_REQ_CODE);
            }
        });



        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==Activity.RESULT_OK){
            if(requestCode==GALLERY_REQ_CODE){
                // for gallery
                img.setImageURI(data.getData());
            }

        }
    }
}