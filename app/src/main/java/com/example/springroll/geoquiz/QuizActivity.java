package com.example.springroll.geoquiz;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton, mFalseButon, mNextbutton;
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

        update();
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
