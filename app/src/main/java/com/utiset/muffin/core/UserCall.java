package com.utiset.muffin.core;

import android.content.Context;

import com.utiset.muffin.data.FeedData;
import com.utiset.muffin.exceptions.NullContextExceptions;
import com.utiset.muffin.models.FeedItemModel;
import com.utiset.muffin.models.FeedResponseModel;
import com.utiset.muffin.models.ResponseModel;

import java.io.IOException;

/**
 * Created by ife on 23/08/16.
 */
public class UserCall extends CoreBase{
    public UserCall(Context _ctx) throws NullContextExceptions {
        super(_ctx);
    }

    public boolean createUser(String url, String userData) throws IOException {
        ResponseModel responseModel = serverCalls.postData(url, userData);
        if (responseModel.getStatusCode() == 200)
        {
            return true;
        }
        return false;
    }
}
