package com.bignerdranch.android.geoquiz;

/**
 * Created by Luis on 26/08/2016.
 */
public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean mIsCheat;

    public boolean isCheat() { return mIsCheat; }
    public void setCheat(boolean cheat) { mIsCheat = cheat; }
    public int getTextResId() {
        return mTextResId;
    }
    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }
    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public Question(int textResId, boolean answerTrue)
    {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
        mIsCheat = false;
    }
}
