package com.example.brainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button playButton;
    ConstraintLayout constraintLayout;
    Button optionButton1;
    Button optionButton2;
    Button optionButton3;
    Button optionButton4;
    Button playAgainButton;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    TextView result;
    TextView scoreView;
    TextView sumTextView;
    TextView timerTextView;
    int locationOfCorrectAnswer;
    int score;
    int totalQues;


    public void chooseAnswer(View view){
        result.setVisibility(View.VISIBLE);
        if(Integer.toString(locationOfCorrectAnswer+1).equals(view.getTag().toString())){
            result.setText("CORRECT :)");
            score++;
        }
        else
        {
            result.setText("INCORRECT :(");
        }
        totalQues++;
        scoreView.setText(Integer.toString(score)+"/"+Integer.toString(totalQues));
        newQuestions();
    }
   public void play(View view){
    playButton.setVisibility(View.INVISIBLE);
    constraintLayout=findViewById(R.id.constraintLayout2);
       playAgain(playAgainButton);
    constraintLayout.setVisibility(View.VISIBLE);
   }

   public void newQuestions(){
       Random rand=new Random();
       int a=rand.nextInt(41);
       int b=rand.nextInt(41);

       sumTextView.setText(Integer.toString(a)+"+"+Integer.toString(b));
       locationOfCorrectAnswer=rand.nextInt(4);
        answers.clear();

       for(int i=0;i<4;i++)
       {
           if(i==locationOfCorrectAnswer)
               answers.add(a+b);
           else
           {
               int value=rand.nextInt(100);
               while(value==(a+b))
                   value=rand.nextInt(100);
               answers.add(value);
           }
       }
       optionButton1.setText(Integer.toString(answers.get(0)));
       optionButton2.setText(Integer.toString(answers.get(1)));
       optionButton3.setText(Integer.toString(answers.get(2)));
       optionButton4.setText(Integer.toString(answers.get(3)));
   }

   public void playAgain(View view){
        score=0;
        totalQues=0;
       scoreView.setText(Integer.toString(score)+"/"+Integer.toString(totalQues));
        timerTextView.setText("30s");
       optionButton1.setEnabled(true);
       optionButton2.setEnabled(true);
       optionButton3.setEnabled(true);
       optionButton4.setEnabled(true);
       newQuestions();
        playAgainButton.setVisibility(View.INVISIBLE);
       new CountDownTimer(30100,1000){
           @Override
           public void onTick(long l) {
               timerTextView.setText(String.valueOf(l/1000)+"s");
           }

           @Override
           public void onFinish() {
               result.setText("Done!");
               playAgainButton.setVisibility(View.VISIBLE);
               optionButton1.setEnabled(false);
               optionButton2.setEnabled(false);
               optionButton3.setEnabled(false);
               optionButton4.setEnabled(false);

           }
   }.start();
   }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton=findViewById(R.id.playButton);
        optionButton1=findViewById(R.id.optionButton1);
        optionButton2=findViewById(R.id.optionButton2);
        optionButton3=findViewById(R.id.optionButton3);
        optionButton4=findViewById(R.id.optionButton4);
        sumTextView=findViewById(R.id.sumTextView);
        scoreView=findViewById(R.id.scoreTextView);
        result=findViewById(R.id.resultTextView);
        timerTextView=findViewById(R.id.timerTextView);
        playAgainButton=findViewById(R.id.playAgainButton);
        result.setVisibility(View.INVISIBLE);

        newQuestions();

       }
}