package com.example.evan.maps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HomePageActivity extends Activity {

    Button userQuestion;
    Button presetQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        userQuestion = (Button) findViewById(R.id.button_user_question);
        presetQuestion = (Button) findViewById(R.id.button_preset_question);

        final Intent intent_user_question = new Intent(this, UserQuestionActivity.class);
        final Intent intent_preset_question = new Intent(this,PresetQuestionActivity.class );

        userQuestion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(intent_user_question);
            }
        });

        presetQuestion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(intent_preset_question);
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
                    Intent ourintent = new Intent(HomePageActivity.this, rclass);
                    startActivity(ourintent);
                }catch(ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return true;

            case R.id.presetquestion:
                try{
                    Class rclass= Class.forName("com.example.evan.maps.PresetQuestionActivity");
                    Intent ourintent = new Intent(HomePageActivity.this, rclass);
                    startActivity(ourintent);
                }catch(ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return true;
            case R.id.homepage:
                try{
                    Class rclass= Class.forName("com.example.evan.maps.HomePageActivity");
                    Intent ourintent = new Intent(HomePageActivity.this, rclass);
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
