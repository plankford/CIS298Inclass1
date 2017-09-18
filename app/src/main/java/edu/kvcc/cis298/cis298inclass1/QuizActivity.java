package edu.kvcc.cis298.cis298inclass1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;

    //Array of questions. We send over the resource if from R.java
    //as the first parameter of the constructor
    //We will use this stored ResourceId (which references
    //a string in strings.xml) later.
    //If we were to have a string variable on the Questions model and
    //try to pass the string value. it would work, but it
    //goes against the convention of android development
    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia,true),
            new Question(R.string.question_oceans,true),
            new Question(R.string.questions_mideast,false),
            new Question(R.string.question_africa,false),
            new Question(R.string.question_americas,true),
            new Question(R.string.question_asia,true),
    };

    //Index of the current question we are on.
    private int mCurrentIndex = 0;

    //Method that will be used to update the questions
    private void updateQuestion() {
        //Get the questions from the array. This will be an integer since
        //we are fetching from an array
        int question = mQuestionBank[mCurrentIndex].getTextResId();

        //Set the text on the question text view to the string resource
        //located at the memory address stored in question
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedButton) {
        //Pull the answer from the current question
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        //Declare an int to hold the string resource id from the answer
        int messageResId = 0;
        //If the questions answer and user Pressing True are equal
        //They got it right. Correct answers will be when both variables
        //are the same. If they are different it is wrong
        //Set the messageResId once we determine what to set it to
        if(userPressedButton == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        //Make the toast
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //Get a reference to the textview that displays the questions
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        updateQuestion();


        //Use R to reach into the view layout and pull out a
        //ref to the button we want to use in code.
        //We will get access to the button in the view by using
        //the id property that we declared on the layout.
        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
    }




}
