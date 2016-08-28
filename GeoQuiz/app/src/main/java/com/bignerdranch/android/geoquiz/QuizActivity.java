package com.bignerdranch.android.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private TextView mQuestionTextView;
    private ImageView mNextButton;
    private ImageView mPrevButton;
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, true),
            new Question(R.string.question_africa, true),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia,true),
    };

    private int mCurrentIndex = 0;

    private void updateQuestion()
    {
        final int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "OnCreate bundle called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        setTitle(R.string.app_name);

        if(savedInstanceState != null)
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mNextButton = (ImageView) findViewById(R.id.next_button);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mPrevButton = (ImageView) findViewById(R.id.prev_button);

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex - 1);
                if(mCurrentIndex < 0) mCurrentIndex = mQuestionBank.length - 1;
                updateQuestion();
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex +1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex +1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        updateQuestion();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onsaveinstancestate");
        outState.putInt(KEY_INDEX, mCurrentIndex);

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy called");
    }

    private void checkAnswer(boolean userPressedTrue)
    {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        if(userPressedTrue == answerIsTrue)
        {
            messageResId = R.string.correct_toast;
        }
        else
            {
                messageResId = R.string.incorrect_toast;
            }

        Toast.makeText(this, messageResId,Toast.LENGTH_LONG).show();
    }
}
