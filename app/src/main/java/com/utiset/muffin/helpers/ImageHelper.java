package com.utiset.muffin.helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ife on 22/08/16.
 */
public class ImageHelper {

    public static int calculateHeightFromAspectRatio(int containerWidth, Bitmap bitmap) {

        int imageWidth = bitmap.getWidth();
        int imageHeight = bitmap.getHeight();

        Log.e("CALC_HEIGHT", String.valueOf(imageHeight));
        Log.e("CALC_WIDTH", String.valueOf(imageWidth));
        Log.e("CALC_WIDTH_2", String.valueOf(containerWidth));


        return ((containerWidth*imageHeight)/imageWidth);
    }


}
