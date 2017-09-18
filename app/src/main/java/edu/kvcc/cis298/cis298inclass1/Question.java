package edu.kvcc.cis298.cis298inclass1;

/**
 * Created by cisco on 9/18/2017.
 */

public class Question {

    //This is an int because it will reference the memory address
    //in the R.java file that points to the string resource in strings.xml
    //for the text of this particular question
    private int mTextResId;
    private boolean mAnswerTrue;

    public Question(int textResId, boolean answerIsTrue)  {
        mTextResId = textResId;
        mAnswerTrue = answerIsTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
