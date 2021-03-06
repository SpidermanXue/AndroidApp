package com.example.evan.maps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class UserQuestionActivity extends Activity {

    Button yesOrNo;
    Button coinFlipper;
    Button spinWheel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_question);

        final Intent intent_yesOrNo = new Intent(this, YesOrNoActivity.class);
        final Intent intent_coinFlipper = new Intent(this, FlipperActivity.class);
       // final Intent intent_spinWheel = new Intent(this, SpinWheel.class);

        yesOrNo = (Button) findViewById(R.id.button_yes_or_no_oracle);
        coinFlipper = (Button) findViewById(R.id.button_the_coin_flipper);
        spinWheel = (Button) findViewById(R.id.button_spin_the_apple_pie);


        yesOrNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_yesOrNo);

            }
        });

        coinFlipper.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(intent_coinFlipper);
            }
        });


        spinWheel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try{
                    Class rclass= Class.forName("com.example.evan.maps.TakeChoice");
                    Intent ourintent = new Intent(UserQuestionActivity.this, rclass);
                  //  ourintent.putExtra("caller", "UserQuestionActivity");
                    //pass a number tp flipperresult
                    startActivity(ourintent);
                }catch(ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
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
                    Intent ourintent = new Intent(UserQuestionActivity.this, rclass);
                    startActivity(ourintent);
                }catch(ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return true;

            case R.id.presetquestion:
                try{
                    Class rclass= Class.forName("com.example.evan.maps.PresetQuestionActivity");
                    Intent ourintent = new Intent(UserQuestionActivity.this, rclass);
                    startActivity(ourintent);
                }catch(ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return true;
            case R.id.homepage:
                try{
                    Class rclass= Class.forName("com.example.evan.maps.HomePageActivity");
                    Intent ourintent = new Intent(UserQuestionActivity.this, rclass);
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
