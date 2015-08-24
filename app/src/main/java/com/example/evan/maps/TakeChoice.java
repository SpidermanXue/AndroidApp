package com.example.evan.maps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.List;
import java.util.ArrayList;
/**
 * Created by Evan on 8/23/15.
 */
public class TakeChoice extends Activity implements View.OnClickListener{
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
        try{
            Class rclass= Class.forName("com.example.evan.maps.SpinWheel");
            Intent ourintent = new Intent(TakeChoice.this, rclass);
            ourintent.putExtra("caller", "TakeChoice");
            ourintent.putStringArrayListExtra("option", (ArrayList<String>) list);
            //pass a number tp flipperresult
            startActivity(ourintent);
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
