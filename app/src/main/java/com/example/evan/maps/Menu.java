package com.example.evan.maps;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;

/**
 * Created by Evan on 8/9/15.
 */
public class Menu extends ListActivity{
    String classes[] = { "MyActivity", "TextPlay", "Email",
            "MapsActivity","Flipper","SpinWheel","BigWheel"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1 , classes)); //fot list activity, String is the type
        //class name, int , string.
        //like list array
        // item1 -> every word occpy one position.
    }

    //activate when list got clicked
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        //postion is the number of the position. can track which item be clicked;
        String cheese = classes[position]; //if position = 1, then cheese = MyActivity.
        try {
            //Class ourClass = Class.forName("com.example.evan.jianxue.MyActivity"); //class variable
            Class ourClass = Class.forName("com.example.evan.maps." + cheese);
            //when click some item, it will jump to it. If can not find that class, it will stay.
            //same as intent in splash.
            Intent ourIntent = new Intent(Menu.this, ourClass); //start place, destination
            //same as Intent ourIntent = new Intent("com.example.evan.jianxue" + cheese); ??
            //1.put destination string
            //2.put classname.this, class type  variable better
            //"this" refers to the containing class
            // "classname.this" refers to the first containing class which names classname
            //which mean if i want to ust Menu class inside class, I have to do Menu.this
            startActivity(ourIntent); //jump to another page

        }catch(ClassNotFoundException e){//catch if the class pachage not found
            e.printStackTrace();//print error
        }
    }




}
