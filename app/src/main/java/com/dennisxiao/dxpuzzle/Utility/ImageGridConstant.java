package com.dennisxiao.dxpuzzle.Utility;

import com.dennisxiao.dxpuzzle.R;

import java.util.Random;

/**
 * Created by Dennis Xiao on 19-Jul-16.
 */
public class ImageGridConstant {

    // initialize a multi-dimensional array to store grid resources
    private static final int[][] imageGrid = {
            {R.drawable.img_bear_00x00, R.drawable.img_bear_00x01, R.drawable.img_bear_00x02,
             R.drawable.img_bear_01x00, R.drawable.img_bear_01x01, R.drawable.img_bear_01x02,
             R.drawable.img_bear_02x00, R.drawable.img_bear_02x01, R.drawable.img_bear_02x02},

            {R.drawable.img_dog_00x00, R.drawable.img_dog_00x01, R.drawable.img_dog_00x02,
             R.drawable.img_dog_01x00, R.drawable.img_dog_01x01, R.drawable.img_dog_01x02,
             R.drawable.img_dog_02x00, R.drawable.img_dog_02x01, R.drawable.img_dog_02x02},

            {R.drawable.img_cat_00x00, R.drawable.img_cat_00x01, R.drawable.img_cat_00x02,
             R.drawable.img_cat_01x00, R.drawable.img_cat_01x01, R.drawable.img_cat_01x02,
             R.drawable.img_cat_02x00, R.drawable.img_cat_02x01, R.drawable.img_cat_02x02}
    };

    // retrieve a random image grid from ImageGridConstant array
    public static int[] getImageGrid(){

        // generate a random number
        int ranNum = new Random().nextInt(imageGrid.length);

        // return a random image grid
        return imageGrid[ranNum];
    }


}
