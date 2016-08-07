package com.dennisxiao.dxpuzzle;

import android.media.MediaPlayer;

/**
 * Created by Dennis Xiao on 07-Aug-16.
 */
public class Utility {

    public static void releaseMediaResource(MediaPlayer m){

        if(m != null){
            m.release();
            m = null;
        }

    }

}
