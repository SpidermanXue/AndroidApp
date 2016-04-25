package com.example.evan.maps;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Evan on 8/18/15.
 */
public class SpinWheel extends Activity implements View.OnClickListener{

    private String lastactivity;
    private static Bitmap imageOriginal, imageScaled;
    private static Matrix matrix;

    private TextView resultoption;
    private Button button;
    private ImageView dialer;
    private int dialerHeight, dialerWidth;
    private boolean buttoncontroller;
    private List<String> searchlist;
    private GestureDetector detector;

    private boolean[] quadrantTouched;
    private boolean allowRotating;

    private int divCount;
    private int divAngle;
    private double totalRotation;
    private int top;
    private String selectedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        divCount = 0;
        super.onCreate(savedInstanceState);
        lastactivity  = getIntent().getExtras().getString("caller");
        setContentView(R.layout.spinwheel);
        // load the image only once
        //tansfer to bitmap and change the imageview
        if (imageOriginal == null) {
            if(lastactivity.equals("MapsActivity")) {
                searchlist = getIntent().getExtras().getStringArrayList("option");
            }else{
                searchlist = getIntent().getExtras().getStringArrayList("option");
            }
            divCount = searchlist.size();
            setDivCount(divCount);

                switch(divCount){
                    case 2:
                        imageOriginal = BitmapFactory.decodeResource(getResources(), R.drawable.two);
                        break;
                    case 3:
                        imageOriginal = BitmapFactory.decodeResource(getResources(), R.drawable.three);
                        break;
                    case 4:
                        imageOriginal = BitmapFactory.decodeResource(getResources(), R.drawable.four);
                        break;
                    case 5:
                        imageOriginal = BitmapFactory.decodeResource(getResources(), R.drawable.five);
                        break;
                    case 6:
                        imageOriginal = BitmapFactory.decodeResource(getResources(), R.drawable.six);
                        break;
                    case 7:
                        imageOriginal = BitmapFactory.decodeResource(getResources(), R.drawable.seven);
                        break;
                    case 8:
                        imageOriginal = BitmapFactory.decodeResource(getResources(), R.drawable.eight);
                        break;
                    default:
                        imageOriginal = BitmapFactory.decodeResource(getResources(), R.drawable.eight);
                        break;
                }
        }

        // initialize the matrix only once
        if (matrix == null) {
            matrix = new Matrix();
        } else {
            // not needed, you can also post the matrix immediately to restore the old state
            matrix.reset();
        }

        buttoncontroller = true;

        detector = new GestureDetector(this, new MyGestureDetector());

        quadrantTouched = new boolean[] {false,false,false,false,false};

        allowRotating = true;

        dialer = (ImageView) findViewById(R.id.imageView_ring);

        resultoption = (TextView) findViewById(R.id.selected_position);

        button = (Button) findViewById(R.id.wlbutton);

        button.setOnClickListener(this);

        dialer.setOnTouchListener(new MyOnTouchListener());

        dialer.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                // method called more than once, but the values only need to be initialized one time
                if (dialerHeight == 0 || dialerWidth == 0) {
                    dialerHeight = dialer.getHeight();
                    dialerWidth = dialer.getWidth();

                    // resize
                    Matrix resize = new Matrix();
                    resize.postScale((float)Math.min(dialerWidth, dialerHeight) / (float)imageOriginal.getWidth(), (float)Math.min(dialerWidth, dialerHeight) / (float)imageOriginal.getHeight());
                    imageScaled = Bitmap.createBitmap(imageOriginal, 0, 0, imageOriginal.getWidth(), imageOriginal.getHeight(), resize, false);
                    imageOriginal = null;

                    float translateX = dialerWidth / 2 - imageScaled.getWidth() / 2;
                    float translateY = dialerHeight / 2 - imageScaled.getHeight() / 2;
                    dialer.setImageBitmap(imageScaled);
                    dialer.setImageMatrix(matrix);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        buttoncontroller = false;
        button.setText("STOP");
    }

    /**
     * Simple implementation of an {@link OnTouchListener} for registering the dialer's touch events.
     */
    private class MyOnTouchListener implements OnTouchListener {

        private double startAngle;

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    for(int i = 0; i< quadrantTouched.length; i++){
                        quadrantTouched[i] = false;
                    }
                    startAngle = getAngle(event.getX(), event.getY());
                    break;

                case MotionEvent.ACTION_MOVE:
                    double currentAngle = getAngle(event.getX(), event.getY());
                    rotateDialer((float) (startAngle - currentAngle));
                    startAngle = currentAngle;
                    allowRotating = true;
                    break;

                case MotionEvent.ACTION_UP:
                    allowRotating = true;
                    buttoncontroller = true;
                    break;
            }

