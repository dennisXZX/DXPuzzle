package com.dennisxiao.dxpuzzle;

import android.media.MediaPlayer;
import android.util.Log;

/**
 * Created by Dennis Xiao on 07-Aug-16.
 */
public class Utility {

    // this method release media resource
    public static void releaseMediaResource(MediaPlayer m){

        if(m != null){
            m.release();
            m = null;
        }

    }

}
