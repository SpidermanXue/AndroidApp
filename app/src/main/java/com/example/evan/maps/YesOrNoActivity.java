package com.example.evan.maps;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class YesOrNoActivity extends Activity implements View.OnKeyListener{

    Button buttonenter;
    EditText editText;
    ImageView imageView_mirror_god;
    ImageView imageView;
    TextView textView;
    TextView textView_warning;
    CountDownTimer timer;
    Random rand = new Random();
    View curr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yes_or_no);
        curr = (View) findViewById(R.id.view_yes_or_no);

        buttonenter = (Button) findViewById(R.id.button_yes_or_no_enter);
        editText = (EditText) findViewById(R.id.edittext_yes_or_no);
        editText.setOnKeyListener(this);
        imageView = (ImageView) findViewById(R.id.imageview_yes_or_no);
        textView = (TextView) findViewById(R.id.textview_yes_or_no_thinking);
        textView_warning = (TextView) findViewById(R.id.textview_warning);

        textView.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.INVISIBLE);
        //TODO Dialog Pop Up forcing user to enter question

    }

    public void displayResult(View v){

        if(editText.getText().toString().equals("")){
            imageView.setImageResource(R.drawable.uglywitch);
            imageView.setVisibility(View.VISIBLE);
            textView_warning.setVisibility(View.VISIBLE);
            return;
        }

        textView_warning.setVisibility(View.INVISIBLE);
        timer = new CountDownTimer(2000, 200) {
            public void onTick(long millisUntilFinished) {
                thinking();
            }

            public void onFinish() {
                thinkingFinish();
            }
        };
        imageView.setVisibility(View.INVISIBLE);
        timer.start();
    }

    public void thinking(){
        textView.setVisibility(View.VISIBLE);
        textView.setText(textView.getText() + ".");
    }
    public void thinkingFinish(){

        int randomNum = rand.nextInt(9);

        textView.setVisibility(View.INVISIBLE);
        textView.setText("Thinking...");

        if(randomNum%2 ==0){
            imageView.setImageResource(R.drawable.no);
            imageView.setVisibility(View.VISIBLE);

        } else{
            imageView.setImageResource(R.drawable.yes);
            imageView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction()!=KeyEvent.ACTION_DOWN)
            return true;
        if(keyCode == 66){
            //hide the keypad
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            //perform onclick
            buttonenter.performClick();
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present. Inject xml to java code to nevigation bar
        getMenuInflater().inflate(R.menu.menu_user_question, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){
            case R.id.userquetion:
                try{
                    Class rclass= Class.forName("com.example.evan.maps.UserQuestionActivity");
                    Intent ourintent = new Intent(YesOrNoActivity.this, rclass);
                    startActivity(ourintent);
                }catch(ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return true;

            case R.id.presetquestion:
                try{
                    Class rclass= Class.forName("com.example.evan.maps.PresetQuestionActivity");
                    Intent ourintent = new Intent(YesOrNoActivity.this, rclass);
                    startActivity(ourintent);
                }catch(ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return true;
            case R.id.homepage:
                try{
                    Class rclass= Class.forName("com.example.evan.maps.HomePageActivity");
                    Intent ourintent = new Intent(YesOrNoActivity.this, rclass);
                    startActivity(ourintent);
                }catch(ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
