package com.example.evan.maps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

import java.util.Random;

/**
 * Created by Evan on 8/13/15.
 */
public class Flipper extends Activity implements View.OnClickListener {

    ViewFlipper flippy;
    Button button;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send);
        flippy = (ViewFlipper) findViewById(R.id.viewFlipper);
        flippy.setFlipInterval(100);
        flippy.startFlipping();
        count = 0;
        button = (Button) findViewById(R.id.VFB);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Random randomnum = new Random();
        count++;
      /*  if((count % 2) == 1) {
            flippy.stopFlipping();
        }else {
            flippy.startFlipping();
        }
        */
        try{
            Class rclass= Class.forName("com.example.evan.maps.FlipperResult");
            Intent ourintent = new Intent(Flipper.this, rclass);
            //pass a number tp flipperresult
            ourintent.putExtra("result",randomnum.nextInt(2));
            startActivity(ourintent);
        }catch(ClassNotFoundException e){
            e.printStackTrace();

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
