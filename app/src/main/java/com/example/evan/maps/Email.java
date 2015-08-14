package com.example.evan.maps;

/**
 * Created by Evan on 8/11/15.
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class Email extends Activity implements View.OnClickListener {

    EditText personsEmail, intro, personsName, stupidThings, hatefulAction,
            outro;
    String option1, option2, option3, option4, option5, option6;

    Button sendEmail;

    int count, getnum;

    TextView outputresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email);
        initializeVars();

        sendEmail.setOnClickListener(this);
    }

    private void initializeVars() {
        // TODO Auto-generated method stub
        outputresult = (TextView) findViewById(R.id.getresult);
        personsEmail = (EditText) findViewById(R.id.editText1);
        intro = (EditText) findViewById(R.id.editText2);
        personsName = (EditText) findViewById(R.id.editText3);
        stupidThings = (EditText) findViewById(R.id.editText4);
        hatefulAction = (EditText) findViewById(R.id.editText5);
        outro = (EditText) findViewById(R.id.editText6);
        sendEmail = (Button) findViewById(R.id.buttonemail);
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub

        count = 0;
        convertEditTextVarsIntoStringsAndYesThisIsAMethodWeCreated();
        setcounter();
        operation();

/*        String emailaddress[] = { option1 };
        String message = "Well hello "
                + option2
                + " I just wanted to say "
                + option3
                + ".  Not only that but I hate when you "
                + option4
                + ", that just really makes me crazy.  I just want to make you "
                + option5
                + ".  Welp, thats all I wanted to chit-chatter about, oh and"
                + option6
                + ".  Oh also if you get bored you should check out www.mybringback.com"
                + '\n' + "PS. I think I love you...   :(";
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emailaddress);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "i love you");
        emailIntent.setType("plain/text"); //set plain type text
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(emailIntent);
       */
    }

    private void convertEditTextVarsIntoStringsAndYesThisIsAMethodWeCreated() {
        // TODO Auto-generated method stub
        option1 = personsEmail.getText().toString();
        option2 = intro.getText().toString();
        option3 = personsName.getText().toString();
        option4 = stupidThings.getText().toString();
        option5 = hatefulAction.getText().toString();
        option6 = outro.getText().toString();
    }

    private void setcounter(){
        if(!(option1.matches(""))){
            count++;
        }
        if(!(option2.matches(""))) {
            count++;
        }
        if(!(option3.matches(""))){
            count++;
        }
        if(!(option4.matches(""))){
            count++;
        }
        if(!(option5.matches(""))){
            count++;
        }
        if(!(option6.matches(""))){
            count++;
        }
    }

    private void operation(){
        Random output = new Random();
        getnum = output.nextInt(count);
        switch(getnum){
            case 0:
                outputresult.setText(option1);
                break;
            case 1:
                outputresult.setText(option2);
                break;
            case 2:
                outputresult.setText(option3);
                break;
            case 3:
                outputresult.setText(option4);
                break;
            case 4:
                outputresult.setText(option5);
                break;
            case 5:
                outputresult.setText(option6);
                break;
            default:
                outputresult.setText("count = "+ count+ "getnum =" + getnum);
        }
    }
    //@Override
   // protected void onPause() {
        // TODO Auto-generated method stub
    //    super.onPause();
     //   finish();
   // }

}