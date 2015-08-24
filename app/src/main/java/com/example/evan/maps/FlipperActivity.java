package com.example.evan.maps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.Random;


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
}
