package com.example.evan.maps;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;
/**
 * Created by Evan on 8/10/15.
 */
public class TextPlay extends Activity implements View.OnClickListener{
    Button check;
    ToggleButton passtg;
    EditText input;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);

        getactions(); //import the action method;

        passtg.setOnClickListener(this);
        check.setOnClickListener(this);

    }

    private void getactions() {
        check = (Button) findViewById(R.id.bResult);
        passtg = (ToggleButton) findViewById(R.id.tbPassword);
        input = (EditText) findViewById(R.id.etCommand);
        display = (TextView) findViewById(R.id.tvResult); //final, can not change?
    }

    @Override
    public void onClick(View v) {
     switch(v.getId()){
         case R.id.tbPassword: //from togglebuttion
             if(passtg.isChecked()){ //WHEN CHECKED, password is hided by star
                 input.setInputType(InputType.TYPE_CLASS_TEXT |  InputType.TYPE_TEXT_VARIATION_PASSWORD);
             }else{//if off, show text
                 input.setInputType(InputType.TYPE_CLASS_TEXT);
             }

             break;
         case R.id.bResult: //from button

             String checkmess = input.getText().toString();
             if(checkmess.contentEquals("left")){
                 display.setText(checkmess);
                 display.setGravity(Gravity.LEFT);
             }else if(checkmess.equals("center")){
                 display.setText(checkmess);
                 display.setGravity(Gravity.CENTER);
             }else if(checkmess.equals("right")){
                 display.setText(checkmess);
                 display.setGravity(Gravity.RIGHT);
             }else if(checkmess.contains("SHABI")){ //contain shabi as sub string
                 Random crazy = new Random();
                 display.setText("WENXIN");
                 display.setTextSize(crazy.nextInt(75)); //from 0 to 74 choices
                 display.setTextColor(Color.rgb(crazy.nextInt(265), crazy.nextInt(265), crazy.nextInt(265)));
                 switch(crazy.nextInt(3)) {
                     case 0:
                         display.setGravity(Gravity.LEFT);
                         break;
                     case 1:
                         display.setGravity(Gravity.CENTER);
                         break;
                     case 2:
                         display.setGravity(Gravity.RIGHT);
                         break;
                 }

             }else{
                 display.setTextColor(Color.WHITE);
                 display.setText("hahahaha");
             }
             break;
     }
    }
}

