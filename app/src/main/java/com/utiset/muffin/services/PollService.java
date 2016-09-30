package com.utiset.muffin.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.utiset.muffin.core.FeedsCall;
import com.utiset.muffin.exceptions.NullContextExceptions;

import java.io.IOException;

public class PollService extends Service {
    private FeedsCall feedCall;
    public PollService() {

        try {
            feedCall = new FeedsCall(this.getApplicationContext());
            feedCall.getRecentFeeds("http://198.74.52.162:5000/feed/recent");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullContextExceptions nullContextExceptions) {
            nullContextExceptions.printStackTrace();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
