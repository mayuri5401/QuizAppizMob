package com.example.quizmob;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.quizmob.databinding.ActivityRegistrationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class registration extends AppCompatActivity {
    ActivityRegistrationBinding binding;
    FirebaseAuth auth;
    FirebaseFirestore database;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();
        dialog = new ProgressDialog(this);

        binding.gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), loginactivity.class));

            }
        });

        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, pwd, name, refercode;

                email = binding.emailbox.getText().toString();
                pwd = binding.pwdbox.getText().toString();
                name = binding.namebox.getText().toString();
                refercode = binding.referbox.getText().toString();


                final User user = new User(name, email, pwd, refercode);

                dialog.show();
                dialog.setMessage("please wait");


                auth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String uid = task.getResult().getUser().getUid();
                            database.collection("users")
                                    .document(uid)
                                    .set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        dialog.dismiss();
                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                        finish();


                                    } else {
                                        Toast.makeText(registration.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });


                        } else {
                            dialog.dismiss();
                            Toast.makeText(registration.this, Objects.requireNonNull(task.getException()).getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });


    }

}