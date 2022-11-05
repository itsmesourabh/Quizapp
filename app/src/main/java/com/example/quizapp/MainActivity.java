package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView totalQuestionsTextView;
    TextView questionsTextview;
    Button ansA, ansB, ansC,ansD;
    Button submit;

    int score=0;
    int totalQuestion = Questionandanswers.Question.length;
    int currentquestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionsTextView = findViewById(R.id.totalquestions);
        questionsTextview = findViewById(R.id.question);
        ansA = findViewById(R.id.ansA);
        ansB = findViewById(R.id.ansB);
        ansC = findViewById(R.id.ansC);
        ansD = findViewById(R.id.ansD);
        submit = findViewById(R.id.submitbutton);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submit.setOnClickListener(this);

        totalQuestionsTextView.setText("Total questions : "+totalQuestion);

        loadNewQuestion();  //new method
    }

    @Override
    public void onClick(View v) {
        //if 1 answer is clicked second also turn in that color so;
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) v;
        if(clickedButton.getId()==R.id.submitbutton){
            if (selectedAnswer.equals(Questionandanswers.correctanswers[currentquestionIndex])){
                score++; //if correct answer score will increased
            }
            currentquestionIndex++;
            loadNewQuestion();

        }else{
            //choices button clicked
            selectedAnswer = clickedButton.getText().toString();//whenever we select ans it will be selected answer
            clickedButton.setBackgroundColor(Color.MAGENTA);//it wil of these colour
        }
    }

    void loadNewQuestion(){

        if (currentquestionIndex == totalQuestion ){
            finishQuiz();
            return;
        }

        questionsTextview.setText(Questionandanswers.Question[currentquestionIndex]);
        ansA.setText(Questionandanswers.Choices[currentquestionIndex][0]);
        ansB.setText(Questionandanswers.Choices[currentquestionIndex][1]);
        ansC.setText(Questionandanswers.Choices[currentquestionIndex][2]);
        ansD.setText(Questionandanswers.Choices[currentquestionIndex][3]);
    }
    void finishQuiz(){
        String passStatus = "";
        if (score > totalQuestion*0.60){
            passStatus = "Passed";
        }else {
            passStatus = "failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+ score+" out of"+totalQuestion)
                .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz() )
                .setCancelable(false)
                .show();


    }
    void restartQuiz(){
        score = 0;
        currentquestionIndex=0;
        loadNewQuestion();


    }
}