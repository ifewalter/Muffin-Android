package com.utiset.muffin.core;

import android.content.Context;

import com.utiset.muffin.data.FeedData;
import com.utiset.muffin.exceptions.NullContextExceptions;
import com.utiset.muffin.models.FeedItemModel;
import com.utiset.muffin.models.FeedResponseModel;
import com.utiset.muffin.models.IpLocationModel;
import com.utiset.muffin.models.ResponseModel;

import java.io.IOException;

/**
 * Created by ife on 25.09.16.
 */
public class IpLocationCall extends CoreBase {

    public IpLocationCall(Context _ctx) throws NullContextExceptions {
        super(_ctx);
    }

    public IpLocationModel getDeviceLocation(String url) throws IOException {
        ResponseModel responseModel = serverCalls.getData(url);

        if (responseModel.getStatusCode() == 200)
        {
            IpLocationModel ipLocationModel = new IpLocationModel();
            ipLocationModel = (IpLocationModel) ipLocationModel.fromJson(responseModel.getResponseBody());
            return ipLocationModel;
        }

        return null;

    }
}
