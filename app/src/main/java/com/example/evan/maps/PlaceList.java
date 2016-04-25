package com.example.evan.maps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Evan on 8/24/15.
 */
public class PlaceList extends Activity implements View.OnLongClickListener{
    public ArrayList<String> searchlist = new ArrayList<String>();
    public ArrayList<String> list;
    TextView option1,option2,option3,option4,option5,option6,option7,option8;
    int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placelist);
        String option;
        counter = 0;
        for( int i = 1 ; i <= 8; i++){
            option = "tvpl" + i;
            int id = getResources().getIdentifier(option, "id", getPackageName());

            if(id !=0){
                TextView output = (TextView) findViewById(id);
                output.setOnLongClickListener(this);
                switch(i){
                    case 1:
                        option = "A";
                        break;
                    case 2:
                        option = "B";
                        break;
                    case 3:
                        option = "C";
                        break;
                    case 4:
                        option = "D";
                        break;
                    case 5:
                        option = "E";
                        break;
                    case 6:
                        option = "F";
                        break;
                    case 7:
                        option = "G";
                        break;
                    case 8:
                        option = "H";
                        break;
                    default:
                        option = "H";
                        break;
                }
                String hold= option +". " + PlacesDisplayTask.placelist.get(i-1);
                searchlist.add(hold);
                output.setText(hold);
            }
        }
    }

    public void onClick_Spin(View v) {
        list = new ArrayList<String>();
        if (searchlist.size() >= 2) {
            for(int m = 0; m < searchlist.size();m++){
                list.add(searchlist.get(m).substring(3));
            }
            try {
                Class rclass = Class.forName("com.example.evan.maps.SpinWheel");
                Intent ourintent = new Intent(PlaceList.this, rclass);
                ourintent.putExtra("caller", "MapsActivity");
                ourintent.putStringArrayListExtra("option", (ArrayList<String>) list);
                //pass a number tp flipperresult
                startActivity(ourintent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public void onClick_Back(View v){
        try{
            Class rclass= Class.forName("com.example.evan.maps.HomePageActivity");
            Intent ourintent = new Intent(PlaceList.this, rclass);
            startActivity(ourintent);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
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
                    Intent ourintent = new Intent(PlaceList.this, rclass);
                    startActivity(ourintent);
                }catch(ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return true;

            case R.id.presetquestion:
                try{
                    Class rclass= Class.forName("com.example.evan.maps.PresetQuestionActivity");
                    Intent ourintent = new Intent(PlaceList.this, rclass);
                    startActivity(ourintent);
                }catch(ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return true;
            case R.id.homepage:
                try{
                    Class rclass= Class.forName("com.example.evan.maps.HomePageActivity");
                    Intent ourintent = new Intent(PlaceList.this, rclass);
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
    public boolean onLongClick(View v) {
        switch(v.getId()){
            case R.id.tvpl1:
                option1 = (TextView) findViewById(R.id.tvpl1);
                option1.setVisibility(View.GONE);
                break;
            case R.id.tvpl2:
                option1 = (TextView) findViewById(R.id.tvpl2);
                option1.setVisibility(View.GONE);
                break;
            case R.id.tvpl3:
                option1 = (TextView) findViewById(R.id.tvpl3);
                option1.setVisibility(View.GONE);
                break;
            case R.id.tvpl4:
                option1 = (TextView) findViewById(R.id.tvpl4);
                option1.setVisibility(View.GONE);
                break;
            case R.id.tvpl5:
                option1 = (TextView) findViewById(R.id.tvpl5);
                option1.setVisibility(View.GONE);
                break;
            case R.id.tvpl6:
                option1 = (TextView) findViewById(R.id.tvpl6);
                option1.setVisibility(View.GONE);
                break;
            case R.id.tvpl7:
                option1 = (TextView) findViewById(R.id.tvpl7);
                option1.setVisibility(View.GONE);
                break;
            case R.id.tvpl8:
                option1 = (TextView) findViewById(R.id.tvpl8);
                option1.setVisibility(View.GONE);
                break;
        }

        for(int n=0; n <searchlist.size();n++){
            if(option1.getText() == searchlist.get(n)){
                searchlist.remove(n);
            }
        }

        return false;
    }


}
