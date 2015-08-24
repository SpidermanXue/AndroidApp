package com.example.evan.maps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.Button;
/**
 * Created by Evan on 8/13/15.
 */

public class FlipperResult extends Activity implements View.OnClickListener{

   // int result = extras.getInt("result");
    ViewFlipper flipper;
    Button backup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flipper);

        int result = getIntent().getExtras().getInt("result");
        TextView title = (TextView) findViewById(R.id.checkresult);
        title.setText(String.valueOf(result));
        flipper=(ViewFlipper) findViewById(R.id.viewFlipper);
        if(result == 1) {
            flipper.setDisplayedChild(0);
        }else{
            flipper.setDisplayedChild(2);
        }
        backup =(Button) findViewById(R.id.VFB);
        backup.setText("FLIP AGAIN");

        backup.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        try{
            Class rclass= Class.forName("com.example.evan.maps.FlipperActivity");
            Intent ourintent = new Intent(FlipperResult.this, rclass);
            startActivity(ourintent);
        }catch(ClassNotFoundException e){
            e.printStackTrace();

        }
    }

    @Override
    protected void onPause(){
        //when jump to flipper , onPause of splash is called
        //called after jump to myactivity class
        super.onPause();
        finish(); //kill the splash class.
    }



}
