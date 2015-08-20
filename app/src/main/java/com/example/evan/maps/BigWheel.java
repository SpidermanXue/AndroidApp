package com.example.evan.maps;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.evan.maps.WheelMenu.*;

/**
 * Created by Evan on 8/19/15.
 */

public class BigWheel extends Activity{

    private WheelMenu wheelMenu;
    private TextView selectedPositionText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bigwheel);

        wheelMenu = (WheelMenu) findViewById(R.id.wheelMenu);

        wheelMenu.setDivCount(12);
        wheelMenu.setWheelImage(R.drawable.wheel);

        selectedPositionText = (TextView) findViewById(R.id.selected_position_text);
        selectedPositionText.setText("selected: " + (wheelMenu.getSelectedPosition() + 1));

       // wheelMenu.rotateWheel(180);
       // this.setOnTouchListener(new WheelTouchListener());

       // wheelMenu.setOnTouchListener(new WheelTouchListener());
      //  wheelMenu.xuejian();
        wheelMenu.setSnapToCenterFlag(false);
        wheelMenu.setWheelChangeListener(new WheelChangeListener() {
            @Override
            public void onSelectionChange(int selectedPosition) {
                selectedPositionText.setText("selected: " + (selectedPosition + 1));
            }
        });

    }
}
