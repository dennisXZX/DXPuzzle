package com.dennisxiao.dxpuzzle;

/**
 * Created by Dennis Xiao on 08-Sep-16.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 1700;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

         /* New Handler to start the SplashActivity activity
         * and close this SplashActivity-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {
            public void run() {
                /* Create an Intent that will start the Main Activity. */
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}