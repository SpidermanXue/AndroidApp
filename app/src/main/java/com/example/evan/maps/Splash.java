package com.example.evan.maps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Evan on 8/8/15.
 */
public class Splash extends Activity{
    @Override
    protected void onCreate(Bundle evan ) {
        super.onCreate(evan); //Bundle: a collection of things
        setContentView(R.layout.splash); //bring in the splash.xml file and shows it
        //set background music
        Thread timer = new Thread(){ //set up a thread to sleep, do mutiple thing at same time
            public void run(){
                try{
                    sleep(2000); // let splash sleep for 5 seconds
                }catch(InterruptedException e){
                    e.printStackTrace(); //print error
                }finally {
                    //start and jump to myactivity class
                    Intent openMyactivity = new Intent("com.example.evan.maps.HomePageActivity");
                    startActivity(openMyactivity);/* start */
                }
            }
        };

        timer.start();//from Thread class, to start our thread
    }

    @Override
    protected void onPause(){
        //when jump to myactivity , onPause of splash is called
        //called after jump to myactivity class
        super.onPause();
        finish(); //kill the splash class.
    }


}
