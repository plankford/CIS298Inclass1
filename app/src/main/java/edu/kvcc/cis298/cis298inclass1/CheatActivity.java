package edu.kvcc.cis298.cis298inclass1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    //This key is used to store the data on the intent
    //that is created to get this activity started
    private static final String EXTRA_ANSWER_IS_TRUE =
            "edu.kvcc.cis298.cis298inclass1.answer_is_true";

    //This key is used to store the return data on the
    //return intent that is created to send data back
    //to the quiz activity
    private static final String EXTRA_ANSWER_SHOWN =
            "edu.kvcc.cis298.cis298inclass1.answer_shown";

    //Stores the answer to the question from the QuizActivity
    private boolean mAnswerIsTrue;

    private TextView mAnswerTextView;
    private Button mShowAnswerButton;

    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        //Start CheatActivity
        //Create a new intent to get the Activity started
        //intents are objects that hold all of the date needed

        //The intent constructor takes to params. The first is the
        //instance of the class that is initiating the new activity
        //the second is the name of the new activity to start.
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }

    //This method will be called by QuizActivity to check and see if a user
    //cheated or not. QuizActivity will send the intent that contains the result
    //data into this method, and this method will 'decode' the intent and return
    //a bool to let us know whether the person cheated or not.
    public static  boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        //Use the key defined at the top to get out the date from the Intent
        //that was used to get this activity started
        //getIntent is a method on the Activity class
        //There are multiple get????Extra methods for each type of extra
        //you want to pull out from the intent. here we are using boolean
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView) findViewById(R.id.answer_text_window);

        mShowAnswerButton = (Button) findViewById(R.id.show_answer_button);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                }else{
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
            }
        });
    }

    //Method to create and set the return date for this activity
    private void setAnswerShownResult(boolean isAnswerShown) {
        //Make a new intent that will be used to hold the return data.
        //It will NOT be used to start a new activity.
        //Intent now has double duty
        Intent data = new Intent();
        //Put an extra just like when we are creating an intent to start an activity
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        //Call Activities setResult method to attach intent as the return data
        //The first parameter is a CONST that says that everything finished okay here
        //There are other RESULT CONSTS for when other things happen
        setResult(RESULT_OK, data);
    }
}
