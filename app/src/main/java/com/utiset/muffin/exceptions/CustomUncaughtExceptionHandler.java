package com.utiset.muffin.exceptions;

import com.utiset.muffin.logger.AppLogger;

import java.io.IOException;

/**
 * Created by ife on 03/06/16.
 */
public class CustomUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        new AppLogger(ex.getMessage());
    }
}
