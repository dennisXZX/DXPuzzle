package com.dennisxiao.dxpuzzle;

import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import java.util.Arrays;
import java.util.Random;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // create a local variable for identifying the class where the log statements come from
    private final static String LOG_TAG = MainActivity.class.getSimpleName();

    // ImageButton for displaying the puzzle image
    private ImageButton ib_00;
    private ImageButton ib_01;
    private ImageButton ib_02;
    private ImageButton ib_10;
    private ImageButton ib_11;
    private ImageButton ib_12;
    private ImageButton ib_20;
    private ImageButton ib_21;
    private ImageButton ib_22;

    // TextView for displaying puzzle time
    private TextView puzzleTime;
    private Button btn_restart;

    // initialize the width and height of the puzzle square
    private int squareWidth = 3;
    private int squareHeight = 3;
    
    // initialize the blank position and the blank ImageButton id, by default the last grid is blank
    private int blankPos = 8;
    private int blankImgId = R.id.ib_02_02;

    // initialize an int array to store the order of the images
    private int[] imageOrder = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8};

    // retrieve a random image
    private int[] imageId = ImageGrid.getImageGrid();

    // initialize variables for recording time
    private boolean timeSwitch = true;

    private int time = 0;

    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg) {
            if(msg.what == 1){
                String time = (String) msg.obj;
                puzzleTime.setText(time);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize the game
        initGame();

        // create an anonymous thread to record time
        new Thread(){
            public void run() {

                time = 0;

                while(timeSwitch == true){
                    String strTime = time + "";
                    Message msg = Message.obtain();
                    msg.what = 1;
                    msg.obj = strTime;
                    mHandler.sendMessage(msg);

                    try {
                        Thread.sleep(1000);
                        time++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            } // end of run()

        }.start(); // end of Thread()

    } // end of onCreate()

    // initialize the game
    private void initGame() {

        puzzleTime = (TextView) findViewById(R.id.puzzleTime);
        btn_restart = (Button) findViewById(R.id.btn_reset);

        ib_00 = (ImageButton) findViewById(R.id.ib_00_00);
        ib_01 = (ImageButton) findViewById(R.id.ib_00_01);
        ib_02 = (ImageButton) findViewById(R.id.ib_00_02);
        ib_10 = (ImageButton) findViewById(R.id.ib_01_00);
        ib_11 = (ImageButton) findViewById(R.id.ib_01_01);
        ib_12 = (ImageButton) findViewById(R.id.ib_01_02);
        ib_20 = (ImageButton) findViewById(R.id.ib_02_00);
        ib_21 = (ImageButton) findViewById(R.id.ib_02_01);
        ib_22 = (ImageButton) findViewById(R.id.ib_02_02);

        // bind onClick listeners to ImageButton
        ib_00.setOnClickListener(puzzleButtonOnClickListener);
        ib_01.setOnClickListener(puzzleButtonOnClickListener);
        ib_02.setOnClickListener(puzzleButtonOnClickListener);
        ib_10.setOnClickListener(puzzleButtonOnClickListener);
        ib_11.setOnClickListener(puzzleButtonOnClickListener);
        ib_12.setOnClickListener(puzzleButtonOnClickListener);
        ib_20.setOnClickListener(puzzleButtonOnClickListener);
        ib_21.setOnClickListener(puzzleButtonOnClickListener);
        ib_22.setOnClickListener(puzzleButtonOnClickListener);

        // shuffle the image order
        random();

        // after shuffling the array, set images to the ImageViews
        ib_00.setImageDrawable(ResourcesCompat.getDrawable(getResources(), imageId[imageOrder[0]], null));
        ib_01.setImageDrawable(ResourcesCompat.getDrawable(getResources(), imageId[imageOrder[1]], null));
        ib_02.setImageDrawable(ResourcesCompat.getDrawable(getResources(), imageId[imageOrder[2]], null));
        ib_10.setImageDrawable(ResourcesCompat.getDrawable(getResources(), imageId[imageOrder[3]], null));
        ib_11.setImageDrawable(ResourcesCompat.getDrawable(getResources(), imageId[imageOrder[4]], null));
        ib_12.setImageDrawable(ResourcesCompat.getDrawable(getResources(), imageId[imageOrder[5]], null));
        ib_20.setImageDrawable(ResourcesCompat.getDrawable(getResources(), imageId[imageOrder[6]], null));
        ib_21.setImageDrawable(ResourcesCompat.getDrawable(getResources(), imageId[imageOrder[7]], null));
        ib_22.setImageDrawable(ResourcesCompat.getDrawable(getResources(), imageId[imageOrder[8]], null));

        btn_restart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                // get a random image
                imageId = ImageGrid.getImageGrid();

                // shuffle the image order
                random();

                // after shuffling the array, set images to the ImageViews
                ib_00.setImageDrawable(ResourcesCompat.getDrawable(getResources(), imageId[imageOrder[0]], null));
                ib_01.setImageDrawable(ResourcesCompat.getDrawable(getResources(), imageId[imageOrder[1]], null));
                ib_02.setImageDrawable(ResourcesCompat.getDrawable(getResources(), imageId[imageOrder[2]], null));
                ib_10.setImageDrawable(ResourcesCompat.getDrawable(getResources(), imageId[imageOrder[3]], null));
                ib_11.setImageDrawable(ResourcesCompat.getDrawable(getResources(), imageId[imageOrder[4]], null));
                ib_12.setImageDrawable(ResourcesCompat.getDrawable(getResources(), imageId[imageOrder[5]], null));
                ib_20.setImageDrawable(ResourcesCompat.getDrawable(getResources(), imageId[imageOrder[6]], null));
                ib_21.setImageDrawable(ResourcesCompat.getDrawable(getResources(), imageId[imageOrder[7]], null));
                ib_22.setImageDrawable(ResourcesCompat.getDrawable(getResources(), imageId[imageOrder[8]], null));

            }
        });

    }

    // create an onclick listener for puzzle button
    View.OnClickListener puzzleButtonOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            // check which ImageButton is clicked
            switch (v.getId()) {
                case R.id.ib_00_00:
                    move(R.id.ib_00_00, 0);
                    break;
                case R.id.ib_00_01:
                    move(R.id.ib_00_01, 1);
                    break;
                case R.id.ib_00_02:
                    move(R.id.ib_00_02, 2);
                    break;
                case R.id.ib_01_00:
                    move(R.id.ib_01_00, 3);
                    break;
                case R.id.ib_01_01:
                    move(R.id.ib_01_01, 4);
                    break;
                case R.id.ib_01_02:
                    move(R.id.ib_01_02, 5);
                    break;
                case R.id.ib_02_00:
                    move(R.id.ib_02_00, 6);
                    break;
                case R.id.ib_02_01:
                    move(R.id.ib_02_01, 7);
                    break;
                case R.id.ib_02_02:
                    move(R.id.ib_02_02, 8);
                    break;
            }
        }
    };

    /**
     * swap clicked ImageView position for the blank view one
     *
     * @param clickImageBtnID the Id of the clicked button
     * @param clickPos an int representing the position of the clicked button, ranging from 0 - 8
     */
    public void move(int clickImageBtnID, int clickPos) {

        // keep track of the imageOrder array and clickPos
        Log.i(LOG_TAG, "imageOrder before swap = " + Arrays.toString(imageOrder));
        Log.i(LOG_TAG, "clickPos = " + clickPos);

        // calculate the clicked image button positions
        int clickPosX = clickPos / squareWidth;
        Log.i(LOG_TAG, "clickPosX = " + clickPosX);
        int clickPosY = clickPos % squareHeight;
        Log.i(LOG_TAG, "clickPosY = " + clickPosY);

        // calculate the blank image button positions
        int blankPosX = blankPos / squareWidth;
        Log.i(LOG_TAG, "blankPosX = " + blankPosX);
        int blankPosY = blankPos % squareHeight;
        Log.i(LOG_TAG, "blankPosY = " + blankPosY);

        // calculate the distance between the clicked and the blank buttons
        int distanceX = Math.abs(clickPosX - blankPosX);
        int distanceY = Math.abs(clickPosY - blankPosY);
        Log.i(LOG_TAG, "distanceX = " + distanceX);
        Log.i(LOG_TAG, "distanceY = " + distanceY);

        // check if the clicked button is next to the blank button
        if ((distanceX == 0 && distanceY == 1) || (distanceX == 1 && distanceY == 0)) {

            // find the clicked ImageButton
            ImageButton clickedButton = (ImageButton) findViewById(clickImageBtnID);
            // set the clicked button to invisible
            clickedButton.setVisibility(View.INVISIBLE);

            // find the blank ImageButton
            ImageButton blankButton = (ImageButton) findViewById(blankImgId);
            // change the blank ImageButton to the clicked ImageButton
            blankButton.setImageDrawable(ResourcesCompat.getDrawable(getResources(), imageId[imageOrder[clickPos]], null));
            // set the blank ImageButton visible
            blankButton.setVisibility(View.VISIBLE);

            // swap the clickPos with the blankPos
            swap(clickPos, blankPos);
            // update the blankPos
            blankPos = clickPos;
            // update the blank ImageButton id
            blankImgId = clickImageBtnID;

            // keep track of the imageOrder array
            Log.i(LOG_TAG, "imageOrder after swap = " + Arrays.toString(imageOrder));
            Log.i(LOG_TAG, "----------");

            // check if the game is finished
            gameFinish();

        }else { // alert the users if they are not clicking images next to the blank ImageButton
            Toast.makeText(MainActivity.this, "Please click on image next to the blank", Toast.LENGTH_SHORT).show();
        }
    }

    // make the elements of imageOrder array in random order
    private void random() {

        // declare two variables to hold random numbers
        int ran1, ran2;

        // shuffle the imageOrder array 25 times
        for (int i = 0; i < 25; i++) {
            // initialize the first random number ranging from 0 - 7
            // this keeps the last element in the array untouched, so always hiding the last piece
            ran1 = new Random().nextInt(8);
            ran2 = new Random().nextInt(8);

            // if the two random indices are different, swap them in imageOrder array
            if(ran1 != ran2){
                swap(ran1, ran2);
            }
        } // end of for loop

    } // end of random()

    /**
     * swaps two indices of imageOrder array
     *
     * @param ran1 an int representing the index in an array
     * @param ran2 an int representing the index in an array
     */
    private void swap(int ran1, int ran2) {
        int temp = imageOrder[ran1];
        imageOrder[ran1] = imageOrder[ran2];
        imageOrder[ran2] = temp;
    }

    // check if the game is finished
    private void gameFinish() {

        boolean gameFinish = true;
        
        // iterate through each grid to check if all the images are in the right places
        for (int i=0; i<9; i++) {
            if(imageOrder[i] != i){
                // if any of the image is not in the right place, the game continues
                gameFinish = false;
            }
        }
        
        // game is finished
        if(gameFinish){
            timeSwitch = false;

            // make all the buttons un-clickable
            ib_00.setClickable(false);
            ib_01.setClickable(false);
            ib_02.setClickable(false);
            ib_10.setClickable(false);
            ib_11.setClickable(false);
            ib_12.setClickable(false);
            ib_20.setClickable(false);
            ib_21.setClickable(false);
            ib_22.setClickable(false);

            // display the last hidden piece of the puzzle
            ib_22.setImageDrawable(ResourcesCompat.getDrawable(getResources(), imageId[8], null));
            ib_22.setVisibility(View.VISIBLE);

            // display the congratulation message
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Congratulation! You finished the puzzle with " + time + " seconds!").setPositiveButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builder.create();
            builder.show();
        }

    } // end of gameFinish()
}