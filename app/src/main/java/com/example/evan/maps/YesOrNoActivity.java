package com.example.evan.maps;

import android.app.Activity;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;

public class YesOrNoActivity extends Activity {

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

        editText = (EditText) findViewById(R.id.edittext_yes_or_no);
        imageView = (ImageView) findViewById(R.id.imageview_yes_or_no);
        textView = (TextView) findViewById(R.id.textview_yes_or_no_thinking);
        textView_warning = (TextView) findViewById(R.id.textview_warning);

        textView.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.INVISIBLE);
        //TODO Dialog Pop Up forcing user to enter question

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_yes_or_no, menu);
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
}
