package com.example.quizmob;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.quizmob.databinding.ActivityLoginactivityBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginactivity extends AppCompatActivity {

    ActivityLoginactivityBinding binding;

    FirebaseAuth auth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginactivityBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);
        dialog.setMessage("logging in..");

        if(auth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, pass;
                email = binding.emailbox.getText().toString();
                pass = binding.pwdbox.getText().toString();
                dialog.show();

                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        dialog.dismiss();
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();

                        } else {
                            Toast.makeText(loginactivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), registration.class));

            }
        });




    }

}