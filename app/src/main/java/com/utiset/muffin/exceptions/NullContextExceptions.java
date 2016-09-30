package com.utiset.muffin.exceptions;

import java.util.concurrent.Executor;

/**
 * Created by ife on 04/06/16.
 */
//Null check on contexts
public class NullContextExceptions extends Exception {
    //Parameterless Constructor
    public NullContextExceptions() {}

    //Constructor that accepts a message
    public NullContextExceptions(String message)
    {
        super(message);

    }
}
