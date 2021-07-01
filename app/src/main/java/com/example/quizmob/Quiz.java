package com.example.quizmob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizmob.databinding.ActivityQuizBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Random;

public class Quiz extends AppCompatActivity {
    ActivityQuizBinding binding;
    ArrayList<Questions> questions;
    int index =0;
    CountDownTimer timer;
    Questions question;
    int correctAnswers = 0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        questions=new ArrayList<>();
        questions.add(new Questions("what is earth?","sun","moon","strat","human","human" ));
        questions.add(new Questions("what is moon?","sun","moon","strat","human","moon" ));
        questions.add(new Questions("what is sun?","sun","moon","strat","human","sun" ));
        questions.add(new Questions("what is star?","sun","moon","strat","human","strat" ));
        questions.add(new Questions("what is samosa?","sun","moon","strat","human","human" ));


        resetTimer();
        setNextQuestion();
    }
    void resetTimer() {
        timer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                binding.timer.setText(String.valueOf(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {

            }
        };
    }
    void showAnswer() {
        if(question.getAnswer().equals(binding.option1.getText().toString()))
            binding.option1.setBackground(getResources().getDrawable(R.drawable.option_right));
        else if(question.getAnswer().equals(binding.option2.getText().toString()))
            binding.option2.setBackground(getResources().getDrawable(R.drawable.option_right));
        else if(question.getAnswer().equals(binding.option3.getText().toString()))
            binding.option3.setBackground(getResources().getDrawable(R.drawable.option_right));
        else if(question.getAnswer().equals(binding.option4.getText().toString()))
            binding.option4.setBackground(getResources().getDrawable(R.drawable.option_right));
    }

    void setNextQuestion() {
        if(timer != null)
            timer.cancel();

        timer.start();

        if(index < questions.size()) {
            binding.quecount.setText(String.format("%d/%d",(index+1),questions.size()));

            question =questions.get(index);
            binding.question.setText(question.getQuestion());
            binding.option1.setText(question.getOption1());
            binding.option2.setText(question.getOption2());
            binding.option3.setText(question.getOption3());
            binding.option4.setText(question.getOption4());
        }
    }
    void checkAnswer(TextView textView) {
        String selectedAnswer = textView.getText().toString();
        if(selectedAnswer.equals(question.getAnswer())) {
            correctAnswers++;
            textView.setBackground(getResources().getDrawable(R.drawable.option_right));

        } else {
            showAnswer();
            textView.setBackground(getResources().getDrawable(R.drawable.option_wrong));
        }
    }
    void reset() {
        binding.option1.setBackground(getResources().getDrawable(R.drawable.option_unselected));
        binding.option2.setBackground(getResources().getDrawable(R.drawable.option_unselected));
        binding.option3.setBackground(getResources().getDrawable(R.drawable.option_unselected));
        binding.option4.setBackground(getResources().getDrawable(R.drawable.option_unselected));
    }
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.option1:
            case R.id.option2:
            case R.id.option3:
            case R.id.option4:
                if(timer!=null)
                    timer.cancel();
                TextView selected = (TextView) view;
                checkAnswer(selected);
                break;
            case  R.id.nextbtn:
                reset();
                if(index <= questions.size()) {
                    index++;
                    setNextQuestion();
                } else {
                    Intent intent = new Intent(Quiz.this, ResultActivity.class);
                    intent.putExtra("correct", correctAnswers);
                    intent.putExtra("total", questions.size());
                    startActivity(intent);

                }
                break;

        }
    }


}