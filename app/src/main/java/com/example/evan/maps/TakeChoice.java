package com.example.evan.maps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Evan on 8/23/15.
 */
public class TakeChoice extends Activity implements View.OnClickListener, View.OnKeyListener {
    private Button choice;
    EditText choice1, choice2, choice3, choice4, choice5, choice6, choice7,choice8;
    String option1, option2, option3, option4, option5, option6, option7, option8;
    int count, getnum;
    List<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.takechoice);
        initializeVars();

        choice1.setOnKeyListener(this);
        choice2.setOnKeyListener(this);
        choice3.setOnKeyListener(this);
        choice4.setOnKeyListener(this);
        choice5.setOnKeyListener(this);
        choice6.setOnKeyListener(this);
        choice7.setOnKeyListener(this);
        choice8.setOnKeyListener(this);

        choice = (Button) findViewById(R.id.buttonchoice);
        choice.setOnClickListener(this);
    }

    private void initializeVars() {
        // TODO Auto-generated method stub
        choice1 = (EditText) findViewById(R.id.editText1);
        choice2 = (EditText) findViewById(R.id.editText2);
        choice3 = (EditText) findViewById(R.id.editText3);
        choice4 = (EditText) findViewById(R.id.editText4);
        choice5 = (EditText) findViewById(R.id.editText5);
        choice6 = (EditText) findViewById(R.id.editText6);
        choice7 = (EditText) findViewById(R.id.editText7);
        choice8 = (EditText) findViewById(R.id.editText8);
        choice = (Button) findViewById(R.id.buttonchoice);
    }

    private void convertEditTextVarsIntoStringsAndYesThisIsAMethodWeCreated() {
        // TODO Auto-generated method stub
        option1 = choice1.getText().toString();
        option2 = choice2.getText().toString();
        option3 = choice3.getText().toString();
        option4 = choice4.getText().toString();
        option5 = choice5.getText().toString();
        option6 = choice6.getText().toString();
        option7 = choice7.getText().toString();
        option8 = choice8.getText().toString();
    }

    private void setcounter(){
        list.clear();
        if(!(option1.matches(""))){
            count++;
            list.add(option1);
        }
        if(!(option2.matches(""))) {
            count++;
            list.add(option2);
        }
        if(!(option3.matches(""))){
            count++;
            list.add(option3);
        }
        if(!(option4.matches(""))){
            count++;
            list.add(option4);
        }
        if(!(option5.matches(""))){
            count++;
            list.add((option5));
        }
        if(!(option6.matches(""))){
            count++;
            list.add(option6);
        }
        if(!(option7.matches(""))){
            count++;
            list.add(option7);
        }
        if(!(option8.matches(""))){
            count++;
            list.add(option8);
        }
    }
    @Override
    public void onClick(View v) {
        count = 0;
        convertEditTextVarsIntoStringsAndYesThisIsAMethodWeCreated();
        setcounter();
        if (count >= 2){
            try {
                Class rclass = Class.forName("com.example.evan.maps.SpinWheel");
                Intent ourintent = new Intent(TakeChoice.this, rclass);
                ourintent.putExtra("caller", "TakeChoice");
                ourintent.putStringArrayListExtra("option", (ArrayList<String>) list);
                startActivity(ourintent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
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
                    Intent ourintent = new Intent(TakeChoice.this, rclass);
                    startActivity(ourintent);
                }catch(ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return true;

            case R.id.presetquestion:
                try{
                    Class rclass= Class.forName("com.example.evan.maps.PresetQuestionActivity");
                    Intent ourintent = new Intent(TakeChoice.this, rclass);
                    startActivity(ourintent);
                }catch(ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return true;
            case R.id.homepage:
                try{
                    Class rclass= Class.forName("com.example.evan.maps.HomePageActivity");
                    Intent ourintent = new Intent(TakeChoice.this, rclass);
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
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        //filter for second call
        if (event.getAction()!=KeyEvent.ACTION_DOWN)
            return true;
        if(keyCode == 66) {
            int id = v.getId();
            switch(id){
                case R.id.editText1:
                    //imm.hideSoftInputFromWindow(choice1.getWindowToken(), 0);
                    choice2.requestFocus();
                    break;
                case R.id.editText2:
                    choice3.requestFocus();
                    break;
                case R.id.editText3:
                    choice4.requestFocus();
                    break;
                case R.id.editText4:
                    choice5.requestFocus();
                    break;
                case R.id.editText5:
                    choice6.requestFocus();
                    break;
                case R.id.editText6:
                    choice7.requestFocus();
                    break;
                case R.id.editText7:
                    choice8.requestFocus();
                    break;
                case R.id.editText8:
                    choice.performClick();
                    break;
            }

        }
        return false;
    }

}
