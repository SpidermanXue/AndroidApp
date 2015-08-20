package com.example.evan.maps;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
/**
 * Created by Evan on 8/18/15.
 */
public class SpinChoice extends Activity{
    private String[] arraySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinchoice);

        this.arraySpinner = new String[] {
                "KFC", "Red Robin", "McDonald", "ChineseFood", "Rubios"
        };
        Spinner spin = (Spinner) findViewById(R.id.Spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
        spin.setAdapter(adapter);
    }
}
