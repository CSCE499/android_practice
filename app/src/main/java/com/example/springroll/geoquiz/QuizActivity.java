package com.example.springroll.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private static final String KEY_INDEX = "index";
    private static final String TAG = "QuizActivity";
    private Button mTrueButton, mFalseButon, mNextbutton, mCheatbutton;
    private TextView mTextView;
    private int mCurrent = 0;

    private TrueFalse[] mBank = new TrueFalse[]{
            new TrueFalse(R.string.question_1,true),
            new TrueFalse(R.string.question_2,false)
    };

    private void update(){
        int question = mBank[mCurrent].getQuestion();
        mTextView.setText(question);
    }

    private void checkAnswer(boolean trueButton){
        boolean go = mBank[mCurrent].isTrueQuestion();
        int msgID = 0;
        if(trueButton == go)
            msgID = R.string.correct_toast;
        else
            msgID = R.string.no_toast;

        Toast.makeText(this,msgID,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.activity_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTextView = (TextView)findViewById(R.id.question_text_view);

        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButon = (Button)findViewById(R.id.false_button);
        mFalseButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextbutton = (Button)findViewById(R.id.next_button);
        mNextbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mCurrent = (mCurrent +1)% mBank.length;
                update();
            }
        });

        mCheatbutton = (Button)findViewById(R.id.cheat_Button);
        mCheatbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(QuizActivity.this, CheatActivity.class);
                boolean answerIsTrue = mBank[mCurrent].isTrueQuestion();
                i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE,answerIsTrue);
                startActivity(i);
            }
        });

        if (savedInstanceState != null){
            mCurrent = savedInstanceState.getInt(KEY_INDEX,0);
        }
        update();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
