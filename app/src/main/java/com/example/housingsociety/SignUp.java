package com.example.housingsociety;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.housingsociety.Model.FirebaseSignUpModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class SignUp extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    public final int GALLERY_REQ_CODE = 1000;
    EditText ed_username, ed_password, ed_email, ed_phone, ed_confirm_password;
    Button btn_signup;

   ImageView imageView;

    TextView txt1, headerUsername;

    Uri uri;
    ProgressBar progressBar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);
        ed_email = findViewById(R.id.email);
        ed_password = findViewById(R.id.password);
        ed_username = findViewById(R.id.username);
        ed_phone = findViewById(R.id.phone);
        ed_confirm_password = findViewById(R.id.confirm_password);
        imageView =findViewById(R.id.profile_image);
        btn_signup = findViewById(R.id.btn_upload);
        txt1 = findViewById(R.id.registerNow);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, GALLERY_REQ_CODE);
            }
        });

        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj = new Intent(SignUp.this, LoginPage.class);
                startActivity(obj);
            }
        });
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email = ed_email.getText().toString();
                String password = ed_password.getText().toString();
                String username = ed_username.getText().toString();
                String phone = ed_phone.getText().toString();
                String confirm_password = ed_confirm_password.getText().toString();

                if(username.isEmpty()) {
                    ed_username.requestFocus();
                    ed_username.setError("Please Enter Username!");
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else if (email.isEmpty()){
                    ed_email.requestFocus();
                    ed_email.setError("Please Enter Email!");
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else if (phone.isEmpty()) {
                    ed_phone.requestFocus();
                    ed_phone.setError("Please Enter Phone!");
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else if(password.isEmpty()){
                    ed_password.requestFocus();
                    ed_password.setError("Please enter Password!");
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else if(confirm_password.isEmpty())
                {
                    ed_confirm_password.requestFocus();
                    ed_confirm_password.setError("Please Enter Password to Confirm!");
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else if(!password.equals(confirm_password)){
                    ed_confirm_password.requestFocus();
                    ed_confirm_password.setError("Password do not match!");
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else{

                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            progressBar.setVisibility(View.INVISIBLE);
                            FirebaseCredentialStorage(username, email, phone, password);
                            Intent obj = new Intent(SignUp.this, LoginPage.class);
                            Toast.makeText(SignUp.this, "SignUpSuccessfull!", Toast.LENGTH_SHORT).show();
                            ed_username.setText("");
                            ed_password.setText("");
                            ed_email.setText("");
                            ed_phone.setText("");
                            ed_confirm_password.setText("");
                            imageView.setImageResource(R.drawable.default_profile_image);
                            startActivity(obj);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(SignUp.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }


        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQ_CODE) {
                uri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

        @Override
        public void onBackPressed () {
            finishAffinity();
        }

        private void FirebaseCredentialStorage (String username, String email, String phone, String
        password){
            storageReference = FirebaseStorage.getInstance().getReference("Images/");
            storageReference.child(email).putFile(uri);
            String uri_filepath = uri.toString();
            databaseReference = FirebaseDatabase.getInstance().getReference("SignUp");
            databaseReference.push().setValue(new FirebaseSignUpModel(username, email, phone, password, uri_filepath));


        }
    }




