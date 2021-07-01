package com.example.quizmob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.quizmob.databinding.ActivityResultBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class ResultActivity extends AppCompatActivity {
    ActivityResultBinding binding;
    int POINTS = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        binding = ActivityResultBinding.inflate(getLayoutInflater());

        int correctAnswers = getIntent().getIntExtra("correct", 0);
        int totalQuestions = getIntent().getIntExtra("total", 0);

        long points = correctAnswers * POINTS;


        binding.score.setText(String.format("%d/%d", correctAnswers, totalQuestions));
        binding.earncoin.setText(String.valueOf(points));

        FirebaseFirestore database = FirebaseFirestore.getInstance();

        database.collection("users")
                .document(FirebaseAuth.getInstance().getUid())
                .update("coins", FieldValue.increment(points));

        binding.restartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
                finishAffinity();
            }
        });

    }
}