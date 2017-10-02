package edu.kvcc.cis298.cis298inclass1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private RadioGroup mQuestionGroup;
    private RadioButton mChoice1;
    private RadioButton mChoice2;
    private RadioButton mChoice3;
    private RadioButton mChoice4;

    private Button mSubmitButton;
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
            //First parameter is the string that is the question text

            //Second parameter is the id that is the correct answer for the question
            //This id is the id field assigned to the radio button widget that will
            //represent the correct answer.
            //If RadioButton2 is the correct answer, I need to assign the id for
            //RadioButton2. It wil not have anything to do with the questions itself.

            //Third parameter is an int array holding the possible answers to the questions
            new Question(R.string.question_1_multiple, R.id.multiple_choice_3,
                    new int[] {R.string.question_1_choice_1, R.string.question_1_choice_2,
                                R.string.question_1_choice_3, R.string.question_1_choice_4}),

            new Question(R.string.question_2_multiple, R.id.multiple_choice_2,
                    new int[] {R.string.question_2_choice_1, R.string.question_2_choice_2,
                            R.string.question_2_choice_3, R.string.question_2_choice_4}),
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

        //Fetch the question choice string from the question object
        int[] choices = mQuestionBank[mCurrentIndex].getChoiceResIds();

        //Assign each question choice text to the text property of the radio button
        mChoice1.setText(choices[0]);
        mChoice2.setText(choices[1]);
        mChoice3.setText(choices[2]);
        mChoice4.setText(choices[3]);
    }

    private void checkAnswer(int selectedRadioButtonId) {
        //Pull the answer from the current question
        int correctAnswer = mQuestionBank[mCurrentIndex].getCorrectAnswerResId();

        //Declare an int to hold the string resource id from the answer
        int messageResId = 0;

        //If the questions resId and correctAnswer resId are equal
        //They got it right. Correct answers will be when both variables
        //are the same. If they are different it is wrong
        //Set the messageResId once we determine what to set it to
        if(selectedRadioButtonId == correctAnswer) {
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

        //Get out the radio button information from the view
        mQuestionGroup = (RadioGroup) findViewById(R.id.multiple_group);

        mChoice1 = (RadioButton) findViewById(R.id.multiple_choice_1);
        mChoice2 = (RadioButton) findViewById(R.id.multiple_choice_2);
        mChoice3 = (RadioButton) findViewById(R.id.multiple_choice_3);
        mChoice4 = (RadioButton) findViewById(R.id.multiple_choice_4);

        updateQuestion();


        //Use R to reach into the view layout and pull out a
        //ref to the button we want to use in code.
        //We will get access to the button in the view by using
        //the id property that we declared on the layout.
        mSubmitButton = (Button) findViewById(R.id.submit_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Query the radio button group to fin out which radio
                //button was selected. Store the if od the selected one in
                //the variable selectedAnswerId
                //This will fetch the id of the RadioButton that was selected.
                //It will not return any string resource ids about the question
                //It is operating on the RadioButton widget, and this returns the
                //id of the widget control.
                int selectedAnswerId = mQuestionGroup.getCheckedRadioButtonId();

                //Call checkAnswer sending in the selectedAnswerId
                checkAnswer(selectedAnswerId);
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