            quadrantTouched[getQuadrant(event.getX() - (dialerWidth / 2), dialerHeight - event.getY() -(dialerHeight / 2))] = true;
            detector.onTouchEvent(event);
            return true;
        }

    }


    private class MyGestureDetector extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            int q1 = getQuadrant(e1.getX() - (dialerWidth / 2), dialerHeight - e1.getY() - (dialerHeight /2));
            int q2 = getQuadrant(e2.getX() - (dialerWidth / 2), dialerHeight - e2.getY() - (dialerHeight /2));

            // the inversed rotations
            if ((q1 == 2 && q2 == 2 && Math.abs(velocityX) < Math.abs(velocityY))
                    || (q1 == 3 && q2 == 3)
                    || (q1 == 1 && q2 == 3)
                    || (q1 == 4 && q2 == 4 && Math.abs(velocityX) > Math.abs(velocityY))
                    || ((q1 == 2 && q2 == 3) || (q1 == 3 && q2 == 2))
                    || ((q1 == 3 && q2 == 4) || (q1 == 4 && q2 == 3))
                    || (q1 == 2 && q2 == 4 && quadrantTouched[3])
                    || (q1 == 4 && q2 == 2 && quadrantTouched[3])) {

                dialer.post(new FlingRunnable(-1 * (velocityX + velocityY)));
            } else {
                // the normal rotation
                dialer.post(new FlingRunnable(velocityX + velocityY));
            }

            return true;
        }
    }

    private class FlingRunnable implements Runnable {

        private float velovity;

        public FlingRunnable(float velovity){
            this.velovity = velovity;
        }

        @Override
        public void run() {
            if(Math.abs(velovity) > 5 && allowRotating){
                 rotateDialer(velovity / 75);
                if(!buttoncontroller) {
                    velovity /= 1.0666F;
                   // velovity = 0;
                }
                dialer.post(this);
            }
            else {
                setTop();
            }
        }
    }

    public void setTop(){
        //get the total angle rotated in 360 degrees

        totalRotation = totalRotation % 360;

        //represent total rotation in positive value
        if (totalRotation < 0) {
            totalRotation = 360 + totalRotation;
        }
        switch(divCount) {
            case 2:
                if( totalRotation > 0 && totalRotation <= 180) selectedPosition = "B. " + searchlist.get(1);
                else selectedPosition = "A. " + searchlist.get(0);
                break;
            case 3:
                if( totalRotation > 0 && totalRotation <= 120) selectedPosition = "C. " + searchlist.get(2);
                else if ( totalRotation > 120 && totalRotation <= 240) selectedPosition = "B. " + searchlist.get(1);
                else selectedPosition = "A. " + searchlist.get(0);
                break;
            case 4:
                if( totalRotation > 0 && totalRotation <= 90) selectedPosition = "D. " + searchlist.get(3);
                else if ( totalRotation > 90 && totalRotation <= 180) selectedPosition = "C. " + searchlist.get(2);
                else if ( totalRotation > 180 && totalRotation <= 270) selectedPosition = "B. " + searchlist.get(1);
                else selectedPosition = "A. " + searchlist.get(0);
                break;
            case 5:
                if( totalRotation > 0 && totalRotation <= 72) selectedPosition = "E. " + searchlist.get(4);
                else if ( totalRotation > 72 && totalRotation <= 144) selectedPosition = "D. " + searchlist.get(3);
                else if ( totalRotation > 144 && totalRotation <= 216) selectedPosition = "C. " + searchlist.get(2);
                else if ( totalRotation > 216 && totalRotation <= 288) selectedPosition = "B. " + searchlist.get(1);
                else selectedPosition = "A. " + searchlist.get(0);
                break;
            case 6:
                if( totalRotation > 0 && totalRotation <= 60) selectedPosition = "F. " + searchlist.get(5);
                else if ( totalRotation > 60 && totalRotation <= 120) selectedPosition = "E. " + searchlist.get(4);
                else if ( totalRotation > 120 && totalRotation <= 180) selectedPosition = "D. " + searchlist.get(3);
                else if ( totalRotation > 180 && totalRotation <= 240) selectedPosition = "C. " + searchlist.get(2);
                else if ( totalRotation > 240 && totalRotation <= 300) selectedPosition = "B. " + searchlist.get(1);
                else selectedPosition = "A. " + searchlist.get(0);
                break;
            case 7:
                if( totalRotation > 0 && totalRotation <= (360/7)) selectedPosition = "G. " + searchlist.get(6);
                else if ( totalRotation > (360/7) && totalRotation <= (360*2/7)) selectedPosition = "F. " + searchlist.get(5);
                else if ( totalRotation > (360*2/7) && totalRotation <= (360*3/7)) selectedPosition = "E. " + searchlist.get(4);
                else if ( totalRotation > (360*3/7) && totalRotation <= (360*4/7)) selectedPosition = "D. " + searchlist.get(3);
                else if ( totalRotation > (360*4/7) && totalRotation <= (360*5/7)) selectedPosition = "C. " + searchlist.get(2);
                else if ( totalRotation > (360*5/7) && totalRotation <= (360*6/7)) selectedPosition = "B. " + searchlist.get(1);
                else selectedPosition = "A. " + searchlist.get(0);
                break;
            case 8:
                if( totalRotation > 0 && totalRotation <= 45) selectedPosition = "H. " + searchlist.get(7);
                else if ( totalRotation > 45 && totalRotation <= 90) selectedPosition = "G. " + searchlist.get(6);
                else if ( totalRotation > 90 && totalRotation <= 135) selectedPosition = "F. " + searchlist.get(5);
                else if ( totalRotation > 135 && totalRotation <= 180) selectedPosition = "E. " + searchlist.get(4);
                else if ( totalRotation > 180 && totalRotation <= 225) selectedPosition = "D. " + searchlist.get(3);
                else if ( totalRotation > 225 && totalRotation <= 270) selectedPosition = "C. " + searchlist.get(2);
                else if ( totalRotation > 270 && totalRotation <= 315) selectedPosition = "B. " + searchlist.get(1);
                else selectedPosition = "A. " + searchlist.get(0);
                break;
            default:
                if( totalRotation > 0 && totalRotation <= 45) selectedPosition = "H." + searchlist.get(7);
                else if ( totalRotation > 45 && totalRotation <= 90) selectedPosition = "G. " + searchlist.get(6);
                else if ( totalRotation > 90 && totalRotation <= 135) selectedPosition = "F. " + searchlist.get(5);
                else if ( totalRotation > 135 && totalRotation <= 180) selectedPosition = "E. " + searchlist.get(4);
                else if ( totalRotation > 180 && totalRotation <= 225) selectedPosition = "D. " + searchlist.get(3);
                else if ( totalRotation > 225 && totalRotation <= 270) selectedPosition = "C. " + searchlist.get(2);
                else if ( totalRotation > 270 && totalRotation <= 315) selectedPosition = "B. " + searchlist.get(1);
                else selectedPosition = "A. " + searchlist.get(0);
        }
        //calculate the no of divs the rotation has crossed
  //      int no_of_divs_crossed = (int) ((totalRotation) / divAngle);

        //calculate current top
    //    top = (divCount + top + no_of_divs_crossed) % divCount; //change

        //for next rotation, the initial total rotation will be the no of degrees
        // inside the current top
      //  totalRotation = totalRotation % divCount;


        //snapping to the top's center
    //    if (true) {

            //calculate the angle to be rotated to reach the top's center.
      //      double leftover = divAngle / 2 - totalRotation;

        //    rotateDialer((float) (leftover));

            //re-initialize total rotation
         //   totalRotation = divAngle / 2;
       // }

        //set the currently selected option
      //  if (top == 0) {
      //      selectedPosition = divCount - 1;//loop around the array
      //  } else {
            //selectedPosition = top - 1;
      //      selectedPosition = top - 1;
       // }
        //selectedPosition = top;
        resultoption.setText(selectedPosition + "!!!");
    }

    private double getAngle(double xTouch, double yTouch) {
        double x = xTouch - (dialerWidth / 2d);
        double y = dialerHeight - yTouch - (dialerHeight / 2d);

        switch (getQuadrant(x, y)) {
            case 1:
                return Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI;
            case 2:
                return 180 - Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI;
            case 3:
                return 180 + (-1 * Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI);
            case 4:
                return 360 + Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI;
            default:
                return 0;
        }
    }


    public void setDivCount(int div) {
        divAngle = 360 / div;
        //totalRotation = -1 * (divAngle / 2);
        totalRotation = 0;
        top = 0;
    }

    /**
     * @return The selected quadrant.
     */
    private static int getQuadrant(double x, double y) {
        if (x >= 0) {
            return y >= 0 ? 1 : 4;
        } else {
            return y >= 0 ? 2 : 3;
        }
    }

    private void rotateDialer(float degrees) {
        matrix.postRotate(degrees, dialerWidth / 2, dialerHeight / 2);
        dialer.setImageMatrix(matrix);

        totalRotation = totalRotation + degrees; //add
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
                    Intent ourintent = new Intent(SpinWheel.this, rclass);
                    startActivity(ourintent);
                }catch(ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return true;

            case R.id.presetquestion:
                try{
                    Class rclass= Class.forName("com.example.evan.maps.PresetQuestionActivity");
                    Intent ourintent = new Intent(SpinWheel.this, rclass);
                    startActivity(ourintent);
                }catch(ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return true;
            case R.id.homepage:
                try{
                    Class rclass= Class.forName("com.example.evan.maps.HomePageActivity");
                    Intent ourintent = new Intent(SpinWheel.this, rclass);
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
