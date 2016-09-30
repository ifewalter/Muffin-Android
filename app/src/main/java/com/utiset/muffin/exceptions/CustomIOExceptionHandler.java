package com.utiset.muffin.exceptions;

import com.utiset.muffin.logger.AppLogger;

import java.io.IOException;

/**
 * Created by ife on 03/06/16.
 */
public class CustomIOExceptionHandler extends IOException {
    public CustomIOExceptionHandler(String message)
    {
        new AppLogger(message);
    }
}
