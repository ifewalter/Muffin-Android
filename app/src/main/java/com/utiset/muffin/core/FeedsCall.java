package com.utiset.muffin.core;

import android.content.Context;
import android.util.Log;

import com.utiset.muffin.data.FeedData;
import com.utiset.muffin.models.FeedItemModel;
import com.utiset.muffin.models.FeedResponseModel;
import com.utiset.muffin.models.ResponseModel;
import com.utiset.muffin.muffin.R;

import com.utiset.muffin.exceptions.NullContextExceptions;

import java.io.IOException;

/**
 * Created by ife on 14/08/16.
 */
public class FeedsCall extends CoreBase {

    public FeedsCall(Context _ctx) throws NullContextExceptions {
        super(_ctx);
    }

    public boolean getRecentFeeds(String url) throws IOException {
        ResponseModel responseModel = serverCalls.getData(url);

        if (responseModel.getStatusCode() == 200)
        {
            FeedResponseModel feedResponseModel = new FeedResponseModel();
            feedResponseModel = (FeedResponseModel) feedResponseModel.fromJson(responseModel.getResponseBody());

            for (FeedItemModel feedItem:feedResponseModel.articles)
            {

                FeedData feedData = new FeedData(ctx);
                feedData.insertFeed(feedItem);
            }
            return true;
        }

        return false;

    }
}
