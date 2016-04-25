package com.example.evan.maps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;


public class FlipperActivity extends Activity implements View.OnClickListener {

    ViewFlipper flippy;
    Button button;
    TextView textView;
    String heads = "HEADS!";
    String tails = "TAILS!";
    String stopFlipping = "STOP FLIPPING";
    String flipAgain = "FLIP AGAIN";
    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flipper);

        flippy = (ViewFlipper) findViewById(R.id.viewFlipper);
        textView = (TextView) findViewById(R.id.checkresult);
        button = (Button) findViewById(R.id.VFB);

        flippy.setFlipInterval(100);
        button.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(flag==0){
            flippy.startFlipping();
            button.setText(stopFlipping);
            flag = 1;
        } else{
            flippy.stopFlipping();
            int index = flippy.getDisplayedChild();
            switch(index){
                case 0:
                    textView.setText(heads);
                    break;
                case 1:
                    flippy.showNext();
                    textView.setText(tails);
                    break;
                case 2:
                    textView.setText(tails);
                    break;
                case 3:
                    flippy.showNext();
                    textView.setText(heads);
                    break;
            }
            button.setText(flipAgain);
            flag=0;
        }
    }

    @Override
    protected void onPause(){
        //when jump to flipperresult , onPause of splash is called
        //called after jump to myactivity class
        super.onPause();
        finish(); //kill the splash class.
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
                    Intent ourintent = new Intent(FlipperActivity.this, rclass);
                    startActivity(ourintent);
                }catch(ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return true;

            case R.id.presetquestion:
                try{
                    Class rclass= Class.forName("com.example.evan.maps.PresetQuestionActivity");
                    Intent ourintent = new Intent(FlipperActivity.this, rclass);
                    startActivity(ourintent);
                }catch(ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return true;
            case R.id.homepage:
                try{
                    Class rclass= Class.forName("com.example.evan.maps.HomePageActivity");
                    Intent ourintent = new Intent(FlipperActivity.this, rclass);
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
