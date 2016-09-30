package com.utiset.muffin.helpers;

import android.content.Context;

import com.utiset.muffin.muffin.R;
import com.utiset.muffin.exceptions.NullContextExceptions;

/**
 * Created by ife on 03/06/16.
 */
public class UrlHelper {
    private Context ctx;
    public UrlHelper(Context _ctx) throws NullContextExceptions {
        if (_ctx == null)
        {
            throw new NullContextExceptions();
        }
        ctx = _ctx;
    }

    /**
     * Combines the api root and the endpoint url and returns one Whole URL
     * @param _endpoint
     * @return url
     */
    public String getUrl(int _endpoint)
    {

        return ctx.getString(R.string.api_root).concat(ctx.getString(_endpoint));

    }
}
