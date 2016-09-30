package com.utiset.muffin.core;

import android.content.Context;

import com.utiset.muffin.exceptions.NullContextExceptions;
import com.utiset.muffin.helpers.ServerCallHelper;
import com.utiset.muffin.helpers.UrlHelper;
import com.utiset.muffin.models.ResponseModel;

/**
 * Created by ife on 04/06/16.
 */
public class CoreBase {
    public Context ctx;
    public ServerCallHelper serverCalls;
    public UrlHelper urlHelper;
    public ResponseModel responseModel;

    public CoreBase(Context _ctx) throws NullContextExceptions {
        ctx = _ctx;
        serverCalls = new ServerCallHelper(ctx);
        urlHelper = new UrlHelper(ctx);
    }
}
